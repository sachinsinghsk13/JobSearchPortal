package com.sachinsingh.jobsearch.service;

import java.security.Principal;
import java.time.LocalDate;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.sachinsingh.jobsearch.exception.EmployerNotFoundException;
import com.sachinsingh.jobsearch.exception.JobPostNotFoundException;
import com.sachinsingh.jobsearch.model.Employer;
import com.sachinsingh.jobsearch.model.JobApplicantApplication;
import com.sachinsingh.jobsearch.model.JobCategory;
import com.sachinsingh.jobsearch.model.JobPost;
import com.sachinsingh.jobsearch.model.Location;
import com.sachinsingh.jobsearch.repository.EmployerRepository;
import com.sachinsingh.jobsearch.repository.JobApplicantApplicationRepository;
import com.sachinsingh.jobsearch.repository.JobCategoryRepository;
import com.sachinsingh.jobsearch.repository.JobPostRepository;
import com.sachinsingh.jobsearch.repository.LocationRepository;

@Service
public class JobPostService {
	
	@Autowired
	private JobPostRepository jobPostRepository;
	
	@Autowired
	private EmployerRepository employerRepository;
	
	@Autowired
	private LocationRepository locationRepo;
	
	@Autowired
	private JobCategoryRepository jobCategoryRepo;
	
	@Autowired
	private JobApplicantApplicationRepository jobApplicantApplicationRepo;
	
	@Transactional
	public JobPost saveJobPost(JobPost jobPost, String employerEmail) throws EmployerNotFoundException {
		Employer employer = employerRepository.findByEmail(employerEmail).orElseThrow(() -> new EmployerNotFoundException());
		jobPost.setPostedBy(employer);
		jobPost.setPostedDate(LocalDate.now());
		JobPost jp = jobPostRepository.save(jobPost);
		return jp;
	}
	
	@Transactional
	public Page<JobPost> searchJobs(Optional<Integer> categoryId, Optional<Long> locationId, Pageable pageable) {
		if (categoryId.isPresent() && locationId.isPresent()) {
			Optional<JobCategory> jc = jobCategoryRepo.findById(categoryId.get());
			Optional<Location> loc = locationRepo.findById(locationId.get());
			return jobPostRepository.findByLocationAndJobCategory(loc.get(), jc.get(), pageable);
		}
		else if (categoryId.isPresent()) {
			Optional<JobCategory> jc = jobCategoryRepo.findById(categoryId.get());
			return jobPostRepository.findByJobCategory(jc.get(), pageable);
		}
		
		else if (locationId.isPresent()) {
			Optional<Location> loc = locationRepo.findById(locationId.get());
			return jobPostRepository.findByLocation(loc.get(), pageable);
		}
		
		else {
			return jobPostRepository.findAll(pageable);
		}
	}

	@Transactional
	public void getJobViewForEmployer(Long jobId, Principal principal, Pageable pageable, ModelAndView modelView) throws EmployerNotFoundException, JobPostNotFoundException {
		Employer employer = employerRepository.findByEmail(principal.getName()).orElseThrow(() -> new EmployerNotFoundException());
		JobPost jobPost = jobPostRepository.findByIdAndPostedBy(jobId, employer).orElseThrow(() -> new JobPostNotFoundException());
		Page<JobApplicantApplication> applicants = jobApplicantApplicationRepo.findByJob(jobPost, pageable);
		
		// Add to Model
		
		modelView.addObject("job", jobPost);
		modelView.addObject("applicants", applicants);
	}
}

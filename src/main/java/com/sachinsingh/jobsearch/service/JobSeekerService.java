package com.sachinsingh.jobsearch.service;

import java.time.LocalDateTime;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sachinsingh.jobsearch.exception.JobAlreadyAppliedException;
import com.sachinsingh.jobsearch.exception.JobPostNotFoundException;
import com.sachinsingh.jobsearch.exception.JobSeekerNotFoundException;
import com.sachinsingh.jobsearch.model.ApplicationStatus;
import com.sachinsingh.jobsearch.model.JobApplicantApplication;
import com.sachinsingh.jobsearch.model.JobPost;
import com.sachinsingh.jobsearch.model.JobSeeker;
import com.sachinsingh.jobsearch.repository.JobApplicantApplicationRepository;
import com.sachinsingh.jobsearch.repository.JobPostRepository;
import com.sachinsingh.jobsearch.repository.JobSeekerRepository;

@Service
public class JobSeekerService {
	
	@Autowired
	private JobPostRepository jobPostRepository;
	
	@Autowired
	private JobApplicantApplicationRepository jobApplicantApplicationRepo;
	
	@Autowired
	private JobSeekerRepository jobSeekerRepo;
	
	@Transactional
	public void applyToJob(String email, Long jobId) {
		JobPost jobPost = jobPostRepository.findById(jobId)
				.orElseThrow(() -> new JobPostNotFoundException());
		
		JobSeeker jobSeeker = jobSeekerRepo.findByEmail(email)
				.orElseThrow(() -> new JobSeekerNotFoundException());
		
		boolean isAlreadyApplied = jobSeeker.getAppliedJobs().stream().anyMatch(app -> app.getJob().equals(jobPost));
		
		if (isAlreadyApplied) {
			throw new JobAlreadyAppliedException();
		}
		
		
		JobApplicantApplication jobApplication = new JobApplicantApplication();
		jobApplication.setJob(jobPost);
		jobApplication.setApplicant(jobSeeker);
		jobApplication.setAppliedOn(LocalDateTime.now());
		jobApplication.setStatus(ApplicationStatus.APPLICATION_SENT);
		
		jobApplicantApplicationRepo.save(jobApplication);
		
	}
}

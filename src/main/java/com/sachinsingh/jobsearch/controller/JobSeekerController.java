package com.sachinsingh.jobsearch.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.sachinsingh.jobsearch.exception.JobSeekerNotFoundException;
import com.sachinsingh.jobsearch.model.JobApplicantApplication;
import com.sachinsingh.jobsearch.model.JobPost;
import com.sachinsingh.jobsearch.model.JobSeeker;
import com.sachinsingh.jobsearch.repository.JobApplicantApplicationRepository;
import com.sachinsingh.jobsearch.repository.JobPostRepository;
import com.sachinsingh.jobsearch.repository.JobSeekerRepository;
import com.sachinsingh.jobsearch.service.JobSeekerService;

@Controller
@RequestMapping("/jobseekers")
public class JobSeekerController {
	
	@Autowired
	private JobSeekerRepository jobSeekerRepo;

	@Autowired
	private JobPostRepository jobPostRepo;
	
	@Autowired
	private JobSeekerService jobSeekerService;
	
	@Autowired
	private JobApplicantApplicationRepository jobApplicantApplicationRepo;
	
	@GetMapping("/test")
	public String home() {
		return "jobseeker/jobseeker-home";
	}
	
	
	@GetMapping("/applied-jobs")
	public ModelAndView appliedJobList(Principal principal,Pageable pageable, ModelAndView modelView) {
		JobSeeker jobseeker = jobSeekerRepo.findByEmail(principal.getName())
								.orElseThrow(() -> new JobSeekerNotFoundException());
		Page<JobApplicantApplication> page = jobApplicantApplicationRepo.findByApplicant(jobseeker, pageable);
		modelView.addObject("appliedJobsApplications", page);
		modelView.setViewName("jobseeker/applied-jobs");
		return modelView;
	}
	
	@PostMapping("/apply-job/{jobId}")
	public ModelAndView applyJobs(@PathVariable("jobId") Long jobId, Principal principal, ModelAndView modelView) {
		jobSeekerService.applyToJob(principal.getName(), jobId);
		modelView.addObject("appliedSuccess", true);
		modelView.setViewName("");
		
		return modelView;
	}
}

package com.sachinsingh.jobsearch.service;

import java.time.LocalDate;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sachinsingh.jobsearch.exception.EmployerNotFoundException;
import com.sachinsingh.jobsearch.model.Employer;
import com.sachinsingh.jobsearch.model.JobPost;
import com.sachinsingh.jobsearch.repository.EmployerRepository;
import com.sachinsingh.jobsearch.repository.JobPostRepository;

@Service
public class JobPostService {
	
	@Autowired
	private JobPostRepository jobPostRepository;
	
	@Autowired
	private EmployerRepository employerRepository;
	
	@Transactional
	public JobPost saveJobPost(JobPost jobPost, String employerEmail) throws EmployerNotFoundException {
		Employer employer = employerRepository.findByEmail(employerEmail).orElseThrow(() -> new EmployerNotFoundException());
		jobPost.setPostedBy(employer);
		jobPost.setPostedDate(LocalDate.now());
		JobPost jp = jobPostRepository.save(jobPost);
		return jp;
	}
}

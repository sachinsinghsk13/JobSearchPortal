package com.sachinsingh.jobsearch.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.sachinsingh.jobsearch.model.JobApplicantApplication;
import com.sachinsingh.jobsearch.model.JobPost;
import com.sachinsingh.jobsearch.model.JobSeeker;

@Repository
public interface JobApplicantApplicationRepository extends PagingAndSortingRepository<JobApplicantApplication, Long> {
	Page<JobApplicantApplication> findByJob(JobPost job, Pageable pageable);
	Page<JobApplicantApplication> findByApplicant(JobSeeker applicant, Pageable pageable);
}

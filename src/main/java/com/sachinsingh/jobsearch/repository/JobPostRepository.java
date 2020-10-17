package com.sachinsingh.jobsearch.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.sachinsingh.jobsearch.model.Employer;
import com.sachinsingh.jobsearch.model.JobCategory;
import com.sachinsingh.jobsearch.model.JobPost;
import com.sachinsingh.jobsearch.model.Location;

@Repository
public interface JobPostRepository extends PagingAndSortingRepository<JobPost, Long>{
	Optional<JobPost> findByIdAndPostedBy(Long id, Employer employer);
	Page<JobPost> findByLocationAndJobCategory(Location location, JobCategory jobCategory, Pageable pageable);
	Page<JobPost> findByLocation(Location location, Pageable pageable);
	Page<JobPost> findByJobCategory(JobCategory jobCategory, Pageable pageable);
	Page<JobPost> findByPostedBy(Employer postedBy, Pageable pageable);
	
}

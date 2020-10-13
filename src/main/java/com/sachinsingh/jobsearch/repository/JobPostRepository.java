package com.sachinsingh.jobsearch.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sachinsingh.jobsearch.model.Employer;
import com.sachinsingh.jobsearch.model.JobPost;

@Repository
public interface JobPostRepository extends CrudRepository<JobPost, Long>{
	Optional<JobPost> findByIdAndPostedBy(Long id, Employer employer);
}

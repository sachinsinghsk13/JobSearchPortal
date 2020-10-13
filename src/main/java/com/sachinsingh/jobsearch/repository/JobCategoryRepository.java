package com.sachinsingh.jobsearch.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sachinsingh.jobsearch.model.JobCategory;

@Repository
public interface JobCategoryRepository extends CrudRepository<JobCategory, Integer> {
	
}

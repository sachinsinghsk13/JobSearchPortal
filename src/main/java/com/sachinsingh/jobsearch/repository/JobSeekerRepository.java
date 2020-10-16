package com.sachinsingh.jobsearch.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sachinsingh.jobsearch.model.JobSeeker;

@Repository
public interface JobSeekerRepository extends CrudRepository<JobSeeker, Long>{
	Optional<JobSeeker> findByEmail(String email);
}

package com.sachinsingh.jobsearch.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sachinsingh.jobsearch.model.Employer;

@Repository
public interface EmployerRepository extends CrudRepository<Employer, Long>{
	Optional<Employer> findByEmail(String email);
}

package com.sachinsingh.jobsearch.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sachinsingh.jobsearch.exception.EmployerNotFoundException;
import com.sachinsingh.jobsearch.model.Employer;
import com.sachinsingh.jobsearch.repository.EmployerRepository;

@Service
public class EmployerService {

	@Autowired
	private EmployerRepository employerRepository;
	
	public Employer getEmployerByEmail(String email) throws EmployerNotFoundException {
		return employerRepository.findByEmail(email).orElseThrow(() -> new EmployerNotFoundException());
	}
}

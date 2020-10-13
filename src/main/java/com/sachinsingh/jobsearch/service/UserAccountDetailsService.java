package com.sachinsingh.jobsearch.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.sachinsingh.jobsearch.repository.AccountRepository;

@Service
public class UserAccountDetailsService implements UserDetailsService {

	@Autowired
	private AccountRepository accountsRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return accountsRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException(username + " not found"));
	}

}

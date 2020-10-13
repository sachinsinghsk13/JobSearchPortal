package com.sachinsingh.jobsearch.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.sachinsingh.jobsearch.model.Account;

public interface AccountRepository extends CrudRepository<Account, Long> {
	Optional<Account> findByEmail(String email);
}

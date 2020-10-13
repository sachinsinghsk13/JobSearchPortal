package com.sachinsingh.jobsearch.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sachinsingh.jobsearch.model.VerificationToken;

@Repository
public interface VerificationTokenRepository extends CrudRepository<VerificationToken, Long> {
	Optional<VerificationToken> findByToken(String token);
}

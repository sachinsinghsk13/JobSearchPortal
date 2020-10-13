package com.sachinsingh.jobsearch.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sachinsingh.jobsearch.model.Location;

@Repository
public interface LocationRepository extends CrudRepository<Location, Long> {

}

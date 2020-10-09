package com.sachinsingh.jobsearch.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "employers")
public class Employer extends Account {
	private static final long serialVersionUID = 1L;

	@Column(name = "name", nullable = false)
	private String name;
	
	@Column(name = "contact_number", nullable = false, unique = true)
	private String contactNumber;
	
	@Column(name = "gender", nullable = false)
	@Enumerated(EnumType.STRING)
	private Gender gender;
	
	private Company company;

	@OneToMany(mappedBy = "postedBy", cascade = CascadeType.ALL)
	private List<JobPost> jobPosts = new ArrayList<>();
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}
	
	
}

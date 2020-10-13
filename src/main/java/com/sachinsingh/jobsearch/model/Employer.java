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
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Entity
@Table(name = "employers")
public class Employer extends Account {
	private static final long serialVersionUID = 1L;

	@Column(name = "name", nullable = false)
	@NotBlank
	@NotNull
	private String name;
	
	@Column(name = "contact_number", nullable = false, unique = true)
	@Pattern(regexp = "^(?:(?:\\+|0{0,2})91(\\s*[\\ -]\\s*)?|[0]?)?[789]\\d{9}|(\\d[ -]?){10}\\d$", message = "Please Enter a Valid Mobile Number")
	@NotNull
	private String contactNumber;
	
	@Column(name = "gender", nullable = false)
	@Enumerated(EnumType.STRING)
	private Gender gender = Gender.MALE;
	
	private Company company;

	@OneToMany(mappedBy = "postedBy", cascade = CascadeType.ALL)
	private List<JobPost> jobPosts = new ArrayList<>();
	
	public Employer() {
		company = new Company();
	}
	
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

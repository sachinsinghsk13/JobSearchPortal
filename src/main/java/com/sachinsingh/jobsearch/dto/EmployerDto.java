package com.sachinsingh.jobsearch.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.sachinsingh.jobsearch.model.Company;
import com.sachinsingh.jobsearch.model.Gender;

public class EmployerDto {
	
	private Long id;
	@NotNull
	@NotBlank
	private String name;
	private String email;
	@Pattern(regexp = "^(?:(?:\\+|0{0,2})91(\\s*[\\ -]\\s*)?|[0]?)?[789]\\d{9}|(\\d[ -]?){10}\\d$", message = "Please Enter a Valid Mobile Number")
	@NotNull
	private String contactNumber;
	private Gender gender;
	private Company company;
	
	public EmployerDto() {
		company = new Company();
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
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
	@Override
	public String toString() {
		return "EmployerDto [id=" + id + ", name=" + name + ", email=" + email + ", contactNumber=" + contactNumber
				+ ", gender=" + gender + ", company=" + company + "]";
	}
	
	
}

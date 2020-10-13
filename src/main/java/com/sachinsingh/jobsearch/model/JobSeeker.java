package com.sachinsingh.jobsearch.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "job_seekers")
public class JobSeeker extends Account {
	private static final long serialVersionUID = 1L;
	
	@Column(name = "full_name", nullable = false)
	@NotNull(message = "Please Enter Your Name")
	@NotBlank(message="Please Enter Your Name")
	private String name;
	
	@Column(name = "contact_number", nullable = false, unique = true)
	@Pattern(regexp = "^(?:(?:\\+|0{0,2})91(\\s*[\\ -]\\s*)?|[0]?)?[789]\\d{9}|(\\d[ -]?){10}\\d$", message = "Please Enter a Valid Mobile Number")
	@NotNull
	private String contactNumber;
	
	@Column(name = "date_of_birth", nullable = false)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@NotNull
	private LocalDate dateOfBirth;
	
	@Column(name = "gender", nullable = false)
	@Enumerated(EnumType.STRING)
	@NotNull
	private Gender gender = Gender.MALE;
	
	@Column(name = "resume", nullable = true)
	@Lob
	private byte[] resume;
	
	@ElementCollection
	@JoinColumn(name = "job_seeker_id")
	private Set<EducationalDetails> educationalDetails = new HashSet<>();
	
	@ElementCollection
	@JoinColumn(name = "job_seeker_id")
	private Set<WorkExperience> workExperiences = new HashSet<>();

	@OneToMany(mappedBy = "applicant")
	private List<JobApplicantApplication> appliedJobs = new ArrayList<>();
	
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

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public List<JobApplicantApplication> getAppliedJobs() {
		return appliedJobs;
	}

	public void setAppliedJobs(List<JobApplicantApplication> appliedJobs) {
		this.appliedJobs = appliedJobs;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public byte[] getResume() {
		return resume;
	}

	public void setResume(byte[] resume) {
		this.resume = resume;
	}

	public Set<EducationalDetails> getEducationalDetails() {
		return educationalDetails;
	}

	public void setEducationalDetails(Set<EducationalDetails> educationalDetails) {
		this.educationalDetails = educationalDetails;
	}

	public Set<WorkExperience> getWorkExperiences() {
		return workExperiences;
	}

	public void setWorkExperiences(Set<WorkExperience> workExperiences) {
		this.workExperiences = workExperiences;
	}

	@Override
	public String toString() {
		return "JobSeeker [name=" + name + ", contactNumber=" + contactNumber + ", dateOfBirth=" + dateOfBirth
				+ ", gender=" + gender + ", educationalDetails=" + educationalDetails + ", workExperiences="
				+ workExperiences + ", id=" + id + ", email=" + email + ", password=" + password + ", role=" + role
				+ ", active=" + active + "]";
	}
	
	
}

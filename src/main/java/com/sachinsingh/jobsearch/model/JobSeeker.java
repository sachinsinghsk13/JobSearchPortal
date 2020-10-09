package com.sachinsingh.jobsearch.model;

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

@Entity
@Table(name = "job_seekers")
public class JobSeeker extends Account {
	private static final long serialVersionUID = 1L;
	
	@Column(name = "full_name", nullable = false)
	private String name;
	
	@Column(name = "contact_number", nullable = false, unique = true)
	private String contactNumber;
	
	@Column(name = "date_of_birth", nullable = false)
	@Temporal(TemporalType.DATE)
	private Date dateOfBirth;
	
	@Column(name = "gender", nullable = false)
	@Enumerated(EnumType.STRING)
	private Gender gender;
	
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
	@JoinColumn(name = "job_seeker_id", nullable = false)
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

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
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
	
}

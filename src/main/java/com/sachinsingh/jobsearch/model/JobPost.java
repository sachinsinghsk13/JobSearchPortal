package com.sachinsingh.jobsearch.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "job_posts")
public class JobPost {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "job_title", nullable = false)
	private String jobTitle;
	
	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	private JobCategory jobCategory;
	
	@Column(name = "salary")
	@AttributeOverrides({
			@AttributeOverride(name = "highest", column = @Column(name = "highest_salary")),
			@AttributeOverride(name = "lowest", column = @Column(name = "lowest_salary"))
	})
	private Range<Double> salary;
	
	
	@Column(name = "experience")
	@AttributeOverrides({
		@AttributeOverride(name = "highest", column = @Column(name = "highest_experience")),
		@AttributeOverride(name = "lowest", column = @Column(name = "lowest_experience"))
	})
	private Range<Integer> experience;
	
	@Column(name = "job_type", nullable = false)
	@Enumerated(EnumType.STRING)
	private JobType jobType;
	
	@Column(name = "posted_date", nullable = false)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate postedDate;
	
	@Column(name = "expiry_date", nullable = false)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate expiryDate;
	
	@Column(name = "vacancies")
	private Integer vacancies;
	
	@Column(name = "job_description", columnDefinition = "varchar(2048)")
	private String description;
	
	@ManyToOne(optional = false)
	private Location location;
	
	@OneToMany(mappedBy = "job")
	private List<JobApplicantApplication> applicants = new ArrayList<>();
	
	@ManyToOne(optional = false)
	private Employer postedBy;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	public JobCategory getJobCategory() {
		return jobCategory;
	}

	public void setJobCategory(JobCategory jobCategory) {
		this.jobCategory = jobCategory;
	}

	public Range<Double> getSalary() {
		return salary;
	}

	public void setSalary(Range<Double> salary) {
		this.salary = salary;
	}

	public Range<Integer> getExperience() {
		return experience;
	}

	public void setExperience(Range<Integer> experience) {
		this.experience = experience;
	}

	public JobType getJobType() {
		return jobType;
	}

	public void setJobType(JobType jobType) {
		this.jobType = jobType;
	}

	public LocalDate getPostedDate() {
		return postedDate;
	}

	public void setPostedDate(LocalDate postedDate) {
		this.postedDate = postedDate;
	}

	public List<JobApplicantApplication> getApplicants() {
		return applicants;
	}

	public void setApplicants(List<JobApplicantApplication> applicants) {
		this.applicants = applicants;
	}

	public Employer getPostedBy() {
		return postedBy;
	}

	public void setPostedBy(Employer postedBy) {
		this.postedBy = postedBy;
	}

	public LocalDate getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(LocalDate expiryDate) {
		this.expiryDate = expiryDate;
	}

	public Integer getVacancies() {
		return vacancies;
	}

	public void setVacancies(Integer vacancies) {
		this.vacancies = vacancies;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}
	
}

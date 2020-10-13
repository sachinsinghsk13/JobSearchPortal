package com.sachinsingh.jobsearch.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CollectionId;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

@Entity
@Table(name = "job_applicant_applications")
public class JobApplicantApplication {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "application_status", nullable = false)
	private ApplicationStatus status;
	
	@ManyToOne(optional = false)
	private JobSeeker applicant;
	
	@ManyToOne(optional = false)
	private JobPost job;
	
	@Column(name = "applied_on", nullable = false)
	private LocalDateTime appliedOn;
	
	@ElementCollection
	@CollectionId(columns = { @Column(name="message_id") }, generator = "message_sequence", type = @Type(type = "long"))
	@GenericGenerator(name = "message_sequence", strategy = "sequence")
	private List<Message> messages = new ArrayList<>();
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public ApplicationStatus getStatus() {
		return status;
	}
	public void setStatus(ApplicationStatus status) {
		this.status = status;
	}
	public JobSeeker getApplicant() {
		return applicant;
	}
	public void setApplicant(JobSeeker applicant) {
		this.applicant = applicant;
	}
	public JobPost getJob() {
		return job;
	}
	public void setJob(JobPost job) {
		this.job = job;
	}
	public List<Message> getMessages() {
		return messages;
	}
	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}
	public LocalDateTime getAppliedOn() {
		return appliedOn;
	}
	public void setAppliedOn(LocalDateTime appliedOn) {
		this.appliedOn = appliedOn;
	}
	
	
}

package com.sachinsingh.jobsearch.model;

import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Lob;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

@Embeddable
public class Company {
	
	@Column(name = "company_name", nullable = false)
	@NotNull
	@NotBlank
	private String companyName;
	
	@Column(name = "industry")
	private String industry;
	
	@Column(name = "address")
	private String address;
	
	@Column(name = "established_in")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate establishedIn;
	
	@Column(name = "about_company", columnDefinition = "VARCHAR(2048)")
	private String about;
	
	@Column(name = "logo")
	@Lob
	private byte[] logo;

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getIndustry() {
		return industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	
	public LocalDate getEstablishedIn() {
		return establishedIn;
	}

	public void setEstablishedIn(LocalDate establishedIn) {
		this.establishedIn = establishedIn;
	}

	public String getAbout() {
		return about;
	}

	public void setAbout(String about) {
		this.about = about;
	}

	public byte[] getLogo() {
		return logo;
	}

	public void setLogo(byte[] logo) {
		this.logo = logo;
	}

	@Override
	public String toString() {
		return "Company [companyName=" + companyName + ", industry=" + industry + ", address=" + address
				+ ", establishedIn=" + establishedIn + ", about=" + about + "]";
	}
	
	
	
}



package com.sachinsingh.jobsearch.model;

import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Embeddable
public class EducationalDetails {
	
	@Column(name = "education_type", nullable = false)
	private String educationType;
	
	@Column(name = "board_or_university", nullable = false)
	private String boardOrUniversity;
	
	@Column(name = "start_date", nullable = false)
	private LocalDate startDate;
	
	@Column(name = "end_date", nullable = true)
	private LocalDate endDate;
	
	@Column(name = "percentage")
	private Double percentage;

	public String getEducationType() {
		return educationType;
	}

	public void setEducationType(String educationType) {
		this.educationType = educationType;
	}

	public String getBoardOrUniversity() {
		return boardOrUniversity;
	}

	public void setBoardOrUniversity(String boardOrUniversity) {
		this.boardOrUniversity = boardOrUniversity;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public Double getPercentage() {
		return percentage;
	}

	public void setPercentage(Double percentage) {
		this.percentage = percentage;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((boardOrUniversity == null) ? 0 : boardOrUniversity.hashCode());
		result = prime * result + ((educationType == null) ? 0 : educationType.hashCode());
		result = prime * result + ((endDate == null) ? 0 : endDate.hashCode());
		result = prime * result + ((percentage == null) ? 0 : percentage.hashCode());
		result = prime * result + ((startDate == null) ? 0 : startDate.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EducationalDetails other = (EducationalDetails) obj;
		if (boardOrUniversity == null) {
			if (other.boardOrUniversity != null)
				return false;
		} else if (!boardOrUniversity.equals(other.boardOrUniversity))
			return false;
		if (educationType == null) {
			if (other.educationType != null)
				return false;
		} else if (!educationType.equals(other.educationType))
			return false;
		if (endDate == null) {
			if (other.endDate != null)
				return false;
		} else if (!endDate.equals(other.endDate))
			return false;
		if (percentage == null) {
			if (other.percentage != null)
				return false;
		} else if (!percentage.equals(other.percentage))
			return false;
		if (startDate == null) {
			if (other.startDate != null)
				return false;
		} else if (!startDate.equals(other.startDate))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "EducationalDetails [educationType=" + educationType + ", boardOrUniversity=" + boardOrUniversity
				+ ", startDate=" + startDate + ", endDate=" + endDate + ", pertentage=" + percentage + "]";
	}
	
}

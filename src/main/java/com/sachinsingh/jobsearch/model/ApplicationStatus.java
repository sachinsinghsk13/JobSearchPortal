package com.sachinsingh.jobsearch.model;

public enum ApplicationStatus {
	APPLICATION_SENT("Application Sent"),
	APPLICATION_VIEWED("Application Viewed"),
	NOT_SUITABLE("Not Suitable"),
	SHORTLISTED_FOR_INTERVIEW("Shortlisted For Interview");
	
	private String status;
	
	private ApplicationStatus(String status) {
		this.status = status;
	}
	
	@Override
	public String toString() {
		return status;
	}
}

package com.sachinsingh.jobsearch.security;

public enum ApplicationSecurityPermission {
	CREATE_JOB_POST("create_job_post");
	
	private final String permission;
	
	private ApplicationSecurityPermission(String permission) {
		this.permission = permission;
	}
	
	public String getPermission() {
		return permission;
	}
}

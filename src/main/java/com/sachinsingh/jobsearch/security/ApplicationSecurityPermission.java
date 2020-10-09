package com.sachinsingh.jobsearch.security;

public enum ApplicationSecurityPermission {
	CREATE_JOB_POST("create_job_post"),
	VIEW_JOB_POSTS("view_job_posts");
	private final String permission;
	
	private ApplicationSecurityPermission(String permission) {
		this.permission = permission;
	}
	
	public String getPermission() {
		return permission;
	}
}

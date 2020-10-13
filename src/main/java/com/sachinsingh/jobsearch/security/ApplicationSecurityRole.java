package com.sachinsingh.jobsearch.security;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.google.common.collect.Sets;

public enum ApplicationSecurityRole {
	ADMIN(Sets.newHashSet(ApplicationSecurityPermission.CREATE_JOB_POST)),
	JOB_SEEKER(Sets.newHashSet(ApplicationSecurityPermission.CREATE_JOB_POST)),
	EMPLOYER(Sets.newHashSet(ApplicationSecurityPermission.CREATE_JOB_POST));
	
	private Set<ApplicationSecurityPermission> permissions;
	
	private ApplicationSecurityRole(Set<ApplicationSecurityPermission> permissions) {
		this.permissions = permissions;
	}
	
	public Set<ApplicationSecurityPermission> getPersmissions() {
		return permissions;
	}
	
	public Collection<GrantedAuthority> getAuthorities() {
		Set<GrantedAuthority> authorities = new HashSet<>();
		
		Set<SimpleGrantedAuthority> x =  getPersmissions()
			.stream()
			.map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
			.collect(Collectors.toSet());
		authorities.addAll(x);
		authorities.add(new SimpleGrantedAuthority("ROLE_" +this.name()));
		return authorities;
	}
	
	
}

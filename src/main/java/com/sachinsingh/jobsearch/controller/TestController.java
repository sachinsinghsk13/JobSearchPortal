package com.sachinsingh.jobsearch.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.sachinsingh.jobsearch.security.ApplicationSecurityPermission;
import com.sachinsingh.jobsearch.security.ApplicationSecurityRole;

@Controller
public class TestController {
	
	@GetMapping("/test")
	public String test() {
		System.out.println(ApplicationSecurityRole.ADMIN.getAuthorities());
		return "index";
	}
}

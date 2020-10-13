package com.sachinsingh.jobsearch.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/jobseekers")
public class JobSeekerController {
	
	
	@GetMapping("/test")
	public String home() {
		return "jobseeker/jobseeker-home";
	}
}

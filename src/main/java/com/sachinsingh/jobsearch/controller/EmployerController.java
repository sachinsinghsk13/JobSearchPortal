package com.sachinsingh.jobsearch.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/employers")
public class EmployerController {

	@GetMapping("/test")
	public String test() {
		return "test";
	}
}

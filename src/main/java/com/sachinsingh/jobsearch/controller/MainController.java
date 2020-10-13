package com.sachinsingh.jobsearch.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.sachinsingh.jobsearch.repository.JobCategoryRepository;
import com.sachinsingh.jobsearch.repository.LocationRepository;

@Controller
public class MainController {
	@Autowired
	private JobCategoryRepository jobCategoryRepo;
	
	@Autowired
	private LocationRepository locationRepo;
	
	@GetMapping({"/", "/index"})
	public String home(Model model) {
		model.addAttribute("pageTitle", "Job Search India | Find Your Dreame Job");
		model.addAttribute("jobCategories", jobCategoryRepo.findAll());
		model.addAttribute("locations", locationRepo.findAll());
		return "index";
	}
	
	@PostMapping("/")
	public String homePost(Model model) {
		model.addAttribute("pageTitle", "Job Search India | Find Your Dreame Job");
		model.addAttribute("jobCategories", jobCategoryRepo.findAll());
		model.addAttribute("locations", locationRepo.findAll());
		return "index";
	}

}

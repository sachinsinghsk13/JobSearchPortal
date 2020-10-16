package com.sachinsingh.jobsearch.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.sachinsingh.jobsearch.exception.JobPostNotFoundException;
import com.sachinsingh.jobsearch.model.JobPost;
import com.sachinsingh.jobsearch.repository.JobCategoryRepository;
import com.sachinsingh.jobsearch.repository.JobPostRepository;
import com.sachinsingh.jobsearch.repository.LocationRepository;
import com.sachinsingh.jobsearch.service.JobPostService;

@Controller
public class MainController {
	@Autowired
	private JobCategoryRepository jobCategoryRepo;
	
	@Autowired
	private LocationRepository locationRepo;
	
	@Autowired
	private JobPostRepository jobPostRepository;
	
	@Autowired
	private JobPostService jobPostService;
	
	
	@GetMapping({"/", "/index"})
	public String home(Model model) {
		model.addAttribute("pageTitle", "Job Search India | Find Your Dreame Job");
		model.addAttribute("jobCategories", jobCategoryRepo.findAll());
		model.addAttribute("locations", locationRepo.findAll());
		Sort sort = Sort.by(Order.desc("postedDate"));
		Pageable pageable = PageRequest.of(0, 10, sort);
		Page<JobPost> jobs = jobPostRepository.findAll(pageable);
		model.addAttribute("recentJobs", jobs);
		return "index";
	}
	
	@PostMapping("/")
	public String homePost(Model model) {
		model.addAttribute("pageTitle", "Job Search India | Find Your Dreame Job");
		model.addAttribute("jobCategories", jobCategoryRepo.findAll());
		model.addAttribute("locations", locationRepo.findAll());
		return "index";
	}

	
	@GetMapping("/jobs")
	public ModelAndView jobSearch(@RequestParam("category") Optional<Integer> categoryId,@RequestParam("location") Optional<Long> locationId,Pageable pageable ,ModelAndView modelView) {
		System.out.println(categoryId);
		System.out.println(locationId);
		Page<JobPost> page = jobPostService.searchJobs(categoryId, locationId, pageable);
		modelView.addObject("jobs", page);
		modelView.addObject("pageTitle", "Job Search India | Find Your Dreame Job");
		modelView.addObject("jobCategories", jobCategoryRepo.findAll());
		modelView.addObject("locations", locationRepo.findAll());
		if (categoryId.isPresent())
			modelView.addObject("category", categoryId.get());
		if (locationId.isPresent())
			modelView.addObject("location", locationId.get());
		modelView.setViewName("jobs");
		return modelView;
	}
	
	@GetMapping("/jobs/{jobId}")
	public ModelAndView viewJob(@PathVariable("jobId") Long jobId, ModelAndView modelView) throws JobPostNotFoundException {
		JobPost job = jobPostRepository.findById(jobId).orElseThrow(() -> new JobPostNotFoundException());
		modelView.addObject("job", job);
		modelView.setViewName("job-view");
		return modelView;
	}
	
	
}

















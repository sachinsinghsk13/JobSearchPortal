package com.sachinsingh.jobsearch.controller;

import java.security.Principal;
import java.util.Optional;

import javax.mail.MessagingException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.sachinsingh.jobsearch.model.Account;
import com.sachinsingh.jobsearch.model.Employer;
import com.sachinsingh.jobsearch.model.JobSeeker;
import com.sachinsingh.jobsearch.service.AccountsService;

@Controller
public class AccountController {
	
	@Autowired
	private AccountsService accountsService;
	
	@GetMapping("/employer-sign-up")
	public ModelAndView employerRegistrationForm(ModelAndView modelView) {
		modelView.addObject("pageTitle", "Employer Sign Up | Job Search ");
		modelView.addObject("employer", new Employer());
		modelView.setViewName("employer-registration-form");
		return modelView;
	}
	
	@PostMapping("/employer-sign-up")
	public ModelAndView processEmployerRegistration(@Valid @ModelAttribute("employer") Employer employer,BindingResult bindingResults, ModelAndView modelView) throws MessagingException {
		System.out.println(employer);
		System.out.println(bindingResults);
		if (bindingResults.hasErrors()) {
			modelView.addObject("alert", true);
			modelView.addObject("alertClass", "alert alert-danger");
			modelView.addObject("alertMessage", "Please Resolve the Errors in form");
			modelView.setViewName("employer-registration-form");
			return modelView;
		}
		accountsService.registerEmployer(employer);
		modelView.addObject("alert", true);
		modelView.addObject("alertClass", "alert alert-success");
		modelView.addObject("alertMessage", "Account Created! We've sent you an email with verification link");
		modelView.setViewName("index");
		return modelView;
	}
	
	@GetMapping("/job-seeker-sign-up")
	public ModelAndView jobSeekerRegistration(ModelAndView modelView) {
		modelView.addObject("jobSeeker", new JobSeeker());
		modelView.addObject("pageTitle", "Job Seeker Registration | Job Seach India");
		modelView.setViewName("jobseeker-registration-form");
		return modelView;
	}
	
	@PostMapping("/job-seeker-sign-up")
	public ModelAndView processJobSeekerRegistration(@Valid @ModelAttribute("jobSeeker") JobSeeker jobSeeker,BindingResult bindingResults, ModelAndView modelView) throws MessagingException {
		if (bindingResults.hasErrors()) {
			modelView.addObject("alert", true);
			modelView.addObject("alertClass", "alert alert-danger");
			modelView.addObject("alertMessage", "Please Resolve the Errors in form");
			modelView.setViewName("jobseeker-registration-form");
			return modelView;
		}
		accountsService.registerJobSeeker(jobSeeker);
		modelView.addObject("alert", true);
		modelView.addObject("alertClass", "alert alert-success");
		modelView.addObject("alertMessage", "Account Created! We've sent you an email with verification link");
		modelView.setViewName("index");
		return modelView;
	}
	
	
	@GetMapping("/login")
	public String loginPage() {
		return "login-form";
	}
	
	
	@PostMapping("/login")
	public ModelAndView loginProcess(ModelAndView modelView) {
		modelView.setViewName("login-form");
		return modelView;
	}
	
	
	@GetMapping("/verifyToken/{token}")
	public ModelAndView verifyActivationToken(@PathVariable("token") String token, ModelAndView modelView) {
		Account user = accountsService.verifyToken(token);
		System.out.println(user);
		modelView.addObject("alert", true);
		if (user == null) {
			modelView.addObject("alertClass", "alert alert-danger");
			modelView.addObject("alertMessage", "Token Not Valid or it may have been expired");
		}
		else {
			modelView.addObject("alertClass", "alert alert-success");
			modelView.addObject("alertMessage", "You're Account is Verified Now. You can Sign In");
		}
		modelView.setViewName("index");
		return modelView;
	}
}

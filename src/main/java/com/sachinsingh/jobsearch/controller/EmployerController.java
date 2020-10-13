package com.sachinsingh.jobsearch.controller;

import java.security.Principal;
import java.time.LocalDate;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.sachinsingh.jobsearch.dto.EmployerDto;
import com.sachinsingh.jobsearch.exception.EmployerNotFoundException;
import com.sachinsingh.jobsearch.exception.JobPostNotFoundException;
import com.sachinsingh.jobsearch.model.Employer;
import com.sachinsingh.jobsearch.model.JobCategory;
import com.sachinsingh.jobsearch.model.JobPost;
import com.sachinsingh.jobsearch.model.Location;
import com.sachinsingh.jobsearch.model.Range;
import com.sachinsingh.jobsearch.repository.EmployerRepository;
import com.sachinsingh.jobsearch.repository.JobCategoryRepository;
import com.sachinsingh.jobsearch.repository.JobPostRepository;
import com.sachinsingh.jobsearch.repository.LocationRepository;
import com.sachinsingh.jobsearch.service.EmployerService;
import com.sachinsingh.jobsearch.service.JobPostService;

@Controller
@RequestMapping("/employers")
public class EmployerController {

	@Autowired
	private EmployerService employerService;
	
	@Autowired
	private JobCategoryRepository jobCategoryRepo;
	
	@Autowired
	private JobPostRepository jobPostRepository;
	
	@Autowired
	private LocationRepository locationRepo;
	
	
	@Autowired
	private EmployerRepository employerRepository;
	
	@Autowired
	private JobPostService jobPostService;
	
	
	@GetMapping("/test")
	public String test() throws EmployerNotFoundException {
	
		return "test";
	}
	
	
	@GetMapping("/profile")
	public ModelAndView employerProfile(Principal principal,ModelAndView modelView) throws EmployerNotFoundException {
		Employer employer = employerService.getEmployerByEmail(principal.getName());
		modelView.addObject("employer", employer);
		modelView.setViewName("employer/employer-profile");
		return modelView;
	}
	
	@GetMapping("/edit-profile")
	public ModelAndView employerProfileEdit(Principal principal, ModelAndView modelView) throws EmployerNotFoundException {
		Employer employer = employerService.getEmployerByEmail(principal.getName());
		EmployerDto dto = new EmployerDto();
		dto.setId(employer.getId());
		dto.setName(employer.getName());
		dto.setEmail(employer.getEmail());
		dto.setContactNumber(employer.getContactNumber());
		dto.setCompany(employer.getCompany());
		dto.setGender(employer.getGender());
		
		modelView.addObject("employer", dto);
		modelView.setViewName("employer/employer-profile-edit");
		return modelView;
	}
	
	
	@PostMapping("/edit-profile")
	public ModelAndView processEmployerProfileEdit(Principal principal, @Valid @ModelAttribute("employer") EmployerDto employer, BindingResult br, ModelAndView modelView) throws EmployerNotFoundException {
		if (br.hasErrors()) {
			modelView.addObject("error", true);
			modelView.setViewName("employer/employer-profile-edit");
			return modelView;
		}
		Employer emp = employerService.getEmployerByEmail(principal.getName());
		// Mapping code: will mapstruct library later to simply this
		emp.setName(employer.getName());
		emp.setContactNumber(employer.getContactNumber());
		emp.getCompany().setIndustry(employer.getCompany().getIndustry());
		emp.getCompany().setAddress(employer.getCompany().getAddress());
		emp.getCompany().setAbout(employer.getCompany().getAbout());
		emp.getCompany().setEstablishedIn(employer.getCompany().getEstablishedIn());
		emp.getCompany().setLogo(employer.getCompany().getLogo());
		System.out.println(emp);
		emp = employerRepository.save(emp);
		modelView.addObject("employer", emp);
		modelView.setViewName("employer/employer-profile");
		return modelView;
	}
	
	@GetMapping("/new-job-post")
	public ModelAndView newJobPostForm(ModelAndView modelView, Principal p) {
		modelView.addObject("job", new JobPost());
		System.out.println(p.getName());
		modelView.addObject("categories", jobCategoryRepo.findAll());
		modelView.addObject("locations", locationRepo.findAll());
		modelView.setViewName("employer/new-job-post");
		return modelView;
	}
	
	
	@PostMapping("/new-job-post")
	public ModelAndView processNewJobPost(@Valid @ModelAttribute("job") JobPost job,BindingResult bindingResult,Principal principal, ModelAndView modelView) throws EmployerNotFoundException {
		if (bindingResult.hasErrors()) {
			modelView.setViewName("employer/new-job-post");
			return modelView;
		}
		JobPost jp = jobPostService.saveJobPost(job, principal.getName());
		modelView.setViewName("redirect:/employers/job-posts/"+jp.getId());
		return modelView;
	}
	
	@GetMapping("/job-posts/{jobId}")
	public ModelAndView viewJobPost(@PathVariable("jobId") Long jobId, Principal principal, ModelAndView modelView) throws EmployerNotFoundException, JobPostNotFoundException {
		Employer employer = employerRepository.findByEmail(principal.getName()).orElseThrow(() -> new EmployerNotFoundException());
		JobPost jobPost = jobPostRepository.findByIdAndPostedBy(jobId, employer).orElseThrow(() -> new JobPostNotFoundException());
		modelView.addObject("job", jobPost);
		modelView.setViewName("employer/job-view");
		return modelView;
	}
}

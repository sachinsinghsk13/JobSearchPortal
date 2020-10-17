package com.sachinsingh.jobsearch.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalField;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.sachinsingh.jobsearch.model.Account;
import com.sachinsingh.jobsearch.repository.AccountRepository;

@Controller
public class Test {
	
	@Autowired
	AccountRepository repo;
	
	@GetMapping("/test")
	public String test(Model model) {
		Account acout = repo.findById(3L).get();
		System.out.println(acout);
		return "test";
	}
	
	public static void main(String[] args) {
	
	}
}

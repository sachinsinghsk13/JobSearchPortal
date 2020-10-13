package com.sachinsingh.jobsearch.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.sachinsingh.jobsearch.model.Account;
import com.sachinsingh.jobsearch.model.Employer;
import com.sachinsingh.jobsearch.model.JobSeeker;
import com.sachinsingh.jobsearch.model.VerificationToken;
import com.sachinsingh.jobsearch.repository.AccountRepository;
import com.sachinsingh.jobsearch.repository.VerificationTokenRepository;
import com.sachinsingh.jobsearch.security.ApplicationSecurityRole;

@Service
public class AccountsService {
	@Autowired
	private VerificationTokenRepository verificationTokenRepository;
	
	@Autowired
	private JavaMailSender mailSender;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private AccountRepository accountRepository;
	
	
	private void sendVerificationToken(String email, String token) throws MessagingException {
		StringBuilder sb = new StringBuilder();
		sb.append("<html>");
		sb.append("<body>");
		sb.append("<h2>Job Search Account Verification </h2>");
		sb.append("<p>Click on the following link to activate your account</p>");
		sb.append("<a href=\"http://localhost:8080/verifyToken/"+token+"\">Activate Account</a>");
		sb.append("</body>");
		sb.append("</html>");
		MimeMessagePreparator mmp = (mimeMessage) -> {
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
			helper.setTo(email);
			helper.setSubject("Job Search Account Verification Link");
			helper.setText(sb.toString(), true);
		};
		new Thread(() -> mailSender.send(mmp)).start();
	}
	
	@Transactional
	public void registerJobSeeker(JobSeeker jobSeeker) throws MessagingException {
		jobSeeker.setRole(ApplicationSecurityRole.JOB_SEEKER);
		jobSeeker.setCreatedTimestamp(LocalDateTime.now());
		jobSeeker.setPassword(passwordEncoder.encode(jobSeeker.getPassword())); // set encoded password
		VerificationToken token = new VerificationToken();
		token.setToken(UUID.randomUUID().toString()); // generate random token
		token.setExpiryDate(LocalDate.now().plus(2, ChronoUnit.WEEKS)); // valid for 2 Weeks
		token.setAccount(jobSeeker);
		verificationTokenRepository.save(token);
		
		sendVerificationToken(jobSeeker.getEmail(), token.getToken());
	}
	
	@Transactional
	public void registerEmployer(Employer employer) throws MessagingException {
		employer.setRole(ApplicationSecurityRole.EMPLOYER);
		employer.setCreatedTimestamp(LocalDateTime.now());
		employer.setPassword(passwordEncoder.encode(employer.getPassword()));
		
		VerificationToken token = new VerificationToken();
		token.setToken(UUID.randomUUID().toString()); // generate random token
		token.setExpiryDate(LocalDate.now().plus(2, ChronoUnit.WEEKS)); // valid for 2 Weeks
		token.setAccount(employer);
		System.out.println(token);
		verificationTokenRepository.save(token);
		
		sendVerificationToken(employer.getEmail(), token.getToken());
	}
	
	
	public Account verifyToken(String vt) {
		Optional<VerificationToken> token = verificationTokenRepository.findByToken(vt);
		if (!token.isPresent())
			return null;
		Account user = token.get().getAccount();
		user.setActive(true); // activate account
		user = accountRepository.save(user);
		return user;
	}
}

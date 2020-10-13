package com.sachinsingh.jobsearch.model;

import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Embeddable;

@Embeddable
public class Message {
	private LocalDateTime timestamp;
	private String text;
	
	public LocalDateTime getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	
	
}

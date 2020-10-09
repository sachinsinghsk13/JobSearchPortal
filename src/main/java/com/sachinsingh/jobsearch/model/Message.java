package com.sachinsingh.jobsearch.model;

import java.util.Date;

import javax.persistence.Embeddable;

@Embeddable
public class Message {
	private Date timestamp;
	private String text;
	
	public Date getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	
	
}

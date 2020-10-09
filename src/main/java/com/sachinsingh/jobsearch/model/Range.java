package com.sachinsingh.jobsearch.model;

public class Range <T> {
	private T lowest;
	private T highest;
	
	public T getLowest() {
		return lowest;
	}
	public void setLowest(T lowest) {
		this.lowest = lowest;
	}
	public T getHighest() {
		return highest;
	}
	public void setHighest(T highest) {
		this.highest = highest;
	}
	
	
}

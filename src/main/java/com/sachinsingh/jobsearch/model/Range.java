package com.sachinsingh.jobsearch.model;

import javax.persistence.Embeddable;

@Embeddable
public class Range {
	private Integer lowest;
	private Integer highest;
	
	
	public Integer getLowest() {
		return lowest;
	}


	public void setLowest(Integer lowest) {
		this.lowest = lowest;
	}


	public Integer getHighest() {
		return highest;
	}


	public void setHighest(Integer highest) {
		this.highest = highest;
	}


	@Override
	public String toString() {
		return "Range [lowest=" + lowest + ", highest=" + highest + "]";
	}
	
}

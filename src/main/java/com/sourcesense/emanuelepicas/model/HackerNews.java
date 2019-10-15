package com.sourcesense.emanuelepicas.model;

import java.util.Date;

public class HackerNews extends News {

	private Date time;

	private String title;

	private String url;

	public Date getTime() {
		return time;
	}

	public void setTime(Long time) {
		// Long newTime = (long) (time + 1546290648000.00);
		Date timeConverted = new java.util.Date(((long) time * 1000));
		this.time = timeConverted;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
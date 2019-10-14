package com.sourcesense.emanuelepicas.model;

import java.util.Comparator;
import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
@JsonDeserialize(as = HackerNews.class)


public abstract class News  {

	private String title;

	private String url;

	private Date created_date;

	private Date time;

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

	public Date getCreated_date() {
		return created_date;
	}

	public void setCreated_date(Date created_date) {
		this.created_date = created_date;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

}

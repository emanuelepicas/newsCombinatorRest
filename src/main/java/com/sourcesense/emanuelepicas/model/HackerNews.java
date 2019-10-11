package com.sourcesense.emanuelepicas.model;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;


public class HackerNews {

	private String by;

	private Integer id;

	private Integer[] kids;

	private Integer score;

	private Date time;

	private String title;

	private String type;

	private String url;

	public String getBy() {
		return by;
	}

	public void setBy(String by) {
		this.by = by;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer[] getKids() {
		return kids;
	}

	public void setKids(Integer[] kids) {
		this.kids = kids;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Long time) {
		Long newTime = (long) (time + 1546290648000.00);
		Date timeConverted=new java.util.Date(((long)time*1000));
		this.time = timeConverted;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	
}
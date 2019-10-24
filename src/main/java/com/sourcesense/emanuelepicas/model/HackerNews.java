package com.sourcesense.emanuelepicas.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)

public class HackerNews extends News {
	@Id
	public Integer id;

	private Date time;
	

	private String title;



	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setTime(Long time) {
		Date timeConverted = new java.util.Date(((long) time * 1000));
		this.time = timeConverted;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public Date getData() {
		return time;
	}

	@Override
	public String getSource() {
		return "Hacker News";
	}

}

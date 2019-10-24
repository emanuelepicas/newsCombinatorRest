package com.sourcesense.emanuelepicas.model;

import java.util.Date;

public class NyTimesArticle extends News {

	private String title;

	private Date created_date;


	public void setTitle(String title) {
		this.title = title;
	}

	public String getTitle() {
		return title;
	}

	public void setCreated_date(Date created_date) {
		this.created_date = created_date;
	}

	@Override
	public Date getData() {
		return created_date;
	}

	@Override
	public String getSource() {
		return "NyTimes";
	}

}

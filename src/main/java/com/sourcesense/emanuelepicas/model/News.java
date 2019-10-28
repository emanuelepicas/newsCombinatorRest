package com.sourcesense.emanuelepicas.model;

import java.util.Date;



public abstract class News {
	

	private String url;
	
	private String source;

	public abstract Date getData();

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

}

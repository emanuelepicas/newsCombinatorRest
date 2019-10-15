package com.sourcesense.emanuelepicas.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@JsonIgnoreProperties(ignoreUnknown = true)
public class NyTimesArticleContainer {
	

	private News[] results;

	public News[] getResults() {
		return results;
	}

	public void setResults(News[] results) {
		this.results = results;
	}

	
}

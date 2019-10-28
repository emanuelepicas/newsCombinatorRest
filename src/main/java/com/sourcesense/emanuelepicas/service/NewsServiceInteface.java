package com.sourcesense.emanuelepicas.service;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;

import com.sourcesense.emanuelepicas.model.News;

public interface NewsServiceInteface {
	
	public List<News> allArticles() throws IOException, InterruptedException, ExecutionException;

}

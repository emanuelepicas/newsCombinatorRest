package com.sourcesense.emanuelepicas.service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.sourcesense.emanuelepicas.model.News;
import com.sourcesense.emanuelepicas.repository.HackerNewsRepository;

@Service 
public class HackerNewsService {
	
	@Autowired
	HackerNewsRepository hackerNewsRepository;
	
	
	public List<News> allOfTheArticlesOfHackerNews() throws JsonParseException, JsonMappingException, MalformedURLException, IOException, InterruptedException, ExecutionException{
		return hackerNewsRepository.printNews();
	}
	
	public List<News> allOfTheArticlesOfHackerNewsWithWebClient() throws JsonParseException, JsonMappingException, MalformedURLException, IOException, InterruptedException, ExecutionException{
		return hackerNewsRepository.printNewsWithWebClient();
	}

}

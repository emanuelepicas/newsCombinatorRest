package com.sourcesense.emanuelepicas.service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.sourcesense.emanuelepicas.model.News;
import com.sourcesense.emanuelepicas.repository.NyTimesRepository;

@Service
public class NyTimesService {
	
	@Autowired
	NyTimesRepository nyTimesRepository;
	
	public List<News> allArticlesOfNyTimes() throws JsonParseException, JsonMappingException, MalformedURLException, IOException{
		return nyTimesRepository.printNews();
		
	}

}

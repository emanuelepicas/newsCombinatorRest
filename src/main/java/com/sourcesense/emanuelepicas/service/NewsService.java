package com.sourcesense.emanuelepicas.service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class NewsService {
	
	@Autowired
	ObjectMapper objectMapper;

	@Value("${allIdUrlOfHackerNews}")
	String hackerNews;

	public void getAllId() throws JsonParseException, JsonMappingException, MalformedURLException, IOException {

		List<Integer> alList = objectMapper.readValue(new URL(hackerNews), List.class);
	}

}

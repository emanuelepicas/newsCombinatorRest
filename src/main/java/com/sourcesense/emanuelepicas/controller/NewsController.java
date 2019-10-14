package com.sourcesense.emanuelepicas.controller;

import java.io.IOException;
import java.net.MalformedURLException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

import org.apache.naming.java.javaURLContextFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.util.ByteArrayBuilder;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sourcesense.emanuelepicas.model.HackerNews;
import com.sourcesense.emanuelepicas.model.News;
import com.sourcesense.emanuelepicas.model.NyTimesArticle;
import com.sourcesense.emanuelepicas.service.NewsService;

@RestController
public class NewsController {

	@Autowired
	NewsService newsService;
	

	@GetMapping(value = "/news")
	@ResponseBody
	public List<News> newsOrdered() throws JsonParseException, JsonMappingException, MalformedURLException, IOException {

		return newsService.printAllNews();

	}

	

	@GetMapping(value = "/news/{source}")
	@ResponseBody
	public List<News> printSourceNews(@PathVariable String source) throws JsonParseException, JsonMappingException, MalformedURLException, IOException{
		if(source.equals("hackerNews")) {
			return  newsService.printAllHackerNews();
			}
		if(source.equals("nyTimesNews")) {
			
			return  newsService.printAllNyTimesNews();
			
		}
		return null;
		
	

	}

	

	
	

}

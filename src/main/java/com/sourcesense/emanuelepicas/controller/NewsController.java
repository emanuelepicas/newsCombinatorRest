package com.sourcesense.emanuelepicas.controller;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.sourcesense.emanuelepicas.model.News;
import com.sourcesense.emanuelepicas.service.HackerNewsService;
import com.sourcesense.emanuelepicas.service.AggregatorNewsService;
import com.sourcesense.emanuelepicas.service.NyTimesService;

@RestController
public class NewsController {

	@Autowired
	AggregatorNewsService newsService;
	
	@Autowired
	HackerNewsService hackNewsService;
	
	@Autowired
	NyTimesService nyTimesService;
	
	public enum NewsType {
		hackerNews,nyTimesNews;
		
	}
	

	@GetMapping(value = "/news")
	@ResponseBody
	public List<News> allNewsOrdered() throws JsonParseException, JsonMappingException, MalformedURLException, IOException {

		return newsService.allArticlesOfTheSources();

	}

	

	@GetMapping(value = "/news/{source}")
	@ResponseBody
	public List<News> printSourceNews(@PathVariable String source) throws JsonParseException, JsonMappingException, MalformedURLException, IOException{
		if(source.equals(NewsType.hackerNews.toString())) {
			return  hackNewsService.allOfTheArticlesOfHackerNews();
			}
		if(source.equals(NewsType.nyTimesNews.toString())) {
			
			return  nyTimesService.allArticlesOfNyTimes();
			
		}
		return null;
		
	

	}

	

	
	

}

package com.sourcesense.emanuelepicas.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.sourcesense.emanuelepicas.model.News;
import com.sourcesense.emanuelepicas.model.NewsType;
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
	

	@GetMapping(value = "/news")
	@ResponseBody
	public List<News> allNewsOrdered() throws IOException, InterruptedException, ExecutionException {

		return newsService.allArticles();

	}
	@GetMapping(value = "/news/getFromDb")
	@ResponseBody
	public List<News> allNewsFromDb() {

		return newsService.getFromDb();

	}

	

	@GetMapping(value = "/news/{source}")
	@ResponseBody
	public List<News> printSourceNews(@PathVariable String source) throws  IOException, InterruptedException, ExecutionException{
		if(source.equals(NewsType.hackerNews.toString())) {
			
			
			return  new ArrayList<>(hackNewsService.allArticles());
			}
		if(source.equals(NewsType.webClient.toString())) {
			return new ArrayList<>(hackNewsService.allOfTheArticlesOfHackerNewsWithWebClient());
		}
		if(source.equals(NewsType.nyTimesNews.toString())) {
			
			return  new ArrayList<>(nyTimesService.allArticles());
			
		}
		return Collections.emptyList();
		
	

	}

	

	
	

}

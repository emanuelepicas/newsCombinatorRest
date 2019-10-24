package com.sourcesense.emanuelepicas.service;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sourcesense.emanuelepicas.model.News;
import com.sourcesense.emanuelepicas.repositories.NewsRepository;

@Service
public class AggregatorNewsService implements NewsServiceInteface {
	
	@Autowired
	NyTimesService nyTimesService;
	
	@Autowired
	HackerNewsService hackerNewsService;
	
	@Autowired
	NewsRepository newsRepository;
	

	@Override
	public List<News> allArticles() throws IOException, InterruptedException, ExecutionException {

		return printNews();
	}

	public List<News> printNews() throws InterruptedException, ExecutionException {
		List<News> allHackerNews = hackerNewsService.printNews();
		List<News> allNews = nyTimesService.printNews();
		CompareDateNews cmp = new CompareDateNews();
		allHackerNews.addAll(allNews);
		Collections.sort(allHackerNews, cmp);
		return allHackerNews;

	}
	
	public List<News> getFromDb(){
		List<News> list= newsRepository.findAll();
		
		return list;
	}
}
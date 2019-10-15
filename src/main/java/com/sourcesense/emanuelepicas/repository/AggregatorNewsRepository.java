package com.sourcesense.emanuelepicas.repository;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.sourcesense.emanuelepicas.model.News;
import com.sourcesense.emanuelepicas.service.CompareDateNews;

@Repository
public class AggregatorNewsRepository implements NewsRepository {

	@Autowired
	NyTimesRepository nyTimesRepository;

	@Autowired
	HackerNewsRepository hackerNewsRepository;


	@Override
	public List<News> printNews() throws JsonParseException, JsonMappingException, MalformedURLException, IOException {
		List<News> allHackerNews = nyTimesRepository.printNews();
		List<News> allNews = hackerNewsRepository.printNews();
		CompareDateNews cmp = new CompareDateNews();
		allNews.addAll(allHackerNews);
		Collections.sort(allNews, cmp);
		return allHackerNews;

	}


}

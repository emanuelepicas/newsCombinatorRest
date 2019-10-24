package com.sourcesense.emanuelepicas.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.sourcesense.emanuelepicas.model.News;
import com.sourcesense.emanuelepicas.model.NyTimesArticleContainer;

@Service
public class NyTimesService implements NewsServiceInteface {
	private static final Logger logger = LoggerFactory.getLogger(HackerNewsService.class);

	@Value("${nyTimesNews}")
	String nyTimesNews;

	

	@Override
	public List<News> allArticles() throws IOException {
		logger.info("creation of NyTimes list");
		return printNews();

	}

	public List<News> printNews() {
		List<News> allNyTimesArticles = new ArrayList<>();
		News[] allArticles = allTheArticlesOFNyTimes().getResults();
		Collections.addAll(allNyTimesArticles, allArticles);
		CompareDateNews cmp = new CompareDateNews();
		Collections.sort(allNyTimesArticles, cmp);
				

		
		
		return allNyTimesArticles;
	}

	public NyTimesArticleContainer allTheArticlesOFNyTimes() {
		NyTimesArticleContainer allNyTimesArticlesContainers;
		RestTemplate restTemplate = new RestTemplate();
		allNyTimesArticlesContainers = restTemplate.getForObject(nyTimesNews, NyTimesArticleContainer.class);

		return allNyTimesArticlesContainers;

	}

}

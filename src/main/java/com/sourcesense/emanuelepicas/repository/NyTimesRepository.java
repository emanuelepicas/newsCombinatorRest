package com.sourcesense.emanuelepicas.repository;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.sourcesense.emanuelepicas.model.News;
import com.sourcesense.emanuelepicas.model.NyTimesArticleContainer;
import com.sourcesense.emanuelepicas.service.CompareDateNews;

@Repository
public class NyTimesRepository implements NewsRepository {
	@Value("${nyTimesNews}")
	String nyTimesNews;

	@Override
	public List<News> printNews() throws JsonParseException, JsonMappingException, MalformedURLException, IOException {
		List<News> allNyTimesArticles = new ArrayList<>();
		News[] allArticles = allTheArticlesOFNyTimes().getResults();

		for (News a : allArticles) {
			allNyTimesArticles.add(a);
		}
		CompareDateNews cmp = new CompareDateNews();
		Collections.sort(allNyTimesArticles, cmp);

		return allNyTimesArticles;
	}

	public NyTimesArticleContainer allTheArticlesOFNyTimes()
			throws JsonParseException, JsonMappingException, MalformedURLException, IOException {
		NyTimesArticleContainer allNyTimesArticlesContainers = new NyTimesArticleContainer();
		RestTemplate restTemplate = new RestTemplate();
		allNyTimesArticlesContainers = restTemplate.getForObject(nyTimesNews, NyTimesArticleContainer.class);

		return allNyTimesArticlesContainers;

	}

}

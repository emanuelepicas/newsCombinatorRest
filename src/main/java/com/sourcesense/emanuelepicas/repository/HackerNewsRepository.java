package com.sourcesense.emanuelepicas.repository;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.sourcesense.emanuelepicas.model.HackerNews;
import com.sourcesense.emanuelepicas.model.News;
import com.sourcesense.emanuelepicas.service.CompareDateNews;

@Repository
public class HackerNewsRepository implements NewsRepository {

	@Value("${UrlofHakerNewsPart1}")
	String hackerNewsUrlpart1;

	@Value("${UrlofHackerNewsPart2}")
	String hackerNewsUrlpart2;

	@Value("${allIdUrlOfHackerNews}")
	String hackerNews;

	@SuppressWarnings("unchecked")
	public List<Integer> readAllId()
			throws JsonParseException, JsonMappingException, MalformedURLException, IOException {

		RestTemplate restTemplate = new RestTemplate();

		return restTemplate.getForObject(hackerNews, List.class);
	}

	@Override
	public List<News> printNews() throws JsonParseException, JsonMappingException, MalformedURLException, IOException {
		List<News> allHackerNews = allTheArticlesOfASource();
		CompareDateNews cmp = new CompareDateNews();
		Collections.sort(allHackerNews, cmp);
		return allHackerNews;
	}

	public List<News> allTheArticlesOfASource()
			throws JsonParseException, JsonMappingException, MalformedURLException, IOException {
		List<Integer> list = readAllId();
		List<News> allHackerNewsList = new ArrayList<>();

		Stream<Integer> stream = list.parallelStream();
		RestTemplate restTemplate = new RestTemplate();

		stream.forEach(p -> allHackerNewsList
				.add(restTemplate.getForObject(hackerNewsUrlpart1 + p + hackerNewsUrlpart2, HackerNews.class)));

		return allHackerNewsList;
	}

}

package com.sourcesense.emanuelepicas.service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.ListIterator;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sourcesense.emanuelepicas.model.HackerNews;

@Service
public class NewsService {

	@Autowired
	ObjectMapper objectMapper;

	@Value("${allIdUrlOfHackerNews}")
	String hackerNews;

	@Value("${test}")
	String test;

	@Value("${singleIdUrlofHakerNewsPart1}")
	String part1;

	@Value("${singleIdUrlofHackerNewsPart2}")
	String part2;

	public List<Integer> getAllId()
			throws JsonParseException, JsonMappingException, MalformedURLException, IOException {
		List<Integer> alList = objectMapper.readValue(new URL(hackerNews), List.class);

		return alList;
	}

	public String test() throws JsonParseException, JsonMappingException, MalformedURLException, IOException {

		HackerNews hackerNews = objectMapper.readValue(new URL(test), HackerNews.class);

		String hackerNewsString = objectMapper.writeValueAsString(hackerNews);

		return hackerNewsString;

	}

	/*
	 * public List<HackerNews> getAllTheArticleOfHackerNews(){ List<HackerNews>
	 * allHackerNews = new ArrayList<>();
	 * 
	 * IntStream stream=IntStream.rangeClosed(0, alList.size()).forEach(x) ->{
	 * 
	 * });
	 * 
	 * 
	 * }
	 */

}

package com.sourcesense.emanuelepicas.service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sourcesense.emanuelepicas.model.HackerNews;
import com.sourcesense.emanuelepicas.model.News;
import com.sourcesense.emanuelepicas.model.NyTimesArticleContainer;

@Service
public class NewsService {

	@Autowired
	ObjectMapper objectMapper;

	@Autowired
	CompareDateNews cmp;

	@Value("${allIdUrlOfHackerNews}")
	String hackerNews;

	@Value("${UrlofHakerNewsPart1}")
	String hackerNewsUrlpart1;

	@Value("${UrlofHackerNewsPart2}")
	String hackerNewsUrlpart2;

	@Value("${nyTimesNews}")
	String nyTimesNews;

	public List<News> printAllNews()
			throws JsonParseException, JsonMappingException, MalformedURLException, IOException {

		List<News> allHackerNews = getAllTheArticleOfHackerNews();
		List<News> allNews = printAllNyTimesNews();
		CompareDateNews cmp = new CompareDateNews();
		allNews.addAll(allHackerNews);
		Collections.sort(allNews, cmp);

		return allNews;

	}

	public List<News> printAllHackerNews()
			throws JsonParseException, JsonMappingException, MalformedURLException, IOException {

		List<News> allHackerNews = getAllTheArticleOfHackerNews();
		Collections.sort(allHackerNews, cmp);
		return allHackerNews;
	}

	public List<News> printAllNyTimesNews()
			throws JsonParseException, JsonMappingException, MalformedURLException, IOException {

		List<News> allNyTimesArticles = new ArrayList<>();
		News[] allArticles = getAllTheArticleOFNyTimesArticles().getResults();

		for (News a : allArticles) {
			allNyTimesArticles.add(a);
		}

		Collections.sort(allNyTimesArticles, cmp);

		return allNyTimesArticles;
	}

	public List<Integer> getAllId()
			throws JsonParseException, JsonMappingException, MalformedURLException, IOException {

		List<Integer> alList = readAllTheId();

		return alList;
	}

	private List<Integer> readAllTheId()
			throws IOException, JsonParseException, JsonMappingException, MalformedURLException {

		return objectMapper.readValue(new URL(hackerNews), List.class);
	}

	public List<News> getAllTheArticleOfHackerNews()
			throws JsonParseException, JsonMappingException, MalformedURLException, IOException {
		List<Integer> list = getAllId();
		List<News> allHackerNewsList = new ArrayList<>();

		Stream<Integer> stream = list.stream();

		stream.forEach(p -> {
			try {
				allHackerNewsList.add(
						objectMapper.readValue(new URL(hackerNewsUrlpart1 + p + hackerNewsUrlpart2), HackerNews.class));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});

		return allHackerNewsList;

	}

	public NyTimesArticleContainer getAllTheArticleOFNyTimesArticles()
			throws JsonParseException, JsonMappingException, MalformedURLException, IOException {
		NyTimesArticleContainer allNyTimesArticlesContainers = new NyTimesArticleContainer();
		allNyTimesArticlesContainers = objectMapper.readValue(new URL(nyTimesNews), NyTimesArticleContainer.class);

		return allNyTimesArticlesContainers;

	}

}
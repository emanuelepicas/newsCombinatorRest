package com.sourcesense.emanuelepicas.repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import com.sourcesense.emanuelepicas.model.News;
import com.sourcesense.emanuelepicas.service.CompareDateNews;

import reactor.core.publisher.Flux;

@Repository
public class HackerNewsRepository implements NewsRepository {
	private static final Logger logger = LoggerFactory.getLogger(HackerNewsRepository.class);

	@Value("${UrlofHakerNewsPart1}")
	String hackerNewsUrlpart1;

	@Value("${UrlofHackerNewsPart2}")
	String hackerNewsUrlpart2;

	@Value("${allIdUrlOfHackerNews}")
	String hackerNews;

	@Value("${hackerNews}")
	String baseUrl;

	@Value("${idHackerNews}")
	String idHackerNews;

	private WebClient webClient = WebClient.create(baseUrl);

	public Flux<Integer> readAllIdWithWebClient() {
		return webClient.get().uri(hackerNews).retrieve().bodyToFlux(Integer.class);
	}

	public List<Integer> listAllIdWithWebClient() {
		List<Integer> list = new ArrayList<>();
		list.addAll(readAllIdWithWebClient().collectList().block());

		return list;

	}

	@SuppressWarnings("unchecked")
	public List<Integer> readAllId() {

		RestTemplate restTemplate = new RestTemplate();

		return restTemplate.getForObject(hackerNews, List.class);
	}

	@Override
	public List<News> printNews() throws IOException, InterruptedException, ExecutionException {
		List<News> allHackerNews = null;

		logger.info("creation of hacker News List");
		allHackerNews = allTheArticlesOfASource();

		CompareDateNews cmp = new CompareDateNews();
		Collections.sort(allHackerNews, cmp);
		return allHackerNews;
	}
	
	public List<News> printNewsWithWebClient() throws IOException, InterruptedException, ExecutionException {
		List<News> allHackerNews = null;

		logger.info("creation of hacker News List");
		allHackerNews = allTheArticlesOfASourceWithWebClient();

		CompareDateNews cmp = new CompareDateNews();
		Collections.sort(allHackerNews, cmp);
		return allHackerNews;
	}

	public List<News> allOfTheArticleOfASourceWithWebClient() {
		Flux<News> allHackerNews = webClient.get().uri(hackerNewsUrlpart1 + "c" + hackerNewsUrlpart2).retrieve()
				.bodyToFlux(News.class);

		return allHackerNews.collectList().block();
	}
	public List<News> allTheArticlesOfASourceWithWebClient() throws InterruptedException, ExecutionException {
		List<Integer> list = listAllIdWithWebClient();
		List<News> allHackerNewsList;
		ForkJoinPool customThreadPool = new ForkJoinPool(20);

		RestTemplate restTemplate = new RestTemplate();

		allHackerNewsList = customThreadPool.submit(() -> list.parallelStream()
				.map(p -> restTemplate.getForObject(hackerNewsUrlpart1 + p + hackerNewsUrlpart2, News.class))
				.collect(Collectors.toList())).get();

		return allHackerNewsList;
	}

	public List<News> allTheArticlesOfASource() throws InterruptedException, ExecutionException {
		List<Integer> list = readAllId();
		List<News> allHackerNewsList;
		ForkJoinPool customThreadPool = new ForkJoinPool(20);

		RestTemplate restTemplate = new RestTemplate();

		allHackerNewsList = customThreadPool.submit(() -> list.parallelStream()
				.map(p -> restTemplate.getForObject(hackerNewsUrlpart1 + p + hackerNewsUrlpart2, News.class))
				.collect(Collectors.toList())).get();

		return allHackerNewsList;
	}

	public List<News> allTheArticlesOfASourceWithFlux() {
		List<Integer> list = listAllIdWithWebClient();
		List<News> allHackerNewsList = new ArrayList<>();
		RestTemplate restTemplate = new RestTemplate();
		Flux.fromIterable(list).log().parallel()
				.map(p -> allHackerNewsList
						.add(restTemplate.getForObject(hackerNewsUrlpart1 + p + hackerNewsUrlpart2, News.class)))
				.subscribe();

		return allHackerNewsList;

	}
}

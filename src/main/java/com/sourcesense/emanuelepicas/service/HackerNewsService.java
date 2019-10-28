package com.sourcesense.emanuelepicas.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import com.sourcesense.emanuelepicas.model.HackerNews;
import com.sourcesense.emanuelepicas.model.News;
import com.sourcesense.emanuelepicas.repositories.HackerNewsRepository;
import com.sourcesense.emanuelepicas.repositories.NewsRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class HackerNewsService implements NewsServiceInteface {
	private static final Logger logger = LoggerFactory.getLogger(HackerNewsService.class);

	@Value("${UrlofHakerNewsPart1}")
	private String hackerNewsUrlpart1;

	@Value("${UrlofHackerNewsPart2}")
	private String hackerNewsUrlpart2;

	@Value("${allIdUrlOfHackerNews}")
	private String hackerNews;

	@Value("${localHost}")
	private String localHost;

	@Value("${idHackerNews}")
	private String idHackerNews;

	@Autowired
	private NewsRepository newsRepository;

	@Autowired
	private HackerNewsRepository hackerNewsRepository;

	@Override
	public List<News> allArticles() throws IOException, InterruptedException, ExecutionException {
		return new ArrayList<>(printNews());
	}

	public List<News> allOfTheArticlesOfHackerNewsWithWebClient() {
		return printNewsWithWebClient();
	}

	@SuppressWarnings("unchecked")
	public List<Integer> readAllId() {

		RestTemplate restTemplate = new RestTemplate();

		return restTemplate.getForObject(hackerNews, List.class);
	}

	public List<News> printNews() throws InterruptedException, ExecutionException {
		List<HackerNews> allHackerNews;

		logger.info("creation of hacker News List");
		allHackerNews = allTheArticlesOfASource();

		CompareDateNews cmp = new CompareDateNews();
		Collections.sort(allHackerNews, cmp);
		try {
			newsRepository.saveAll(allHackerNews);

		} catch (Exception e) {
			logger.info("DuplicateKeyException catch");
		}

		return new ArrayList<>(allHackerNews);
	}

	public List<HackerNews> allTheArticlesOfASource() throws InterruptedException, ExecutionException {
		List<Integer> list = readAllId();
		List<HackerNews> allHackerNewsList;

		ForkJoinPool customThreadPool = new ForkJoinPool(20);

		allHackerNewsList = customThreadPool.submit(() -> list.parallelStream().map(p ->

		controlfromDb(p)).collect(Collectors.toList())).get();

		return allHackerNewsList;
	}

	public HackerNews controlfromDb(Integer p) {
		RestTemplate restTemplate = new RestTemplate();

		Optional<HackerNews> value = hackerNewsRepository.findById(p);

		if (value.isPresent()) {
			return value.get();
		} else {
			return restTemplate.getForObject(hackerNewsUrlpart1 + p + hackerNewsUrlpart2, HackerNews.class);
		}
	}

	// WEB CLIENT

	private WebClient webClient = WebClient.create(localHost);

	public Flux<Integer> readAllIdWithWebClient() {
		return webClient.get().uri(hackerNews).retrieve().bodyToFlux(Integer.class);
	}

	public List<Integer> listAllIdWithWebClient() {
		List<Integer> list = new ArrayList<>();
		list.addAll(readAllIdWithWebClient().collectList().block());

		return list;

	}

	public List<News> printNewsWithWebClient() {
		List<News> allHackerNews = null;

		logger.info("creation of hacker News List");
		allHackerNews = articles();
		return allHackerNews;
	}

	public Mono<HackerNews> singleArticle(Integer id) {
		return webClient.get().uri(hackerNewsUrlpart1 + id + ".json").retrieve().bodyToMono(HackerNews.class);
	}

	public List<News> articles() {
		CompareDateNews cmp = new CompareDateNews();
		return new ArrayList<>(Flux.fromIterable(listAllIdWithWebClient()).parallel().flatMap(this::singleArticle)
				.collectSortedList(cmp).block());

	}
}

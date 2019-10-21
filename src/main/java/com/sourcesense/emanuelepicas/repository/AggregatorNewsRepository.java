package com.sourcesense.emanuelepicas.repository;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.sourcesense.emanuelepicas.model.News;
import com.sourcesense.emanuelepicas.service.CompareDateNews;

@Repository
public class AggregatorNewsRepository implements NewsRepository {

	@Autowired
	NyTimesRepository nyTimesRepository;

	@Autowired
	HackerNewsRepository hackerNewsRepository;


	@Override
	public List<News> printNews() throws  IOException, InterruptedException, ExecutionException {
		List<News> allHackerNews = hackerNewsRepository.printNews();
		List<News> allNews = nyTimesRepository.printNews();
		CompareDateNews cmp = new CompareDateNews();
		allNews.addAll(allHackerNews);
		Collections.sort(allNews, cmp);
		return allHackerNews;

	}


}

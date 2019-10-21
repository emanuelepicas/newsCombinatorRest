package com.sourcesense.emanuelepicas.service;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sourcesense.emanuelepicas.model.News;
import com.sourcesense.emanuelepicas.repository.AggregatorNewsRepository;

@Service
public class AggregatorNewsService {
	@Autowired
	AggregatorNewsRepository aggregatorNewsRepository;

	public List<News> allArticlesOfTheSources()
			throws  IOException, InterruptedException, ExecutionException {

		return aggregatorNewsRepository.printNews();
	}
}
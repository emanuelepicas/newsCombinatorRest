package com.sourcesense.emanuelepicas.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.sourcesense.emanuelepicas.model.News;


public interface NewsRepository extends MongoRepository<News, Integer> {
	

	}

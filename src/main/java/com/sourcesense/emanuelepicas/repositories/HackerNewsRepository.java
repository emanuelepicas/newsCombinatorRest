package com.sourcesense.emanuelepicas.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.sourcesense.emanuelepicas.model.HackerNews;

public interface HackerNewsRepository extends MongoRepository<HackerNews, Integer>{
	

}

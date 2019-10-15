package com.sourcesense.emanuelepicas.repository;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.sourcesense.emanuelepicas.model.News;
@Repository
public interface NewsRepository {
	
	
	public List<News> printNews() throws JsonParseException, JsonMappingException, MalformedURLException, IOException;
	
	
	

}

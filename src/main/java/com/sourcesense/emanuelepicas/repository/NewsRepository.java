package com.sourcesense.emanuelepicas.repository;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.stereotype.Repository;
import com.sourcesense.emanuelepicas.model.News;
@Repository
public interface NewsRepository {
	
	
	public List<News> printNews() throws  IOException, InterruptedException, ExecutionException;
	
	
	

}

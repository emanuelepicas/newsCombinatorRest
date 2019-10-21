package com.sourcesense.emanuelepicas.repository;

import static org.junit.Assert.*;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.sourcesense.emanuelepicas.model.News;
@RunWith(SpringRunner.class)
@WebMvcTest(HackerNewsRepository.class)
public class HackerNewsRepositoryTest {
	
	
    @MockBean
	private HackerNewsRepository hackerNewsRepository;
    
    @Before
	public void init() throws IOException, InterruptedException, ExecutionException {
		Mockito.when(hackerNewsRepository.printNews()).thenReturn(new ArrayList<News>());
	}


	@Test
	public void NewsEmpty() throws JsonParseException, JsonMappingException, MalformedURLException, IOException, InterruptedException, ExecutionException {
		assertTrue(hackerNewsRepository.printNews().isEmpty());
	}

}

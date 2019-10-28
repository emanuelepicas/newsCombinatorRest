package com.sourcesense.emanuelepicas.service;

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
@RunWith(SpringRunner.class)
@WebMvcTest(HackerNewsService.class)
public class HackerNewsServiceTest {

	@MockBean
	private HackerNewsService hackerNewsService;

	@Before
	public void init() throws IOException, InterruptedException, ExecutionException {
		Mockito.when(hackerNewsService.printNews()).thenReturn(new ArrayList<>());
	}

	@Test
	public void NewsEmpty() throws JsonParseException, JsonMappingException, MalformedURLException, IOException,
			InterruptedException, ExecutionException {
		assertTrue(hackerNewsService.printNews().isEmpty());
	}

}

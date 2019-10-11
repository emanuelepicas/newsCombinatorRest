package com.sourcesense.emanuelepicas.controller;

import java.io.IOException;
import java.net.MalformedURLException;
import java.time.Instant;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.sourcesense.emanuelepicas.model.HackerNews;
import com.sourcesense.emanuelepicas.service.NewsService;

@RestController
public class NewsController {

	@Autowired
	NewsService newsService;

	@GetMapping(value = "/news")
	@ResponseBody
	public void newsOrdered() throws JsonParseException, JsonMappingException, MalformedURLException, IOException {

		List<Integer> aliList = newsService.getAllId();

		// TODO

	}

	@GetMapping(value = "/test", produces = "text/json")
	@ResponseBody
	public String test() throws JsonParseException, JsonMappingException, MalformedURLException, IOException {

		String test = newsService.test();

		return test;

	}

	@GetMapping("/")
	@ResponseBody
	public String te() {
		Date date = Date.from(Instant.ofEpochSecond(21204007));

		return date.toString();

	}
}

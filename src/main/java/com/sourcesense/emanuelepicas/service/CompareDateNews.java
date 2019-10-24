package com.sourcesense.emanuelepicas.service;

import java.util.Comparator;

import org.springframework.stereotype.Component;

import com.sourcesense.emanuelepicas.model.News;
@Component
public class CompareDateNews implements Comparator<News> {

	@Override
	public int compare(News o1, News o2) {
	return o1.getData().compareTo(o2.getData());

}
}

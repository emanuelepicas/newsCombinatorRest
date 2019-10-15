package com.sourcesense.emanuelepicas.service;

import java.util.Comparator;

import org.springframework.stereotype.Component;

import com.sourcesense.emanuelepicas.model.News;
@Component
public class CompareDateNews implements Comparator<News> {

	@Override
	public int compare(News o1, News o2) {
		if (o1.getTime() == null && o2.getTime() != null)
			return o1.getCreated_date().compareTo(o2.getTime());
		
		else if (o1.getCreated_date() == null && o2.getCreated_date() != null) {
			return o1.getTime().compareTo(o2.getCreated_date());
			
		} else if (o1.getCreated_date() == null && o2.getCreated_date() == null) {
			return o1.getTime().compareTo(o2.getTime());
		}
		return o1.getCreated_date().compareTo(o2.getCreated_date());

	}

}

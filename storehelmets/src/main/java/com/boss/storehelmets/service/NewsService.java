package com.boss.storehelmets.service;

import java.util.List;
import java.util.Optional;

import com.boss.storehelmets.model.News;

public interface NewsService {
	public List<News> getNewsAll();
	
	public Optional<News> getNewsById(String id);
	
	public List<News> getNewsPost();
}

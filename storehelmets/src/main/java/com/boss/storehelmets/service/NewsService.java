package com.boss.storehelmets.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.boss.storehelmets.model.News;
import com.boss.storehelmets.model.NewsImage;
import com.boss.storehelmets.model.User;

public interface NewsService {
	public List<News> getNewsAll();
	
	public Optional<News> getNewsById(String id);
	
	public List<News> getNewsPost();
	
	
	public String deleteNews(String id);
	
	public String updateNews(News news);

	public	String addNews(News newsInput);
}

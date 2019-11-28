package com.boss.storehelmets.service;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.boss.storehelmets.model.News;
import com.boss.storehelmets.repository.NewsRepository;

@Service
public class NewsServiceImlp implements NewsService{
	@Autowired
	NewsRepository newsRepository;
	
	
	@Override
	public List<News> getNewsAll() {
		// TODO Auto-generated method stub
		try {
			List<News> listNews = newsRepository.findAll(); 
			return listNews;
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}

	@Override
	public Optional<News> getNewsById(String id) {
		// TODO Auto-generated method stub
		Optional<News> optionalNews = newsRepository.findById(id);
		if (!optionalNews.isPresent()) {
			return null;
		}
		return optionalNews;
	}

	@Override
	public List<News> getNewsPost() {
		// TODO Auto-generated method stub
		return newsRepository.findByOrderByDateCreatedDesc();
	}

	

}

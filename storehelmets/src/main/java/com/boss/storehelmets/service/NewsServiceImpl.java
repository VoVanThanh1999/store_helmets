package com.boss.storehelmets.service;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.boss.storehelmets.model.News;
import com.boss.storehelmets.model.User;
import com.boss.storehelmets.repository.NewsRepository;

@Service
public class NewsServiceImpl implements NewsService{
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

	@Override
	public String addNews(News newsInput, User user) {
		// TODO Auto-generated method stub
		News news = new News();
		news.setCategoryDetails(newsInput.getCategoryDetails());
		news.setTitle(news.getTitle());
		news.setNewsDetails(newsInput.getNewsDetails());
		news.setUser(user);
		return null;
	}

	@Override
	public String deleteNews(String id) {
		// TODO Auto-generated method stub
		Optional<News> optional = newsRepository.findById(id);
		if (optional.isPresent()) {
			newsRepository.delete(optional.get());
		}
		return null;
	}

	@Override
	public String updateNews(News newsInput, User user) {
		// TODO Auto-generated method stub
		Optional<News> optional = newsRepository.findById(newsInput.getIdNews());
		optional.ifPresent(news ->{
			news.setNewsDetails(newsInput.getNewsDetails());
			news.setTitle(newsInput.getTitle());
			news.setIdUserUpdate(newsInput.getUser().getIdUser());
			news.setCategoryDetails(newsInput.getCategoryDetails());
		});
		newsRepository.save(optional.get());
		return null;
	}



	

}

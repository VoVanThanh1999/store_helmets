package com.boss.storehelmets.user.resources;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.boss.storehelmets.model.News;
import com.boss.storehelmets.service.NewsService;

@Controller
@RestController
@RequestMapping(value = "/api/v1/user")
public class RestApiNewsController {
	
	@Autowired
	NewsService newsService;
	
	@RequestMapping(value = "/news",method = RequestMethod.GET)
	public List<News> loadAll(){
		try {
			List<News> news = newsService.getNewsAll();
			return news;
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}
	
	@RequestMapping(value = "/news/{id}",method = RequestMethod.GET)
	public Optional<News> loadById(String id) {
		try {
			Optional<News> news = newsService.getNewsById(id);
			return news;
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}
	
	@RequestMapping(value = "/news/newpost",method = RequestMethod.GET)
	public List<News> loadByNewsPost(){
		try {
			List<News> news = newsService.getNewsAll();
			return news;
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}
}

package com.boss.storehelmets.admin.resources;
import java.io.File;
import java.io.FileInputStream;
import java.util.Date;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import com.boss.storehelmets.app.utils.AppConstants;
import com.boss.storehelmets.dto.MockMultipartFile;
import com.boss.storehelmets.model.News;
import com.boss.storehelmets.model.NewsDetails;
import com.boss.storehelmets.model.NewsImage;
import com.boss.storehelmets.model.User;
import com.boss.storehelmets.repository.UserRepository;
import com.boss.storehelmets.securityjwt.JwtAuthenticationFilter;
import com.boss.storehelmets.securityjwt.JwtTokenProvider;
import com.boss.storehelmets.service.FileStorageService;
import com.boss.storehelmets.service.NewsService;
import com.boss.storehelmets.service.UserDetailServiceImpl;
import com.boss.storehelmets.service.UserSevice;

@RestController
@Controller
@RequestMapping(value = "/api/v1/admins")
public class RestApiAdminNewsController {
	
	@Autowired
	private NewsService newsService;
	
	@Autowired
	UserDetailServiceImpl userDetailsServiceImlp;
	
	@Autowired
	UserSevice userSevice;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	JwtTokenProvider tokenProvider;
	
	@Autowired
	JwtAuthenticationFilter authenticationFilter;
	
	@Autowired
	FileStorageService fileStorageService;
	
	@RequestMapping(value = "/news",method = RequestMethod.POST)
	public String creatNews(@RequestBody News newsInput,HttpServletRequest request) {
		try {
			String jwt = authenticationFilter.getJwtFromRequest(request);
			String userId = tokenProvider.getUserIdFromJWT(jwt);
			Optional<User> user  = userSevice.findUserById(userId);
			if (newsInput != null && user != null) {
				News news = new News();
				Date date1 = new Date();
				java.sql.Date date = new java.sql.Date(date1.getYear(), date1.getMonth(), date1.getDate());
				
				NewsDetails newsDetails = newsInput.getNewsDetails();
				Set<NewsImage> newsImages = new HashSet<NewsImage>();
				for (NewsImage image : newsInput.getNewsDetails().getImages()) {
					NewsImage newsImage = new  NewsImage();
					File file = new File(AppConstants.TEMP_DIR+image.getImage());
					FileInputStream input = new FileInputStream(file);
					MultipartFile multipartFile = new MockMultipartFile("file",file.getName(),
													"text/plain", IOUtils.toByteArray(input));
					String fileName = fileStorageService.storeFile(multipartFile);
					String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path(AppConstants.DOWNLOAD_PATH+AppConstants.RESOURCES_IMAGE+"/")
							.path(fileName).toUriString();	
					newsImage.setImage(fileDownloadUri);
					newsImages.add(newsImage);
				}
				news.setTitle(newsInput.getTitle());
				newsDetails.setImages(newsImages);
				news.setNewsDetails(newsDetails);
				news.setCategoryDetails(newsInput.getCategoryDetails());
				news.setUser(user.get());
				newsService.addNews(news);
			}
			return AppConstants.SUCCESS_CREATE;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
	
	
	@RequestMapping(value = "/news",method = RequestMethod.PUT)
	public String updateNews(@RequestBody News newsInput,HttpServletRequest request) {
		String jwt = authenticationFilter.getJwtFromRequest(request);
		String userId = tokenProvider.getUserIdFromJWT(jwt);
		Optional<User> user  = userSevice.findUserById(userId);
		if (user.get() != null && newsInput != null) {
			Optional<News> news = newsService.getNewsById(newsInput.getIdNews());
			news.get().setIdUserUpdate(newsInput.getIdNews());
			news.get().setCategoryDetails(newsInput.getCategoryDetails());
			news.get().setNewsDetails(newsInput.getNewsDetails());
			news.get().setTitle(newsInput.getTitle());
			newsService.updateNews(news.get());
			return AppConstants.SUCCESS_UPDATE;
		}
		return null;
	}
	
	@RequestMapping(value = "/news/{id}",method = RequestMethod.DELETE)
	public String deleteNews(@PathVariable("id")String id) {
		try {
			newsService.deleteNews(id);
			return AppConstants.SUCCESS_DELETE ;
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println(e.getMessage());
		}
		return AppConstants.ERROR_DELETE;
	}
}

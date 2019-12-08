package com.boss.storehelmets.admin.resources;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.boss.storehelmets.model.Category;
import com.boss.storehelmets.model.CategoryDetails;
import com.boss.storehelmets.model.User;
import com.boss.storehelmets.repository.CategoryDetailsRepository;
import com.boss.storehelmets.repository.UserRepository;
import com.boss.storehelmets.securityjwt.JwtAuthenticationFilter;
import com.boss.storehelmets.securityjwt.JwtTokenProvider;
import com.boss.storehelmets.service.CategoryService;
import com.boss.storehelmets.service.UserDetailServiceImlp;
import com.boss.storehelmets.service.UserSevice;

@Controller
@RestController
@RequestMapping(value = "/api/v1/admin")
public class RestApiCategoryAdminController {
	
	@Autowired
	UserDetailServiceImlp userDetailsServiceImlp;
	
	@Autowired
	UserSevice userSevice;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	JwtTokenProvider tokenProvider;
	
	@Autowired
	JwtAuthenticationFilter authenticationFilter;
	
	@Autowired
	CategoryService categoryService;
	
	@Autowired 
	CategoryDetailsRepository categoryDetailsRepository;
	
	@RequestMapping(value = "/categorys",method = RequestMethod.POST)
	public String addCategory(@RequestBody Category categoryInput,HttpServletRequest request) {
		try {
			Category category = categoryInput;
			String jwt = authenticationFilter.getJwtFromRequest(request);
			String userId = tokenProvider.getUserIdFromJWT(jwt);
			Optional<User> user  = userSevice.findUserById(userId);
			if (category.getNameCategory() != null && user.isPresent()) {
				categoryService.addCategory(categoryInput, user.get());
				return "add thanh cong";
			}

		} catch (Exception e) {
			// TODO: handle exception
			System.err.println(e.getMessage());
			return null;
		}
		return null;
	}
	
	@RequestMapping(value = "/categorys/{id}",method = RequestMethod.DELETE)
	public String deleteCategory(@PathVariable("id") String id) {
		try {
			categoryService.deleteCategory(id);
			return "delete thanh cong";
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println(e.getMessage());
			return null;
		}
	}
	
	@RequestMapping(value = "/categorys",method = RequestMethod.PUT)
	public String updateCategory(@RequestBody Category categoryInput,HttpServletRequest request) {
		try {
			Category category = categoryInput;
			String jwt = authenticationFilter.getJwtFromRequest(request);
			String userId = tokenProvider.getUserIdFromJWT(jwt);
			Optional<User> user  = userSevice.findUserById(userId);
			if (category.getNameCategory() != null && user.isPresent()) {
				categoryService.updateCategory(category, user.get());
				return "update thanh cong";
			}
		
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println(e.getMessage());
			return null;
		}
		return null;
	}
	
	@RequestMapping(value = "/categorys/details",method = RequestMethod.PUT)
	public String updateCategoryDetails(@RequestBody CategoryDetails CategoryDetailsInput,HttpServletRequest request) {
		try {
			String jwt = authenticationFilter.getJwtFromRequest(request);
			String userId = tokenProvider.getUserIdFromJWT(jwt);
			Optional<User> user  = userSevice.findUserById(userId);
			Optional<CategoryDetails> categoryDetails = categoryDetailsRepository.findById(CategoryDetailsInput.getId());
			if (categoryDetails!=null && user.get()!= null) {
				categoryService.updateCategoryDetails(CategoryDetailsInput, user.get());
				return "update thanh cong";
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
	
	
	@RequestMapping(value = "/categorys/details/{id}")
	public String deleteCategoryDetails(@PathVariable("id") String id) {
		try {
			categoryService.deleteCategoryDetails(id);
			return "delete thanh cong";
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
	
	@RequestMapping(value = "/categorys/details",method = RequestMethod.POST)
	public String addCategoryDetails(@RequestBody CategoryDetails categoryDetails) {
		try {
			categoryService.addCategoryDetails( categoryDetails);
			return "them thanh cong";
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println(e.getMessage());
		}
		return null;
		
	}
	
}

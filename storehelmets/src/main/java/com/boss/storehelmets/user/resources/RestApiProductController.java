package com.boss.storehelmets.user.resources;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.boss.storehelmets.model.Product;
import com.boss.storehelmets.model.User;
import com.boss.storehelmets.repository.UserRepository;
import com.boss.storehelmets.securityjwt.JwtAuthenticationFilter;
import com.boss.storehelmets.securityjwt.JwtTokenProvider;
import com.boss.storehelmets.service.CategoryService;
import com.boss.storehelmets.service.ProductService;
import com.boss.storehelmets.service.UserDetailServiceImlp;
import com.boss.storehelmets.service.UserSevice;

@Controller
@RestController
@RequestMapping("/api/v1/user")
public class RestApiProductController {
	@Autowired
	private ProductService productService;
	
	@Autowired
	private CategoryService categoryService;
	
	
	@RequestMapping(value = "/products",method = RequestMethod.GET)
	private List<Product>   loadAllProduct(Model model) {
		try {
			List<Product> products = productService.getAll();
			model.addAttribute("products", products);
			return products;
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}
	
	@RequestMapping(value = "/products/{id}",method = RequestMethod.GET)
	private Optional<Product> loadProductById(@PathVariable("id") String id,Model model ){
		try {
			Optional<Product> product = productService.getById(id);
			model.addAttribute("product", product);
			return product;
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}
	
	@RequestMapping(value = "/products/categorydetails/{id}",method = RequestMethod.GET)
	private List<Product> loadProductsByCategoryDetailsId(@PathVariable("id")String id){
		try {
			List<Product> products = categoryService.getProductsByCategoryDetails(id);
			return products;
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}
	
	@RequestMapping(value = "/products/newlyadded",method = RequestMethod.GET)
	private List<Product> loadProductNewlyAdd (){
		try {
			List<Product> products = productService.getProductNewlyAdd();
			return products;
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}
	
	@RequestMapping(value = "/products/popular",method = RequestMethod.GET)
	private List<Product> loadProductByPopular(){
		try {
			List<Product> products = productService.getProductByPopular();
			return products;
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}
	
	@RequestMapping(value = "products/amountasc",method = RequestMethod.GET)
	private List<Product> loadProductByAmountAsc(){
		try {
			List<Product> products = productService.getByOrderByAmountAsc();
			return products;
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}
	
	@RequestMapping(value = "products/amountdesc",method = RequestMethod.GET)
	private List<Product> loadProductByAmountDesc(){
		try {
			List<Product> products = productService.getByOrderByAmountDesc();
			return products;
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}
	
	

}

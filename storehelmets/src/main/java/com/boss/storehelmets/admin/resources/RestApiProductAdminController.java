package com.boss.storehelmets.admin.resources;

import java.util.Collection;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
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
import com.boss.storehelmets.service.ProductService;
import com.boss.storehelmets.service.UserDetailServiceImlp;
import com.boss.storehelmets.service.UserSevice;

@RestController
@Controller
@RequestMapping(value = "/api/v1/admin")
public class RestApiProductAdminController {

	@Autowired
	private ProductService productService;
	
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
	
	@RequestMapping(value = "/products",method = RequestMethod.POST)
	public String addNewProduct(@RequestBody Product productInput,HttpServletRequest request) {
		try {
			String jwt = authenticationFilter.getJwtFromRequest(request);
			String userId = tokenProvider.getUserIdFromJWT(jwt);
			Optional<User> user  = userSevice.findUserById(userId);
			if (productInput != null && user != null) {
				productService.addProduct(productInput, user.get());
			}
			return "them thanh cong";
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
			return null;
		}
	
	}
	
	@RequestMapping(value = "products/{id}",method = RequestMethod.DELETE)
	public String deleteProductById(@PathVariable("id") String id,HttpServletRequest request) {
		try {
			String jwt = authenticationFilter.getJwtFromRequest(request);
			String userId = tokenProvider.getUserIdFromJWT(jwt);
			UserDetails userDetails  = userDetailsServiceImlp.loadUserById(userId);
			if (userDetails!= null) {
				Collection<? extends GrantedAuthority> role = userDetails.getAuthorities();
				for (GrantedAuthority grantedAuthority : role) {
					if (grantedAuthority.getAuthority().equalsIgnoreCase("ROLE_ADMIN")) {
						productService.deleteProduct(id);
					}
				}	
			}
				return "xoa thanh cong";
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}
	
	@RequestMapping(value = "/products",method = RequestMethod.PUT)
	public String updateProduct(@RequestBody Product productInput,HttpServletRequest request) {
		try {
			String jwt = authenticationFilter.getJwtFromRequest(request);
			String userId = tokenProvider.getUserIdFromJWT(jwt);
			Optional<User> user  = userSevice.findUserById(userId);
			if (productInput != null && user != null) {
				productService.updateProduct(productInput, user.get());
			}
			return "update thanh cong";
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
			return null;
		}
	
	}
}

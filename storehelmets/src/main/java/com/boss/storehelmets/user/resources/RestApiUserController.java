package com.boss.storehelmets.user.resources;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Optional;

import javax.jws.soap.SOAPBinding.Use;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import com.boss.storehelmets.app.utils.AppConstants;
import com.boss.storehelmets.dto.UserDto;
import com.boss.storehelmets.model.CustomUserDetails;
import com.boss.storehelmets.model.User;
import com.boss.storehelmets.securityconfig.LoginRequest;
import com.boss.storehelmets.securityconfig.LoginResponse;
import com.boss.storehelmets.securityjwt.JwtTokenProvider;
import com.boss.storehelmets.service.UserSevice;
import com.fasterxml.jackson.databind.ObjectMapper;


@RestController
@Controller
@RequestMapping(value = "/api/v1/users")
public class RestApiUserController {
	@Autowired(required = false)
    AuthenticationManager authenticationManager;;
    
	@Autowired
    private JwtTokenProvider tokenProvider;
	
	@Autowired
	private UserSevice userSevice;
	
	@PostMapping("/login")
	private LoginResponse authenticateUser( @RequestBody LoginRequest loginRequest,HttpServletResponse httpServletResponse) {
	     try {
	    	   // Xác thực từ username và password.
		        Authentication authentication = authenticationManager.authenticate(
		                new UsernamePasswordAuthenticationToken(
		                		loginRequest.getEmail(),
		                        loginRequest.getPassword()
		                )
		        );
		        // Nếu không xảy ra exception tức là thông tin hợp lệ
		        // Set thông tin authentication vào Security Context
		        SecurityContextHolder.getContext().setAuthentication(authentication);
		        // Trả về jwt cho người dùng.
		        String jwt = tokenProvider.generateToken((CustomUserDetails) authentication.getPrincipal(),httpServletResponse);
		        return new LoginResponse(jwt);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
	 return null;   
	}
	
	@RequestMapping(value = "/register",method = RequestMethod.POST)
	private String registerAccount(@RequestBody String jsonValue) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			UserDto userDto = mapper.readValue(jsonValue, UserDto.class);
			userSevice.registerAccount(userDto);
			return AppConstants.SUCCESS_CREATE_USER;
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println(e.getMessage());
			return AppConstants.ERROR_CREATE_USER;
		}
		
	}
	
	@RequestMapping(value="/username",method=RequestMethod.GET)
	public String getUsernameCustomer() {
		try {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			String email = auth.getName();
			Optional<User> user = userSevice.findUserByEmail(email);
			if (user!= null) {
				String username = user.get().getFullName();
				return username;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
	
	
	@RequestMapping(value="/auth/me",method=RequestMethod.GET)
	public User getUserByAuth() {
		try {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			String username = auth.getName();
			Optional<User> user = userSevice.findUserByEmail(username);
			return user.get();
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println(e.getMessage());
		}
		return null;
	}
	
	private String changeInformationUser(UserDto userDto) {
		
		return null;
	}
	
	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	public User getUserById(@PathVariable("id") String id){
		try {
			Optional<User> user = userSevice.findUserById(id);
			return user.get();
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println(e.getMessage());
			System.err.println(e.getCause());
		}
		return null;
	}
}

package com.boss.storehelmets.admin.resources;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.boss.storehelmets.model.CustomUserDetails;
import com.boss.storehelmets.securityconfig.LoginRequest;
import com.boss.storehelmets.securityconfig.LoginResponse;
import com.boss.storehelmets.securityjwt.JwtTokenProvider;
import com.boss.storehelmets.service.UserSevice;

@RestController
@Controller
@RequestMapping(value = "/api/v1/admin")
public class RestApiAdminController {
	
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

}

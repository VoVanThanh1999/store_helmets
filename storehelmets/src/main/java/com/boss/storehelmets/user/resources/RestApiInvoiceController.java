package com.boss.storehelmets.user.resources;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.boss.storehelmets.app.utils.AppConstants;
import com.boss.storehelmets.model.User;
import com.boss.storehelmets.repository.UserRepository;
import com.boss.storehelmets.securityjwt.JwtAuthenticationFilter;
import com.boss.storehelmets.securityjwt.JwtTokenProvider;
import com.boss.storehelmets.service.InvoiceService;
import com.boss.storehelmets.service.UserDetailServiceImpl;

@RestController
@Controller
@RequestMapping(value = "/api/v1/users")
public class RestApiInvoiceController {
	@Autowired
	UserDetailServiceImpl userDetailsServiceImlp;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	InvoiceService invoiceService;
	
	@Autowired
	JwtTokenProvider tokenProvider;
	
	@Autowired
	JwtAuthenticationFilter authenticationFilter;
	
	@RequestMapping(value = "/invoices",method = RequestMethod.POST)
	private String insertNewInvoice(HttpServletRequest request) {
		try {
			String jwt = authenticationFilter.getJwtFromRequest(request);
			String userId = tokenProvider.getUserIdFromJWT(jwt);
			UserDetails userDetails  = userDetailsServiceImlp.loadUserById(userId);
			if (userDetails!= null) {
				String userName = userDetails.getUsername();
				Optional<User> optionalUser = userRepository.findByEmail(userName);
				invoiceService.inserNewInvoice(request, optionalUser.get());
				return AppConstants.SUCCESS_CREATE;
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println(e.getMessage());
			return AppConstants.ERROR_UPDATE;
		}
		return AppConstants.ERROR_UPDATE;
	}

}

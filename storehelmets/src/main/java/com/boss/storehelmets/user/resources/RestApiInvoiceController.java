package com.boss.storehelmets.user.resources;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import com.boss.storehelmets.securityjwt.JwtAuthenticationFilter;
import com.boss.storehelmets.securityjwt.JwtTokenProvider;
import com.boss.storehelmets.service.InvoiceService;


public class RestApiInvoiceController {
	@Autowired
	UserDetailsService userDetailsService;
	
	@Autowired
	InvoiceService invoiceService;
	
	@Autowired
	JwtTokenProvider jwtTokenProvider;
	
	@Autowired
	JwtAuthenticationFilter authenticationFilter;
	
	public RestApiInvoiceController(HttpServletRequest request) {
		try {

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}

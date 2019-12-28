package com.boss.storehelmets.admin.resources;
import java.util.List;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.boss.storehelmets.app.utils.AppConstants;
import com.boss.storehelmets.model.Invoice;
import com.boss.storehelmets.model.User;
import com.boss.storehelmets.repository.UserRepository;
import com.boss.storehelmets.securityjwt.JwtAuthenticationFilter;
import com.boss.storehelmets.securityjwt.JwtTokenProvider;
import com.boss.storehelmets.service.InvoiceService;
import com.boss.storehelmets.service.UserDetailServiceImpl;
import com.boss.storehelmets.service.UserSevice;

@RestController
@Controller
@RequestMapping(value = "/api/v1/admins")
public class RestApiInvoiceAdminController {
	@Autowired
	InvoiceService invoiceService;
	
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
	
	
	@RequestMapping(value = "/invoices/{id}",method = RequestMethod.PUT)
	public String confrimInvoice(@PathVariable("id") String id,HttpServletRequest request ) {
		try {
			String jwt = authenticationFilter.getJwtFromRequest(request);
			String userId = tokenProvider.getUserIdFromJWT(jwt);
			Optional<User> user  = userSevice.findUserById(userId);
			if (invoiceService.confimInvoice(user.get(), id) != null) {
				return AppConstants.SUCCESS_invoice_approval;
			}
			return AppConstants.ERROR_invoice_approval;
		} catch (Exception e) {
			// TODO: handle exception
			return AppConstants.ERROR_invoice_approval;
		}
	}
	
	@RequestMapping(value = "/invoices",method = RequestMethod.GET)
	public List<Invoice> getAllInvoice() {
		List<Invoice> invoices = invoiceService.getAllInvoice();
		try {
			return invoices;
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}
	
}
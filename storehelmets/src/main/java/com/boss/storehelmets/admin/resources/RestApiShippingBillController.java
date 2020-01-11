package com.boss.storehelmets.admin.resources;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.boss.storehelmets.app.utils.AppConstants;
import com.boss.storehelmets.model.Invoice;
import com.boss.storehelmets.model.ShippingBill;
import com.boss.storehelmets.model.User;
import com.boss.storehelmets.repository.UserRepository;
import com.boss.storehelmets.securityjwt.JwtAuthenticationFilter;
import com.boss.storehelmets.securityjwt.JwtTokenProvider;
import com.boss.storehelmets.service.ShippingBillService;
import com.boss.storehelmets.service.UserDetailServiceImpl;
import com.boss.storehelmets.service.UserSevice;

@RestController
@RequestMapping("api/v1/admins")
public class RestApiShippingBillController {
	
	@Autowired
	ShippingBillService shippingBillService;

	
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
	
	@RequestMapping(value = "/shippings",method = RequestMethod.POST)
	public String addNewsShippingBill(@RequestBody User shipper,List<Invoice> invoicesDto,HttpServletRequest request) {
		try {
			String jwt = authenticationFilter.getJwtFromRequest(request);
			String userId = tokenProvider.getUserIdFromJWT(jwt);
			Optional<User> user  = userSevice.findUserById(userId);
			if (user != null) {
				if (shippingBillService.addNewsShippingBill(user.get(), shipper, invoicesDto) != null) {
					return AppConstants.SUCCESS_ADD_SHIPPINGBILL;
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
			return AppConstants.ERROR_ADD_SHIPPINGBILL;
		}
		return AppConstants.ERROR_ADD_SHIPPINGBILL;
	}
	
	@RequestMapping(value = "/shippings",method = RequestMethod.GET)
	public List<ShippingBill> getShippingBillByShipper(HttpServletRequest request){
		try {
			String jwt = authenticationFilter.getJwtFromRequest(request);
			String userId = tokenProvider.getUserIdFromJWT(jwt);
			Optional<User> user  = userSevice.findUserById(userId);
			List<ShippingBill> shippingBills = shippingBillService.getShippingBillHaveUserId(user.get());
			if (shippingBills != null) {
				return shippingBills;
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
			return null;
		}
		return null;
	}

	
}

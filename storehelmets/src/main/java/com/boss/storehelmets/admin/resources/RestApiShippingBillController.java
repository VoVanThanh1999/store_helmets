package com.boss.storehelmets.admin.resources;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.management.relation.Role;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
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
import com.boss.storehelmets.service.InvoiceService;
import com.boss.storehelmets.service.ShippingBillService;
import com.boss.storehelmets.service.UserDetailServiceImpl;
import com.boss.storehelmets.service.UserSevice;

@RestController
@RequestMapping("/api/v1/admins")
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
	InvoiceService invoiceService;
	
	@Autowired
	JwtAuthenticationFilter authenticationFilter;
	
	@RequestMapping(value="/shippings/drivers/shippers")
	public List<User> getUserHaveRoleIsShipper(){
		List<User> userDrivers = shippingBillService.getUserHaveRoleIsShippers();
		return userDrivers;
	}
	
	@RequestMapping(value = "/shippings/{idshiper}/{idadmin}",method = RequestMethod.POST)
	public String addNewsShippingBill(@RequestBody List<Invoice> invoicesDto, @PathVariable("idshiper") String idshiper, @PathVariable("idadmin") String idadmin) {
		try {
			System.err.println(invoicesDto.get(0).getIdInvoice());
			Optional<User> admin  = userSevice.findUserById(idadmin);
			Optional<User> shiper = userSevice.findUserById(idshiper);
			if (admin != null && shiper != null && invoicesDto != null) {
				List<Invoice> invoices = new ArrayList<>();
				for (Invoice invoiceDto : invoicesDto) {
					Invoice invoice = invoiceService.getInvoiceById(invoiceDto.getIdInvoice());
					invoices.add(invoice);
				}
				if (shippingBillService.addNewsShippingBill(admin.get(), shiper.get(), invoices) != null) {
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
	
	@RequestMapping(value="shippings/beingtransported",method=RequestMethod.GET)
	public List<ShippingBill> getShippingBillBeingTransported(){
		try {
			return shippingBillService.getShipppingBillBeingTransported();
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println(e.getMessage());
			System.err.println(e.getLocalizedMessage());
		}
		return null;
	}
	
	
}

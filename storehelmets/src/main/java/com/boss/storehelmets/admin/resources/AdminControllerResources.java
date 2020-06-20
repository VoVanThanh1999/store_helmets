package com.boss.storehelmets.admin.resources;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.boss.storehelmets.model.Invoice;
import com.boss.storehelmets.model.User;
import com.boss.storehelmets.service.ShipperService;
import com.boss.storehelmets.service.UserSevice;

@Controller
@RequestMapping("/admins")
public class AdminControllerResources {
	
	@Autowired
	ShipperService shipperService;
	
	@Autowired
	UserSevice userSevice;
	
	@RequestMapping("/trangchu")
	public ModelAndView defaultIndex() {
		ModelAndView modelAndView = new ModelAndView("index_admin");
		
		return modelAndView;
	}
	
	@RequestMapping(value="/login")
	public String login() {
		try {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			String username = auth.getName();
			Optional<User> optional = userSevice.findUserByEmail(username);
			if (optional.get()!=null) {
				return "redirect:/";
			}
		} catch (Exception e) {
			// TODO: handle exception
		}	
		return "login-admin";
	}
	
	@RequestMapping(value="/chitiethoadon/{idhoadon}",method=RequestMethod.GET)
	public String viewDetailsInvoiceInShippingBill(@PathVariable("idhoadon")String idhoadon,ModelMap modelMap) {
		try {
			List<Invoice> invoices = shipperService.getInvoiceByShipping(idhoadon);
			modelMap.addAttribute("invoices", invoices);
			modelMap.addAttribute("idHoaDon", idhoadon);
			return"admin_details_shipping";
		} catch (Exception e) {
			// TODO: handle except
		}
		return null;
	}
	
	
	
	
	
}

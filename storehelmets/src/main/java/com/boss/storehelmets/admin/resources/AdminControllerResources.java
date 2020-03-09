package com.boss.storehelmets.admin.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.boss.storehelmets.model.Invoice;
import com.boss.storehelmets.service.ShipperService;

@Controller
@RequestMapping("/admins")
public class AdminControllerResources {
	
	@Autowired
	ShipperService shipperService;
	
	@RequestMapping("/trangchu")
	public ModelAndView defaultIndex() {
		ModelAndView modelAndView = new ModelAndView("index_admin");
		
		return modelAndView;
	}
	
	@RequestMapping(value="/login")
	public String login() {
		return "login_admin";
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

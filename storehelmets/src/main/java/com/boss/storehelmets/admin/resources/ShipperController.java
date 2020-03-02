package com.boss.storehelmets.admin.resources;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.boss.storehelmets.app.utils.AppConstants;
import com.boss.storehelmets.model.Invoice;
import com.boss.storehelmets.model.ShippingBill;
import com.boss.storehelmets.model.User;
import com.boss.storehelmets.service.ShipperService;
import com.boss.storehelmets.service.UserSevice;
import com.fasterxml.jackson.annotation.JsonCreator.Mode;

@Controller
@RequestMapping("/shipper")
public class ShipperController {
	@Autowired
	UserSevice userSevice;
	
	@Autowired
	ShipperService shipperService;
	
	public static final Logger LOGGER = LogManager.getLogger(ShipperController.class);
	
	
	@RequestMapping(value="/trangchu", method=RequestMethod.GET)
	public String homeShipper(ModelMap modelMap) {
		try {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			String username = auth.getName();
			Optional<User> user = userSevice.findUserByEmail(username);
			List<ShippingBill> shippingBills = shipperService.getShippingBillByIdShipper(user.get().getIdUser());
			modelMap.addAttribute("shippingBills", shippingBills);
			return "home_shipper";
		} catch (Exception e) {
			// TODO: handle exception
			LOGGER.debug("Debug log message");
		}
		return null;
	}
	
	@RequestMapping(value="/chitiethoadon/{idhoadon}",method=RequestMethod.GET)
	public String viewDetailsInvoiceInShippingBill(@RequestParam("idhoadon")String idhoadon,ModelMap modelMap) {
		try {
			List<Invoice> invoices = shipperService.getInvoiceByShipping(idhoadon);
			modelMap.addAttribute("invoices", invoices);
			modelMap.addAttribute("idHoaDon", idhoadon);
			return"shipper_details_shipping";
		} catch (Exception e) {
			// TODO: handle exception
			LOGGER.debug("Debug log message");
		}
		return null;
	}
	
}

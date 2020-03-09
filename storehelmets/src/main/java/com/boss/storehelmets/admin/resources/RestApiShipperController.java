package com.boss.storehelmets.admin.resources;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.boss.storehelmets.model.Invoice;
import com.boss.storehelmets.model.ShippingBill;
import com.boss.storehelmets.model.User;
import com.boss.storehelmets.service.ShipperService;
import com.boss.storehelmets.service.ShipperServiceImpl;
import com.boss.storehelmets.service.UserSevice;

@RequestMapping(value="/api/v1/shippers")
@RestController
public class RestApiShipperController {
	
	@Autowired
	ShipperService shipperService;
	
	@Autowired
	private UserSevice userSevice;
	
	public static final Logger LOGGER = LogManager.getLogger(RestApiShipperController.class);
	
	
	@RequestMapping(value="/shippingbills/{idShipper}",method=RequestMethod.GET)
	public List<ShippingBill> getShippingbillByShipper(@PathVariable("idShipper") String idShipper){
		return shipperService.getShippingBillByIdShipper(idShipper);
	}
	
	@RequestMapping(value="/shippingbills/shippers/{idShipper}",method=RequestMethod.GET)
	public List<ShippingBill> hienThiHoaDonDaGiaoThanhCong(@PathVariable("idShipper")String idShipper){
		return shipperService.hienThiShippingBillDaGiaoThanhCong(idShipper);
	}
	
	@RequestMapping(value="/shippingbills/{idShippingBill}/invoices",method=RequestMethod.GET)
	public List<Invoice> getInvoiceByShippingBill(@PathVariable("idShippingBill") String idShippingBill){
		try {
			List<Invoice> invoices = shipperService.getInvoiceByShipping(idShippingBill);
			return invoices;
		} catch (Exception e) {
			// TODO: handle exception
			LOGGER.getClass();
			LOGGER.fatal(e.getMessage());
			LOGGER.error(e.getMessage());
		}
		return null;
	}
	
	@RequestMapping(value="/shippingbills/{idShippingBill}/cancel/invoices/{idInvoice}",method=RequestMethod.PATCH)
	public String cancelInvoiceByShipper(@PathVariable("idShippingBill") String idShippingBill,@PathVariable("idInvoice") String idInvoice) {
		try {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			String username = auth.getName();
			Optional<User> user = userSevice.findUserByEmail(username);
			return shipperService.cancelInvoice(idInvoice, user.get().getIdUser(), idShippingBill);
		} catch (Exception e) {
			// TODO: handle exception
			LOGGER.getClass();
			e.printStackTrace();
		}
		return ShipperServiceImpl.ErrorSuccessCancelInvoice;
	}
	
	@RequestMapping(value="/shippingbills/{idShippingBill}/confirm/invoices/{idInvoice}")
	public String confirmSuccessDelivery(@PathVariable("idShippingBill")String idShippingBill,@PathVariable("idInvoice")String invoices) {
		try {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			String username = auth.getName();
			Optional<User> user = userSevice.findUserByEmail(username);
			return shipperService.confirmInvoiceInShipping(idShippingBill, invoices, user.get().getIdUser());
		} catch (Exception e) {
			// TODO: handle exception
			LOGGER.warn("Xác nhận giao hàng");
			e.printStackTrace();
		}
			return ShipperServiceImpl.ErrorConfirmInvoice;
	}
	
	@RequestMapping(value="/shippingbills/{idShipping}",method=RequestMethod.PUT)
	public String chuyenHoaDonChoAdmin(@PathVariable("idShipping") String idShipping) {
		try {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			String username = auth.getName();
			Optional<User> user = userSevice.findUserByEmail(username);
			return	shipperService.confirmShipping(idShipping, user.get().getIdUser());
		} catch (Exception e) {
			// TODO: handle exception
			LOGGER.warn("chuyển hóa đơn xong admin");
			e.printStackTrace();
		}
		return ShipperServiceImpl.ErrorTransferredToTheManager;
	}
	@RequestMapping(value="/shippingbills/awaitingconfirmation",method=RequestMethod.GET)
	public List<ShippingBill> hienThiShippingbillDangChoXacNhan(){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		Optional<User> user = userSevice.findUserByEmail(username);
		return	shipperService.hienThiShippingBillDangChoXacNhan(user.get().getIdUser());
	}
	
	@RequestMapping(value="/shippingbills/deliveredsuccessfully",method=RequestMethod.GET)
	public List<ShippingBill> hienThiShippingbillDaGiaoThanhCCong(){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		Optional<User> user = userSevice.findUserByEmail(username);
		return shipperService.hienThiShippingBillDaGiaoThanhCong(user.get().getIdUser());
	}
	
	
}

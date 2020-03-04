package com.boss.storehelmets.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.boss.storehelmets.model.Invoice;
import com.boss.storehelmets.model.ShippingBill;

@Service
public interface ShipperService {
	public List<ShippingBill> getShippingBillByIdShipper(String id);
	
	public List<Invoice> getInvoiceByShipping(String idShipping);
	
	public String confirmInvoiceInShipping(String idShipping,String idInvoice,String idShipper);
	
	public String confirmShipping(String idShipping,String idShipper);
	
	public String cancelInvoice(String idInvoice,String idShipper,String idShippingBill);
	
	public List<Invoice> checkInvoicesAreCancel(List<Invoice> invoices);
	
	public float getMoneyCancelInShippingBill(String idShippingBill);
	
	public float getMoneyProceeds(String idShippingBill);
	
	public String chuyenDonGiaoHangChoAdminQuanLy(String idShipping,String idShipper);
	

}

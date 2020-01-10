package com.boss.storehelmets.service;
import java.util.List;
import org.springframework.stereotype.Service;
import com.boss.storehelmets.model.Invoice;
import com.boss.storehelmets.model.ShippingBill;
import com.boss.storehelmets.model.User;

@Service
public interface ShippingBillService {
	public void addNewsShippingBill(User user,List<Invoice> invoices);
	
	public ShippingBill checkShippingBill(User user,ShippingBill shippingBill);
	
	public String confimInvoiceByShipper(User user, Invoice invoice);
	
	public List<ShippingBill> getShippingBillHaveUserId(User user);
	
}

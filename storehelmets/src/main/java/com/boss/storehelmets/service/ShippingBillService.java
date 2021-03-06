package com.boss.storehelmets.service;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import com.boss.storehelmets.model.Invoice;
import com.boss.storehelmets.model.ShippingBill;
import com.boss.storehelmets.model.User;

@Service
public interface ShippingBillService {
	
	public String addNewsShippingBill(User adminCreate,User shipper, List<Invoice> invoicesDto);
	
	public ShippingBill checkShippingBill(User user,ShippingBill shippingBill);
	
	public String confimInvoiceByShipper(User user, Invoice invoice);
	
	public List<ShippingBill> getShippingBillHaveUserId(User user);
	
	public List<Invoice> getInvoiceHaveInShippingBill(User shippier,ShippingBill shippingBill);

	public String confimShippingBill(ShippingBill shippingBill,User shippier);
	
	public List<User> getUserHaveRoleIsShippers();
	
	public String cancelInvoice(ShippingBill shippingBill,User shippier,String idInvoice);
	
	public List<ShippingBill> getShipppingBillBeingTransported();
	
	public Page<ShippingBill> getShipppingBillBeingTransportedByPage(PageRequest pageRequest);
	
	public float getMoneyShippingBillBeingTransported();
	
	public String xacNhanHoanThanhHoaDon(String idShippingBill,String idManger);
	
	public List<ShippingBill> hienThiNhungHoaDonThanhCong();
	
	public List<ShippingBill> hienThiNhungHoaDonDangChoDuyet();
	
	public List<ShippingBill> hienThiShippingbillsThanhCong();
	
	
	
}

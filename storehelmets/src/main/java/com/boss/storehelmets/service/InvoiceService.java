package com.boss.storehelmets.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.data.domain.Page;

import com.boss.storehelmets.model.Invoice;
import com.boss.storehelmets.model.User;

public interface InvoiceService {
	public String inserNewInvoice(HttpServletRequest httpServletRequest,User user , Invoice invoice);
	
	public String confimInvoice(User user,String id);
	
	public List<Invoice> getAllInvoice();
	
	public String deleteInvoice();
	
	public  List<Invoice> getInvoicesByUserId(User user);
	
	public Long getAmountInvoice();
	
	public Page<Invoice> getInvoiceByPageRequest(String valuekey);
	
	public int getTotalPages();
	
	public Invoice getInvoiceById(String id);
	
	public Page<Invoice> getInvoicesByStatusConfimIsTrueAndSuccesIsFalse(String key);
	
	public int getTotalMoneyInvouceAwaitingApproval();
	
	public List<Invoice> getInvoiceStatusTransportIsTrue();
	
	public List<Invoice> getInvoiceByIdShippingBill(String idShippingBill);
	
}

package com.boss.storehelmets.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.boss.storehelmets.model.Invoice;
import com.boss.storehelmets.model.User;

public interface InvoiceService {
	public String inserNewInvoice(HttpServletRequest httpServletRequest,User user , Invoice invoice);
	
	public String confimInvoice(User user,String id);
	
	public List<Invoice> getAllInvoice();
	
	public String deleteInvoice();
	
	public  List<Invoice> getInvoicesByUserId(User user);
	
}

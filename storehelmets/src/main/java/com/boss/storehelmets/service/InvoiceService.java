package com.boss.storehelmets.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.boss.storehelmets.model.Invoice;
import com.boss.storehelmets.model.User;

public interface InvoiceService {
	public void inserNewInvoice(HttpServletRequest httpServletRequest,User user);
	
	public String confimInvoice(User user,String id);
	
	public List<Invoice> getAllInvoice();
	
	public void deleteInvoice();
	
}

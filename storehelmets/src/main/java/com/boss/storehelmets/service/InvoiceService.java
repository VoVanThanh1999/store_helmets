package com.boss.storehelmets.service;

import javax.servlet.http.HttpServletRequest;
import com.boss.storehelmets.model.User;

public interface InvoiceService {
	public void inserNewInvoice(HttpServletRequest httpServletRequest,User user);
	
}

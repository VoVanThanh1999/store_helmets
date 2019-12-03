package com.boss.storehelmets.service;
import javax.servlet.http.HttpServletRequest;

import com.boss.storehelmets.dto.BasketDto;
import com.boss.storehelmets.dto.BastketDtoTotal;

public interface BasketDtoService {

	public BasketDto checkProductToBasket(BasketDto basketDto,HttpServletRequest request);

	public void addProductToBasket(BasketDto basketDto,HttpServletRequest request);
	
	public void deleteProductInBasket(String id,HttpServletRequest request);
	
	public void updateProductInBasket(BasketDto basketDto,HttpServletRequest request);
	
	public BastketDtoTotal getTotalBasketDto(HttpServletRequest request);
}

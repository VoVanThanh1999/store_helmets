package com.boss.storehelmets.service;
import javax.servlet.http.HttpServletRequest;
import com.boss.storehelmets.dto.BasketDto;

public interface BasketDtoService {

	public BasketDto checkProductToBasket(BasketDto basketDto,HttpServletRequest request);

	public void addProductToBasket(BasketDto basketDto,HttpServletRequest request);
	
	public String deleteProductInBasket(BasketDto basketDto);
	
	public String updateProductInBasket(BasketDto basketDto);
}

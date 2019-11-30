package com.boss.storehelmets.dto;
import java.util.List;

public class BastketDtoTotal {
	private List<BasketDto> basketDtos;
	private float totalMoneyBasket;
	/**
	 * @return the basketDtos
	 */
	public List<BasketDto> getBasketDtos() {
		return basketDtos;
	}
	/**
	 * @param basketDtos the basketDtos to set
	 */
	public void setBasketDtos(List<BasketDto> basketDtos) {
		this.basketDtos = basketDtos;
	}
	/**
	 * @return the totalMoneyBasket
	 */
	public float getTotalMoneyBasket() {
		return totalMoneyBasket;
	}
	/**
	 * @param totalMoneyBasket the totalMoneyBasket to set
	 */
	public void setTotalMoneyBasket(float totalMoneyBasket) {
		this.totalMoneyBasket = totalMoneyBasket;
	}
	
	
	
}

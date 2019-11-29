package com.boss.storehelmets.dto;


import com.boss.storehelmets.model.Product;

public class BasketDto {
	 private String idBasket;
	 private String nameProduct;
	 private String imageProduct;
	 private float price;
	 private float totalMoney;
	 private int numOfCart;

	/**
	 * @return the idBasket
	 */
	public String getIdBasket() {
		return idBasket;
	}

	/**
	 * @param idBasket the idBasket to set
	 */
	public void setIdBasket(String idBasket) {
		this.idBasket = idBasket;
	}

	/**
	 * @return the nameProduct
	 */
	public String getNameProduct() {
		return nameProduct;
	}

	/**
	 * @param nameProduct the nameProduct to set
	 */
	public void setNameProduct(String nameProduct) {
		this.nameProduct = nameProduct;
	}

	/**
	 * @return the imageProduct
	 */
	public String getImageProduct() {
		return imageProduct;
	}

	/**
	 * @param imageProduct the imageProduct to set
	 */
	public void setImageProduct(String imageProduct) {
		this.imageProduct = imageProduct;
	}

	/**
	 * @return the price
	 */
	public float getPrice() {
		return price;
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(float price) {
		this.price = price;
	}

	/**
	 * @return the totalMoney
	 */
	public float getTotalMoney() {
		return totalMoney;
	}

	/**
	 * @param totalMoney the totalMoney to set
	 */
	public void setTotalMoney(float totalMoney) {
		this.totalMoney = totalMoney;
	}

	/**
	 * @return the numOfCart
	 */
	public int getNumOfCart() {
		return numOfCart;
	}

	/**
	 * @param numOfCart the numOfCart to set
	 */
	public void setNumOfCart(int numOfCart) {
		this.numOfCart = numOfCart;
	}
	 
	
	 
	
}

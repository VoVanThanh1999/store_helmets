package com.boss.storehelmets.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;
@Entity
@Table(name="basket")
public class Basket {
	@Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid", strategy = "uuid")
	@Column(name="id_basket")
	private String idBasket;
	
	@Column(name="name_product")
	private String nameProduct;
	
	@Column(name="id_product")
	private String idProduct;
	
	@Column(name = "image_product")
	private String imageProduct;
	
	@Column(name="price")
	private float price;
	
	@Column(name="total_money")
	private float totalMoney;
	
	@Column(name="num_of_cart")
	private int numOfCart;
	
	@ManyToMany(mappedBy = "baskets")
	private Set<BastketTotal> bastketTotals;

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
	 * @return the idProduct
	 */
	public String getIdProduct() {
		return idProduct;
	}

	/**
	 * @param idProduct the idProduct to set
	 */
	public void setIdProduct(String idProduct) {
		this.idProduct = idProduct;
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

package com.boss.storehelmets.model;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
@Entity
@Table(name="basket_total")
public class BastketTotal {
	
	@Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid", strategy = "uuid")
	@Column(name="id_basket_total")
	private String id; 
	
	@Column(name="total_money_basket")
	private float totalMoneyBasket;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "basket_details_total",
		joinColumns = @JoinColumn(name="id_basket_total"),
			inverseJoinColumns = @JoinColumn(name="id_basket")
	)
	Set<Basket> baskets;

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
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

	/**
	 * @return the baskets
	 */
	public Set<Basket> getBaskets() {
		return baskets;
	}

	/**
	 * @param baskets the baskets to set
	 */
	public void setBaskets(Set<Basket> baskets) {
		this.baskets = baskets;
	}

	
	
	
	
}

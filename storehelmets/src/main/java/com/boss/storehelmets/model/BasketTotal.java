package com.boss.storehelmets.model;
import java.util.List;
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
import org.hibernate.annotations.ManyToAny;

@Entity
@Table(name="basket_total")
public class BasketTotal {
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid", strategy = "uuid")
	@Column(name = "id_basket_total")
	@Id
	private String idBasketTotal;
	
	@ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	@JoinTable(name = "product_basket_total",joinColumns = @JoinColumn(name="id_basket_total"),
		inverseJoinColumns = @JoinColumn(name="id_product")
	)
	private List<Product> products;
	
	@Column(name = "total_money")
	private float totalMoney;

	/**
	 * @return the idBasketTotal
	 */
	public String getIdBasketTotal() {
		return idBasketTotal;
	}

	/**
	 * @param idBasketTotal the idBasketTotal to set
	 */
	public void setIdBasketTotal(String idBasketTotal) {
		this.idBasketTotal = idBasketTotal;
	}

	/**
	 * @return the products
	 */
	public List<Product> getProducts() {
		return products;
	}

	/**
	 * @param products the products to set
	 */
	public void setProducts(List<Product> products) {
		this.products = products;
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
	
	
	
	
	
}

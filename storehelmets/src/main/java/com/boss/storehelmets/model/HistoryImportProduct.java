package com.boss.storehelmets.model;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;    
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="product_import_history")
public class HistoryImportProduct {
	@Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid", strategy = "uuid")
	@Column(name="id_history_import_product")
	private String idHistoryImportProduct;
	
	@Column(name = "describes")
	private String describe;
	
	
	@OneToOne(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	private Product products;
	
	@OneToOne(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	private User user;
	
	
	
	public String getIdHistoryImportProduct() {
		return idHistoryImportProduct;
	}

	public void setIdHistoryImportProduct(String idHistoryImportProduct) {
		this.idHistoryImportProduct = idHistoryImportProduct;
	}

	public Product getProducts() {
		return products;
	}

	public void setProducts(Product products) {
		this.products = products;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getDescribe() {
		return describe;
	}

	public void setDescribe(String describe) {
		this.describe = describe;
	}
	
	
	
	
}

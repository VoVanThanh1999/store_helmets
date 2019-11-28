package com.boss.storehelmets.model;

import java.sql.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Table(name="product")
@Entity
public class Product {
	@Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid", strategy = "uuid")
	@Column(name="id_product")
	private String idProduct;
	@Column(name="name_product")
	private String nameProduct;
	
	@Column(name="date_create")
	private Date dateCreate;
	
	@OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	@JoinTable(name = "product_category",joinColumns = @JoinColumn(name="id_product"),
		inverseJoinColumns = @JoinColumn(name="id_details_category")
	)
	private Set<CategoryDetails> categoryDetails;
	
	@OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	@JoinColumn(name="id_user")
	private User userCreat;
	
	@OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	@JoinColumn(name="id_products_details")
	private ProductsDetails productsDetails;
	public String getIdProduct() {
		return idProduct;
	}

	public void setIdProduct(String idProduct) {
		this.idProduct = idProduct;
	}

	public String getNameProduct() {
		return nameProduct;
	}

	public void setNameProduct(String nameProduct) {
		this.nameProduct = nameProduct;
	}

	public Set<CategoryDetails> getCategoryDetails() {
		return categoryDetails;
	}

	public void setCategoryDetails(Set<CategoryDetails> categoryDetails) {
		this.categoryDetails = categoryDetails;
	}

	public User getUserCreat() {
		return userCreat;
	}

	public void setUserCreat(User userCreat) {
		this.userCreat = userCreat;
	}

	public ProductsDetails getProductsDetails() {
		return productsDetails;
	}

	public void setProductsDetails(ProductsDetails productsDetails) {
		this.productsDetails = productsDetails;
	}

	public Date getDateCreate() {
		return dateCreate;
	}

	public void setDateCreate(Date dateCreate) {
		this.dateCreate = dateCreate;
	}
	
	
}

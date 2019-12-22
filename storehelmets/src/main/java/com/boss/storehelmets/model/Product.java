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
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;

import com.sun.istack.NotNull;

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
	
	
	@Column(name="id_user_update")
	private String idUserUpdate;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "product_category",joinColumns = @JoinColumn(name="id_product"),
		inverseJoinColumns  = @JoinColumn(name="id_details_category")
	)
	private Set<CategoryDetails> categoryDetails;
	
	@ManyToOne()
	@JoinColumn(name="id_user")
	private User userCreat;
	
	@OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	@JoinColumn(name="id_products_details")
	private ProductsDetails productsDetails;

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
	 * @return the dateCreate
	 */
	public Date getDateCreate() {
		return dateCreate;
	}

	/**
	 * @param dateCreate the dateCreate to set
	 */
	public void setDateCreate(Date dateCreate) {
		this.dateCreate = dateCreate;
	}

	/**
	 * @return the idUserUpdate
	 */
	public String getIdUserUpdate() {
		return idUserUpdate;
	}

	/**
	 * @param idUserUpdate the idUserUpdate to set
	 */
	public void setIdUserUpdate(String idUserUpdate) {
		this.idUserUpdate = idUserUpdate;
	}

	/**
	 * @return the userCreat
	 */
	public User getUserCreat() {
		return userCreat;
	}

	/**
	 * @param userCreat the userCreat to set
	 */
	public void setUserCreat(User userCreat) {
		this.userCreat = userCreat;
	}

	/**
	 * @return the productsDetails
	 */
	public ProductsDetails getProductsDetails() {
		return productsDetails;
	}

	/**
	 * @param productsDetails the productsDetails to set
	 */
	public void setProductsDetails(ProductsDetails productsDetails) {
		this.productsDetails = productsDetails;
	}

	/**
	 * @return the categoryDetails
	 */
	public Set<CategoryDetails> getCategoryDetails() {
		return categoryDetails;
	}

	/**
	 * @param categoryDetails the categoryDetails to set
	 */
	public void setCategoryDetails(Set<CategoryDetails> categoryDetails) {
		this.categoryDetails = categoryDetails;
	}

	
	
	
}

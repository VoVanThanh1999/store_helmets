package com.boss.storehelmets.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="category_details")
public class CategoryDetails {
	@Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid", strategy = "uuid")
	@Column(name="id_details_category")
	private String id;
	
	@Column(name="name_details_category")
	private String nameDetailsCategory;
	
	@OneToOne()
	@JoinColumn(name = "id_category")
	private Category category;
	
	@ManyToMany(mappedBy = "categoryDetails",fetch = FetchType.EAGER)
	Set<Product> products;

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
	 * @return the nameDetailsCategory
	 */
	public String getNameDetailsCategory() {
		return nameDetailsCategory;
	}

	/**
	 * @param nameDetailsCategory the nameDetailsCategory to set
	 */
	public void setNameDetailsCategory(String nameDetailsCategory) {
		this.nameDetailsCategory = nameDetailsCategory;
	}


	/**
	 * @param category the category to set
	 */
	public void setCategory(Category category) {
		this.category = category;
	}

	/**
	 * @return the products
	 */
	public Set<Product> getProducts() {
		return products;
	}

	/**
	 * @param products the products to set
	 */
	public void setProducts(Set<Product> products) {
		this.products = products;
	}
	
	
	
	

}

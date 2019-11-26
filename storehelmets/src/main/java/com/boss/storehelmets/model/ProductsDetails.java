package com.boss.storehelmets.model;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="product_details")
public class ProductsDetails {
	@Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid", strategy = "uuid")
	@Column(name="id_products_details")
	private String idProductsDetails;
	
	@Column(name="description")
	private String description;
	
	@Column(name="brand")
	private String brand;
	
	@Column(name="amount")
	private int amount;
	
	@Column(name="weight")
	private String weight;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="id_products_details")
	private Set<ProductImage> productImages;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="id_products_details")
	private Set<ProductSize> productSizes;

	public String getIdProductsDetails() {
		return idProductsDetails;
	}

	public void setIdProductsDetails(String idProductsDetails) {
		this.idProductsDetails = idProductsDetails;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public Set<ProductImage> getProductImages() {
		return productImages;
	}

	public void setProductImages(Set<ProductImage> productImages) {
		this.productImages = productImages;
	}

	public Set<ProductSize> getProductSizes() {
		return productSizes;
	}

	public void setProductSizes(Set<ProductSize> productSizes) {
		this.productSizes = productSizes;
	}
	
	

}

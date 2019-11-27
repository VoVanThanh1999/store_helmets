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
	
	@Column(name="structure")
	private String structure;
	
	@Column(name="year_of_manufacture")
	private String yearOfManufacture;
	
//	Số lượng nhập vào
	@Column(name="number_entered")
	private int numberEntered;
	
//	số lượng đã bán
	@Column(name="quantity_sold")
	private int quantitySold;
//  số lượng tồn tại
	@Column(name="quantity_exists")
	private int	quantityExists;
	
//	thương hiệu
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

	/**
	 * @return the idProductsDetails
	 */
	public String getIdProductsDetails() {
		return idProductsDetails;
	}

	/**
	 * @param idProductsDetails the idProductsDetails to set
	 */
	public void setIdProductsDetails(String idProductsDetails) {
		this.idProductsDetails = idProductsDetails;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the structure
	 */
	public String getStructure() {
		return structure;
	}

	/**
	 * @param structure the structure to set
	 */
	public void setStructure(String structure) {
		this.structure = structure;
	}

	/**
	 * @return the yearOfManufacture
	 */
	public String getYearOfManufacture() {
		return yearOfManufacture;
	}

	/**
	 * @param yearOfManufacture the yearOfManufacture to set
	 */
	public void setYearOfManufacture(String yearOfManufacture) {
		this.yearOfManufacture = yearOfManufacture;
	}

	/**
	 * @return the numberEntered
	 */
	public int getNumberEntered() {
		return numberEntered;
	}

	/**
	 * @param numberEntered the numberEntered to set
	 */
	public void setNumberEntered(int numberEntered) {
		this.numberEntered = numberEntered;
	}

	/**
	 * @return the quantitySold
	 */
	public int getQuantitySold() {
		return quantitySold;
	}

	/**
	 * @param quantitySold the quantitySold to set
	 */
	public void setQuantitySold(int quantitySold) {
		this.quantitySold = quantitySold;
	}

	/**
	 * @return the quantityExists
	 */
	public int getQuantityExists() {
		return quantityExists;
	}

	/**
	 * @param quantityExists the quantityExists to set
	 */
	public void setQuantityExists(int quantityExists) {
		this.quantityExists = quantityExists;
	}

	/**
	 * @return the brand
	 */
	public String getBrand() {
		return brand;
	}

	/**
	 * @param brand the brand to set
	 */
	public void setBrand(String brand) {
		this.brand = brand;
	}

	/**
	 * @return the amount
	 */
	public int getAmount() {
		return amount;
	}

	/**
	 * @param amount the amount to set
	 */
	public void setAmount(int amount) {
		this.amount = amount;
	}

	/**
	 * @return the weight
	 */
	public String getWeight() {
		return weight;
	}

	/**
	 * @param weight the weight to set
	 */
	public void setWeight(String weight) {
		this.weight = weight;
	}

	/**
	 * @return the productImages
	 */
	public Set<ProductImage> getProductImages() {
		return productImages;
	}

	/**
	 * @param productImages the productImages to set
	 */
	public void setProductImages(Set<ProductImage> productImages) {
		this.productImages = productImages;
	}

	/**
	 * @return the productSizes
	 */
	public Set<ProductSize> getProductSizes() {
		return productSizes;
	}

	/**
	 * @param productSizes the productSizes to set
	 */
	public void setProductSizes(Set<ProductSize> productSizes) {
		this.productSizes = productSizes;
	}

	

}

package com.boss.storehelmets.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="product_image")
public class ProductImage {
	@Id
	@Column(name="id_product_image")
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid", strategy = "uuid")
	public String idProductImage;
	
	@Column(name="image_name")
	public String imageName;

	public String getIdProductImage() {
		return idProductImage;
	}

	public void setIdProductImage(String idProductImage) {
		this.idProductImage = idProductImage;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}
	
	
}

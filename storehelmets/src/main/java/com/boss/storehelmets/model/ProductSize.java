package com.boss.storehelmets.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="product_size")
public class ProductSize {
	@Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid", strategy = "uuid")
	
	@Column(name="id_product_size")
	private String idProductSize;
	
	@Column(name="size_name")
	private String sizeName;

	public String getIdProductSize() {
		return idProductSize;
	}

	public void setIdProductSize(String idProductSize) {
		this.idProductSize = idProductSize;
	}

	public String getSizeName() {
		return sizeName;
	}

	public void setSizeName(String sizeName) {
		this.sizeName = sizeName;
	}
	
	
	
	
}

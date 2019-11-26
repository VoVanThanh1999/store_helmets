package com.boss.storehelmets.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="caregory_details")
public class CategoryDetails {
	@Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid", strategy = "uuid")
	@Column(name="id_details_category")
	private String id;
	
	@Column(name="name_details_category")
	private String nameDetailsCategory;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNameDetailsCategory() {
		return nameDetailsCategory;
	}
	public void setNameDetailsCategory(String nameDetailsCategory) {
		this.nameDetailsCategory = nameDetailsCategory;
	}
	

}

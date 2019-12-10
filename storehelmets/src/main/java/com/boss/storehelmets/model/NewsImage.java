package com.boss.storehelmets.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="news_image")
public class NewsImage {
	@Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid", strategy = "uuid")
	@Column(name="id_image")
	public String idImage;
	
	@Column(name="image")
	public String image;
	/**
	 * @return the idImage
	 */
	public String getIdImage() {
		return idImage;
	}
	/**
	 * @param idImage the idImage to set
	 */
	public void setIdImage(String idImage) {
		this.idImage = idImage;
	}
	/**
	 * @return the image
	 */
	public String getImage() {
		return image;
	}
	/**
	 * @param image the image to set
	 */
	public void setImage(String image) {
		this.image = image;
	}
	
	
	
	
	
}

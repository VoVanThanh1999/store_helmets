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
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "news_details")
public class NewsDetails {
	
	@Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid", strategy = "uuid")	
	@Column(name="id_news_details")
	private String idNewsDetails;
	
	@Column(name="content")
	private String content;
	
	@Column(name="date_created")
	private Date dateCreated;
	
	@OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	@JoinColumn(name = "id_news_details")
	private Set<NewsImage> images; 

	/**
	 * @return the idNewsDetails
	 */
	public String getIdNewsDetails() {
		return idNewsDetails;
	}

	/**
	 * @param idNewsDetails the idNewsDetails to set
	 */
	public void setIdNewsDetails(String idNewsDetails) {
		this.idNewsDetails = idNewsDetails;
	}

	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * @return the dateCreated
	 */
	public Date getDateCreated() {
		return dateCreated;
	}

	/**
	 * @param dateCreated the dateCreated to set
	 */
	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	/**
	 * @return the images
	 */
	public Set<NewsImage> getImages() {
		return images;
	}

	/**
	 * @param images the images to set
	 */
	public void setImages(Set<NewsImage> images) {
		this.images = images;
	}

	/**
	 * @return the images
	 */
	
	
	

}

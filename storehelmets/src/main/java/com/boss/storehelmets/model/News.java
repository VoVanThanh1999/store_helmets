package com.boss.storehelmets.model;
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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "news")
public class News {
	@Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid", strategy = "uuid")
	@Column(name="id_news")
	private String idNews;
	
	@Column(name="title")
	private String title;
	
	@Column(name="id_user_update")
	private String idUserUpdate;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "news_category_details",joinColumns = @JoinColumn(name="id_news"),
		inverseJoinColumns = @JoinColumn(name="id_details_category")
	)
	private Set<CategoryDetails> categoryDetails;
	
	@OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	@JoinColumn(name="id_news_details")
	private NewsDetails newsDetails;
	
	@OneToOne()
	@JoinColumn(name="id_user")
	private User user;

	/**
	 * @return the idNews
	 */
	public String getIdNews() {
		return idNews;
	}

	/**
	 * @param idNews the idNews to set
	 */
	public void setIdNews(String idNews) {
		this.idNews = idNews;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
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

	/**
	 * @return the newsDetails
	 */
	public NewsDetails getNewsDetails() {
		return newsDetails;
	}

	/**
	 * @param newsDetails the newsDetails to set
	 */
	public void setNewsDetails(NewsDetails newsDetails) {
		this.newsDetails = newsDetails;
	}

	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}

	
	
	
	
}

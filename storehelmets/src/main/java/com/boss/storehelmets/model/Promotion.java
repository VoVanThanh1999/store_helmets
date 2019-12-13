package com.boss.storehelmets.model;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="promotion")
public class Promotion {
	@Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid", strategy = "uuid")
	@Column(name="id_promotion")
	private String idPromotion;
	
	@Column(name="name_promotion")
	private String namePromotion;
	
	@OneToOne
	private User userCreate;
	
	@OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	private PromotionDetails promotionDetails;

	/**
	 * @return the idPromotion
	 */
	public String getIdPromotion() {
		return idPromotion;
	}

	/**
	 * @param idPromotion the idPromotion to set
	 */
	public void setIdPromotion(String idPromotion) {
		this.idPromotion = idPromotion;
	}

	/**
	 * @return the namePromotion
	 */
	public String getNamePromotion() {
		return namePromotion;
	}

	/**
	 * @param namePromotion the namePromotion to set
	 */
	public void setNamePromotion(String namePromotion) {
		this.namePromotion = namePromotion;
	}

	/**
	 * @return the userCreate
	 */
	public User getUserCreate() {
		return userCreate;
	}

	/**
	 * @param userCreate the userCreate to set
	 */
	public void setUserCreate(User userCreate) {
		this.userCreate = userCreate;
	}

	/**
	 * @return the promotionDetails
	 */
	public PromotionDetails getPromotionDetails() {
		return promotionDetails;
	}

	/**
	 * @param promotionDetails the promotionDetails to set
	 */
	public void setPromotionDetails(PromotionDetails promotionDetails) {
		this.promotionDetails = promotionDetails;
	}

	
	
	
	

}

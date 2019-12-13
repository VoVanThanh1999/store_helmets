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
@Table(name="promotion_details")
public class PromotionDetails {
	@Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid", strategy = "uuid")
	@Column(name="id_promotion_details")
	private String idPromotionDetails;
	
	@Column(name="content")
	private String content;
	
	@Column(name="date_create")
	private Date dateCreate;
	
	@Column(name="date_end")
	private String dateEnd;
	
	@Column(name="percent_reduction")
	private int percentReduction;
	
	@OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	@JoinColumn(name="id_promotion_details")
	private Set<CodePromotion> codePromotion;

	/**
	 * @return the idPromotionDetails
	 */
	public String getIdPromotionDetails() {
		return idPromotionDetails;
	}

	/**
	 * @param idPromotionDetails the idPromotionDetails to set
	 */
	public void setIdPromotionDetails(String idPromotionDetails) {
		this.idPromotionDetails = idPromotionDetails;
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
	 * @return the dateCreate
	 */
	public Date getDateCreate() {
		return dateCreate;
	}

	/**
	 * @param dateCreate the dateCreate to set
	 */
	public void setDateCreate(Date dateCreate) {
		this.dateCreate = dateCreate;
	}

	/**
	 * @return the dateEnd
	 */
	public String getDateEnd() {
		return dateEnd;
	}

	/**
	 * @param dateEnd the dateEnd to set
	 */
	public void setDateEnd(String dateEnd) {
		this.dateEnd = dateEnd;
	}

	/**
	 * @return the percentReduction
	 */
	public int getPercentReduction() {
		return percentReduction;
	}

	/**
	 * @param percentReduction the percentReduction to set
	 */
	public void setPercentReduction(int percentReduction) {
		this.percentReduction = percentReduction;
	}

	/**
	 * @return the codePromotion
	 */
	public Set<CodePromotion> getCodePromotion() {
		return codePromotion;
	}

	/**
	 * @param codePromotion the codePromotion to set
	 */
	public void setCodePromotion(Set<CodePromotion> codePromotion) {
		this.codePromotion = codePromotion;
	}
	
	
	
	
}

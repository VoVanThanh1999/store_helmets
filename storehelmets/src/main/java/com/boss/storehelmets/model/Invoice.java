package com.boss.storehelmets.model;
import java.sql.Date;
import java.time.LocalDate;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="invoice")
public class Invoice {
	
	@Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid", strategy = "uuid")
	@Column(name="id_invoice")
	private String idInvoice;
	
	@Column(name="name_customer")
	private String nameCustomer;
	
	@Column(name="address1")
	private String address1;
	
	@Column(name="address2")
	private String address2;
	
	@Column(name="tel")
	private int tel;
	
	@Column(name="date_create")
	private Date dateCreat;
	
	@Column(name="email")
	@Email
	private String email;
	
	@Column(name="status")
	private boolean status;
	
	@OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	@JoinColumn(name="id_basket_total")
	BastketTotal bastketTotal;
	
	@OneToOne
	@JoinColumn(name="id_user")
	User userConfirm;
	
	
	/**
	 * @return the idInvoice
	 */
	public String getIdInvoice() {
		return idInvoice;
	}

	/**
	 * @param idInvoice the idInvoice to set
	 */
	public void setIdInvoice(String idInvoice) {
		this.idInvoice = idInvoice;
	}

	/**
	 * @return the nameCustomer
	 */
	public String getNameCustomer() {
		return nameCustomer;
	}

	/**
	 * @param nameCustomer the nameCustomer to set
	 */
	public void setNameCustomer(String nameCustomer) {
		this.nameCustomer = nameCustomer;
	}

	/**
	 * @return the address1
	 */
	public String getAddress1() {
		return address1;
	}

	/**
	 * @param address1 the address1 to set
	 */
	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	/**
	 * @return the address2
	 */
	public String getAddress2() {
		return address2;
	}

	/**
	 * @param address2 the address2 to set
	 */
	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	/**
	 * @return the tel
	 */
	public int getTel() {
		return tel;
	}

	/**
	 * @param tel the tel to set
	 */
	public void setTel(int tel) {
		this.tel = tel;
	}

	/**
	 * @return the dateCreat
	 */
	public Date getDateCreat() {
		return dateCreat;
	}

	/**
	 * @param dateCreat the dateCreat to set
	 */
	public void setDateCreat(Date dateCreat) {
		this.dateCreat = dateCreat;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the status
	 */
	public boolean isStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(boolean status) {
		this.status = status;
	}

	/**
	 * @return the bastketTotal
	 */
	public BastketTotal getBastketTotal() {
		return bastketTotal;
	}

	/**
	 * @param bastketTotal the bastketTotal to set
	 */
	public void setBastketTotal(BastketTotal bastketTotal) {
		this.bastketTotal = bastketTotal;
	}

	/**
	 * @return the userConfirm
	 */
	public User getUserConfirm() {
		return userConfirm;
	}

	/**
	 * @param userConfirm the userConfirm to set
	 */
	public void setUserConfirm(User userConfirm) {
		this.userConfirm = userConfirm;
	}

	

	
	
	
	
	
}
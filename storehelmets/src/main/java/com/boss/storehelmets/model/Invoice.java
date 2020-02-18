package com.boss.storehelmets.model;
import java.sql.Date;
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
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.validation.annotation.Validated;

@Entity
@Table(name="invoice")
public class Invoice {
	
	@Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid", strategy = "uuid")
	@Column(name="id_invoice")
	private String idInvoice;
	
	@Column(name="name_customer")
	@NotNull
	private String nameCustomer;
	
	@Column(name="address1")
	private String address1;
	
	@Column(name="address2")
	private String address2;
	
	@Column(name="tel")
	private int tel;
	
	@Column(name="note")
	private String note;
	
	@Column(name="date_create")
	private Date dateCreat;
	
	@Column(name="date_confirm")
	private Date dateConfirm;
	
	@Column(name="email")
	@Email
	private String email;
	
	@Column(name="statusConfim")
	private boolean	statusConfim;
	
	@Column(name="statusSuccess")
	private boolean	statusSuccess;
	
	@Column(name="statusTransport")
	private boolean	statusTransport;
	
	@Column(name="statusCancel")
	private boolean statusCancel;
	
	@OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	@JoinColumn(name="id_basket_total")
	BastketTotal bastketTotal;
	
	@OneToOne
	@JoinColumn(name="id_user")
	User userConfirm;
	
	@OneToOne
	User userCreate;
	
	@OneToOne
	private User nhanVienGiaoHang;
	
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

	public boolean isStatusConfim() {
		return statusConfim;
	}

	public void setStatusConfim(boolean statusConfim) {
		this.statusConfim = statusConfim;
	}

	public boolean isStatusSuccess() {
		return statusSuccess;
	}

	public void setStatusSuccess(boolean statusSuccess) {
		this.statusSuccess = statusSuccess;
	}

	public boolean isStatusTransport() {
		return statusTransport;
	}

	public void setStatusTransport(boolean statusTransport) {
		this.statusTransport = statusTransport;
	}

	public User getUserCreate() {
		return userCreate;
	}

	public void setUserCreate(User userCreate) {
		this.userCreate = userCreate;
	}

	public boolean isStatusCancel() {
		return statusCancel;
	}

	public void setStatusCancel(boolean statusCancel) {
		this.statusCancel = statusCancel;
	}
	
	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Date getDateConfirm() {
		return dateConfirm;
	}

	public void setDateConfirm(Date dateConfirm) {
		this.dateConfirm = dateConfirm;
	}

	public User getNhanVienGiaoHang() {
		return nhanVienGiaoHang;
	}

	public void setNhanVienGiaoHang(User nhanVienGiaoHang) {
		this.nhanVienGiaoHang = nhanVienGiaoHang;
	}
	

	
	
	
	
	
}

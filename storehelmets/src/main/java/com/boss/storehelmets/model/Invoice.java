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
	private boolean statusConfim;

	@Column(name="status_transport")
	private	boolean statusTransport;
	
	@Column(name="status_success")
	private boolean statusSuccess;
	
	@Column(name="total_money_invoice")
	private int totalMoneyInvoice;
	
	@OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	@JoinColumn(name="id_basket_total")
	BastketTotal bastketTotal;
	
	@OneToOne
	@JoinColumn(name="id_user")
	User userConfirm;

	public String getIdInvoice() {
		return idInvoice;
	}

	public void setIdInvoice(String idInvoice) {
		this.idInvoice = idInvoice;
	}

	public String getNameCustomer() {
		return nameCustomer;
	}

	public void setNameCustomer(String nameCustomer) {
		this.nameCustomer = nameCustomer;
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public int getTel() {
		return tel;
	}

	public void setTel(int tel) {
		this.tel = tel;
	}

	public Date getDateCreat() {
		return dateCreat;
	}

	public void setDateCreat(Date dateCreat) {
		this.dateCreat = dateCreat;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isStatusConfim() {
		return statusConfim;
	}

	public void setStatusConfim(boolean statusConfim) {
		this.statusConfim = statusConfim;
	}

	public boolean isStatusTransport() {
		return statusTransport;
	}

	public void setStatusTransport(boolean statusTransport) {
		this.statusTransport = statusTransport;
	}

	public boolean isStatusSuccess() {
		return statusSuccess;
	}

	public void setStatusSuccess(boolean statusSuccess) {
		this.statusSuccess = statusSuccess;
	}

	public BastketTotal getBastketTotal() {
		return bastketTotal;
	}

	public void setBastketTotal(BastketTotal bastketTotal) {
		this.bastketTotal = bastketTotal;
	}

	public User getUserConfirm() {
		return userConfirm;
	}

	public void setUserConfirm(User userConfirm) {
		this.userConfirm = userConfirm;
	}

	public int getTotalMoneyInvoice() {
		return totalMoneyInvoice;
	}

	public void setTotalMoneyInvoice(int totalMoneyInvoice) {
		this.totalMoneyInvoice = totalMoneyInvoice;
	}
	
	
	
	
	

	
	
	
	
	
}

package com.boss.storehelmets.model;

import java.sql.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;


@Entity
@Table(name="shipping_bill")
public class ShippingBill {
	
	@Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid", strategy = "uuid")
	@Column(name="id_shipping_bill")
	private String idShippingBill;
		
	@Column(name="total_money_invoice")
	private float totalMoneyInvoice;
	
	@Column(name="total_money_collected")
	private float totalMoneyCollected;
	
	@Column(name="total_money_not_yet_collected")
	private float totalMoneyNotYetCollected;
	
	@Column(name="date")
	private Date date;
	
	@OneToOne
	private User user;
	
	@ManyToMany
	private List<Invoice> invoices;

	public String getIdShippingBill() {
		return idShippingBill;
	}

	public void setIdShippingBill(String idShippingBill) {
		this.idShippingBill = idShippingBill;
	}

	public float getTotalMoneyInvoice() {
		return totalMoneyInvoice;
	}

	public void setTotalMoneyInvoice(float totalMoneyInvoice) {
		this.totalMoneyInvoice = totalMoneyInvoice;
	}

	public float getTotalMoneyCollected() {
		return totalMoneyCollected;
	}

	public void setTotalMoneyCollected(float totalMoneyCollected) {
		this.totalMoneyCollected = totalMoneyCollected;
	}

	public float getTotalMoneyNotYetCollected() {
		return totalMoneyNotYetCollected;
	}

	public void setTotalMoneyNotYetCollected(float totalMoneyNotYetCollected) {
		this.totalMoneyNotYetCollected = totalMoneyNotYetCollected;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Invoice> getInvoices() {
		return invoices;
	}

	public void setInvoices(List<Invoice> invoices) {
		this.invoices = invoices;
	}

	
	
}

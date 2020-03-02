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
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "history_create_shippingbill")
public class HistoryCreateShippingbill {

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = "id_history_create_shippingbill")
	private String idHistoryCreateShippingbill;

	@OneToOne
	@JoinColumn(name="id_admin_create")
	private User adminCreate;

	@OneToOne
	@JoinColumn(name="id_user_shipper")
	private User userShipper;

	@Column(name = "dateCreate")
	private Date date;

	@OneToOne
	private ShippingBill shippingBill;

	public String getIdHistoryCreateShippingbill() {
		return idHistoryCreateShippingbill;
	}

	public void setIdHistoryCreateShippingbill(String idHistoryCreateShippingbill) {
		this.idHistoryCreateShippingbill = idHistoryCreateShippingbill;
	}

	public User getAdminCreate() {
		return adminCreate;
	}

	public void setAdminCreate(User adminCreate) {
		this.adminCreate = adminCreate;
	}

	public User getUserShipper() {
		return userShipper;
	}

	public void setUserShipper(User userShipper) {
		this.userShipper = userShipper;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public ShippingBill getShippingBill() {
		return shippingBill;
	}

	public void setShippingBill(ShippingBill shippingBill) {
		this.shippingBill = shippingBill;
	}

	
}

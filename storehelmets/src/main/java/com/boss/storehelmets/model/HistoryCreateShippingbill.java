package com.boss.storehelmets.model;
import java.sql.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="history_create_shippingbill")
public class HistoryCreateShippingbill {
	
	@Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid", strategy = "uuid")
	@Column(name="id_history_event")
	private String idHistoryEvent;
	@OneToOne
	private User adminCreate;
	@OneToOne
	private User userShipper;
	private Date date;
	@OneToOne
	private ShippingBill shippingBill;
	
	public String getIdHistoryEvent() {
		return idHistoryEvent;
	}
	public void setIdHistoryEvent(String idHistoryEvent) {
		this.idHistoryEvent = idHistoryEvent;
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

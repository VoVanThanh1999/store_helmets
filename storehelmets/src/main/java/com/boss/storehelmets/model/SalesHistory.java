package com.boss.storehelmets.model;
import java.sql.Date;

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
@Table(name="history_store_event_details")
public class SalesHistory {
	
	@Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid", strategy = "uuid")
	@Column(name="id_history_store_details")
	private String id; 
	
//	san pham da ban 
	@OneToOne(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	private BastketTotal bastketTotal;
	
	@Column(name="thoi_gian_ban")
	private Date thoiGianBan;
	
	@OneToOne(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	private User user;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public BastketTotal getBastketTotal() {
		return bastketTotal;
	}

	public void setBastketTotal(BastketTotal bastketTotal) {
		this.bastketTotal = bastketTotal;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
	
}

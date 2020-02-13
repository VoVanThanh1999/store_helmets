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
@Table(name="history_comfirm_invoice")
public class HistoryComfirmInvoice {
	@Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid", strategy = "uuid")
	@Column(name="id_history_comfirm_invoice")
	private String idHistoryComfirmInvoice;
	
	@OneToOne()
	private User user;
	
	@OneToOne(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	private Invoice invoice;
	
	@Column(name="thoi_gian_duyet")
	private Date thoiGianDuyet;
	
	public String getIdHistoryComfirmInvoice() {
		return idHistoryComfirmInvoice;
	}

	public void setIdHistoryComfirmInvoice(String idHistoryComfirmInvoice) {
		this.idHistoryComfirmInvoice = idHistoryComfirmInvoice;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Invoice getInvoice() {
		return invoice;
	}

	public void setInvoice(Invoice invoice) {
		this.invoice = invoice;
	}

	public Date getThoiGianDuyet() {
		return thoiGianDuyet;
	}

	public void setThoiGianDuyet(Date thoiGianDuyet) {
		this.thoiGianDuyet = thoiGianDuyet;
	}
	
	
}

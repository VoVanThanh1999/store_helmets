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
@Table(name = "history_shipper_invoice")
public class HistoryShipperInvoice {

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = "id_history_shipper_invoice")
	private String idHistoryShipperInvoice;

	@OneToOne
	private Invoice invoice;

	@Column(name = "money_invoice")
	private float moneyInvoice;
	
	@Column(name="ngay_hoan_thanh")
	private Date ngayHoanThanh;
	
	public String getIdHistoryShipperInvoice() {
		return idHistoryShipperInvoice;
	}

	public void setIdHistoryShipperInvoice(String idHistoryShipperInvoice) {
		this.idHistoryShipperInvoice = idHistoryShipperInvoice;
	}

	public Invoice getInvoice() {
		return invoice;
	}

	public void setInvoice(Invoice invoice) {
		this.invoice = invoice;
	}

	public float getMoneyInvoice() {
		return moneyInvoice;
	}

	public void setMoneyInvoice(float moneyInvoice) {
		this.moneyInvoice = moneyInvoice;
	}

	public Date getNgayHoanThanh() {
		return ngayHoanThanh;
	}

	public void setNgayHoanThanh(Date ngayHoanThanh) {
		this.ngayHoanThanh = ngayHoanThanh;
	}
	
	
}	

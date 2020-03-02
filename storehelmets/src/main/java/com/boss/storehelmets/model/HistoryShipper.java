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
@Table(name = "history_shipper")
public class HistoryShipper {
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = "id_history_shipper")
	private String idHistoryShipper;

	@Column(name = "date")
	private Date date;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "id_history_shipper")
	private Set<HistoryShipperInvoice> historyShipperInvoices;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "id_history_shipper")
	private Set<HistoryShipperShippingBill> historyShipperShippingBills;

	public String getIdHistoryShipper() {
		return idHistoryShipper;
	}

	public void setIdHistoryShipper(String idHistoryShipper) {
		this.idHistoryShipper = idHistoryShipper;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Set<HistoryShipperInvoice> getHistoryShipperInvoices() {
		return historyShipperInvoices;
	}

	public void setHistoryShipperInvoices(Set<HistoryShipperInvoice> historyShipperInvoices) {
		this.historyShipperInvoices = historyShipperInvoices;
	}

	public Set<HistoryShipperShippingBill> getHistoryShipperShippingBills() {
		return historyShipperShippingBills;
	}

	public void setHistoryShipperShippingBills(Set<HistoryShipperShippingBill> historyShipperShippingBills) {
		this.historyShipperShippingBills = historyShipperShippingBills;
	}

}

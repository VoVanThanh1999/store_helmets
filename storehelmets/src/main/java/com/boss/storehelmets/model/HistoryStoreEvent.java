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
@Table(name="history_store_event")
public class HistoryStoreEvent {
	@Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid", strategy = "uuid")
	@Column(name="id_history_event")
	private String idHistoryEvent;
	
//	ngay tao su kien 
	@Column(name="date")
	private Date date;
	
	@Column(name="total_money_forday")
	private int totalMoneyForDay;
	
	@Column(name="total_money_sold")
	private double totalMoneySold;
	
	@Column(name="total_deductible_amount")
	private double totalDeductibleAmount;
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="id_history_event")
	private Set<SalesHistory> salesHistory;
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="id_history_event")
	private Set<HistoryImportProduct> productImportHistories;
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="id_history_event")
	private Set<HistoryComfirmInvoice> historyComfirmInvoices;
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="id_history_event")
	private Set<HistoryCreateShippingbill> historyCreateShippingbills;
	
	public String getIdHistoryEvent() {
		return idHistoryEvent;
	}

	public void setIdHistoryEvent(String idHistoryEvent) {
		this.idHistoryEvent = idHistoryEvent;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Set<SalesHistory> getSalesHistory() {
		return salesHistory;
	}

	public void setSalesHistory(Set<SalesHistory> salesHistory) {
		this.salesHistory = salesHistory;
	}

	public int getTotalMoneyForDay() {
		return totalMoneyForDay;
	}

	public void setTotalMoneyForDay(int totalMoneyForDay) {
		this.totalMoneyForDay = totalMoneyForDay;
	}
	
	public Set<HistoryImportProduct> getProductImportHistories() {
		return productImportHistories;
	}

	public void setProductImportHistories(Set<HistoryImportProduct> productImportHistories) {
		this.productImportHistories = productImportHistories;
	}	

	public double getTotalMoneySold() {
		return totalMoneySold;
	}

	public void setTotalMoneySold(double totalMoneySold) {
		this.totalMoneySold = totalMoneySold;
	}

	public double getTotalDeductibleAmount() {
		return totalDeductibleAmount;
	}

	public void setTotalDeductibleAmount(double totalDeductibleAmount) {
		this.totalDeductibleAmount = totalDeductibleAmount;
	}

	public Set<HistoryComfirmInvoice> getHistoryComfirmInvoices() {
		return historyComfirmInvoices;
	}

	public void setHistoryComfirmInvoices(Set<HistoryComfirmInvoice> historyComfirmInvoices) {
		this.historyComfirmInvoices = historyComfirmInvoices;
	}

	public Set<HistoryCreateShippingbill> getHistoryCreateShippingbills() {
		return historyCreateShippingbills;
	}

	public void setHistoryCreateShippingbills(Set<HistoryCreateShippingbill> historyCreateShippingbills) {
		this.historyCreateShippingbills = historyCreateShippingbills;
	}
	
	
	

	
	
	

	
	
	
	
	
	 
	
	
}

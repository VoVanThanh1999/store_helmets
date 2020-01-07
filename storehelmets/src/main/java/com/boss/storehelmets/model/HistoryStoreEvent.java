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
	private int totalMoneySold;
	
	@Column(name="total_deductible_amount")
	private int totalDeductibleAmount;
	
	@OneToMany(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	@JoinColumn(name="id_history_event")
	private Set<SalesHistory> salesHistory;
	
	@OneToMany(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	@JoinColumn(name="id_history_event")
	private Set<HistoryImportProduct> productImportHistories;

	
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

	public int getTotalMoneySold() {
		return totalMoneySold;
	}

	public void setTotalMoneySold(int totalMoneySold) {
		this.totalMoneySold = totalMoneySold;
	}

	public int getTotalDeductibleAmount() {
		return totalDeductibleAmount;
	}

	public void setTotalDeductibleAmount(int totalDeductibleAmount) {
		this.totalDeductibleAmount = totalDeductibleAmount;
	}

	
	
	

	
	
	
	
	
	 
	
	
}

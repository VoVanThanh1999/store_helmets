package com.boss.storehelmets.model;

import java.sql.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "shipping_bill")
public class ShippingBill {

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = "id_shipping_bill")
	private String idShippingBill;

	@Column(name = "total_money_invoice")
	private float totalMoneyInvoice;

	@Column(name = "total_money_collected")
	private float totalMoneyCollected;

	@Column(name = "total_money_not_yet_collected")
	private float totalMoneyNotYetCollected;

	@Column(name = "tien_da_huy")
	private float tienDaHuy;

	@Column(name = "date")
	private Date date;

	@Column(name = "statusShippingbill")
	private boolean statusShippingbill;

	@Column(name = "xac_nhan_tu_tai_xe")
	private boolean xacNhanTuTaiXe;

	@Column(name = "chuyen_cho_admin")
	private boolean chuyenChoAdmin;

	@Column(name = "ngay_hoan_thanh")
	private Date ngayHoanThanh;
	
	@Column(name="ngay_chuyen_cho_admin")
	private Date ngayChuyenChoAdmin;
	
	@Column(name="ngay_admin_xac_nhan_thanh_cong")
	private Date ngayAdminXacNhanThanhCong;
	
	@OneToOne
	private User adminCreate;

	@OneToOne
	private User shipper;

	@OneToOne
	private User adminXacNhanHoanThanhHoaDon;
	
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

	public List<Invoice> getInvoices() {
		return invoices;
	}

	public void setInvoices(List<Invoice> invoices) {
		this.invoices = invoices;
	}

	public User getAdminCreate() {
		return adminCreate;
	}

	public void setAdminCreate(User adminCreate) {
		this.adminCreate = adminCreate;
	}

	public User getShipper() {
		return shipper;
	}

	public void setShipper(User shipper) {
		this.shipper = shipper;
	}

	public boolean isStatusShippingbill() {
		return statusShippingbill;
	}

	public void setStatusShippingbill(boolean statusShippingbill) {
		this.statusShippingbill = statusShippingbill;
	}

	public Date getNgayHoanThanh() {
		return ngayHoanThanh;
	}

	public void setNgayHoanThanh(Date ngayHoanThanh) {
		this.ngayHoanThanh = ngayHoanThanh;
	}

	public boolean isXacNhanTuTaiXe() {
		return xacNhanTuTaiXe;
	}

	public void setXacNhanTuTaiXe(boolean xacNhanTuTaiXe) {
		this.xacNhanTuTaiXe = xacNhanTuTaiXe;
	}

	public float getTienDaHuy() {
		return tienDaHuy;
	}

	public void setTienDaHuy(float tienDaHuy) {
		this.tienDaHuy = tienDaHuy;
	}

	public boolean isChuyenChoAdmin() {
		return chuyenChoAdmin;
	}

	public void setChuyenChoAdmin(boolean chuyenChoAdmin) {
		this.chuyenChoAdmin = chuyenChoAdmin;
	}

	public Date getNgayChuyenChoAdmin() {
		return ngayChuyenChoAdmin;
	}

	public void setNgayChuyenChoAdmin(Date ngayChuyenChoAdmin) {
		this.ngayChuyenChoAdmin = ngayChuyenChoAdmin;
	}

	public Date getNgayAdminXacNhanThanhCong() {
		return ngayAdminXacNhanThanhCong;
	}

	public void setNgayAdminXacNhanThanhCong(Date ngayAdminXacNhanThanhCong) {
		this.ngayAdminXacNhanThanhCong = ngayAdminXacNhanThanhCong;
	}

	public User getAdminXacNhanHoanThanhHoaDon() {
		return adminXacNhanHoanThanhHoaDon;
	}

	public void setAdminXacNhanHoanThanhHoaDon(User adminXacNhanHoanThanhHoaDon) {
		this.adminXacNhanHoanThanhHoaDon = adminXacNhanHoanThanhHoaDon;
	}
	
	
	
}

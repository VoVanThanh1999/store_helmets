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
@Table(name = "history_shipper_shipping_bill")
public class HistoryShipperShippingBill {

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = "id_history_shipper_shipping_bill")
	public String idHistoryShipperShippingBill;

	@OneToOne
	public ShippingBill shippingBill;

	@Column(name = "so_tien_cua_bill")
	public float soTienCuaBill;

	@Column(name = "so_tien_thu_duoc")
	public float soTienThuDuoc;

	@Column(name = "so_tien_huy")
	public float soTienHuy;
	
	@Column(name="ngay_hoan_thanh")
	public Date ngayHoanThanh;

	public String getIdHistoryShipperShippingBill() {
		return idHistoryShipperShippingBill;
	}

	public void setIdHistoryShipperShippingBill(String idHistoryShipperShippingBill) {
		this.idHistoryShipperShippingBill = idHistoryShipperShippingBill;
	}

	public ShippingBill getShippingBill() {
		return shippingBill;
	}

	public void setShippingBill(ShippingBill shippingBill) {
		this.shippingBill = shippingBill;
	}

	public float getSoTienCuaBill() {
		return soTienCuaBill;
	}

	public void setSoTienCuaBill(float soTienCuaBill) {
		this.soTienCuaBill = soTienCuaBill;
	}

	public float getSoTienThuDuoc() {
		return soTienThuDuoc;
	}

	public void setSoTienThuDuoc(float soTienThuDuoc) {
		this.soTienThuDuoc = soTienThuDuoc;
	}

	public float getSoTienHuy() {
		return soTienHuy;
	}

	public void setSoTienHuy(float soTienHuy) {
		this.soTienHuy = soTienHuy;
	}

	public Date getNgayHoanThanh() {
		return ngayHoanThanh;
	}

	public void setNgayHoanThanh(Date ngayHoanThanh) {
		this.ngayHoanThanh = ngayHoanThanh;
	}

	
	
	
}

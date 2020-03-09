package com.boss.storehelmets.repository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.boss.storehelmets.model.ShippingBill;

@Repository
public interface ShippingBillRepository extends JpaRepository<ShippingBill, String>{
	@Query(value= "Select u FROM ShippingBill u Where u.shipper.idUser = :idUser")
	List<ShippingBill>  findShippingBillsByShipper(@Param("idUser") String idUser);
	
	@Query(value= "SELECT U FROM ShippingBill U WHERE U.xacNhanTuTaiXe = true and U.statusShippingbill= false and U.chuyenChoAdmin=true")
	List<ShippingBill> hienThiNhungHoaDonDangChoXacNhan();
	
	@Query(value= "SELECT U FROM ShippingBill U WHERE U.xacNhanTuTaiXe = true and U.statusShippingbill= true and U.chuyenChoAdmin=true")
	List<ShippingBill> hienThiNhungHoaDonDaGiaoThanhCong();
	/*List<ShippingBill> hienThiNhungHoaDonDaHoanThanh();*/
	
	@Query(value= "SELECT U FROM ShippingBill U WHERE U.xacNhanTuTaiXe = true and U.statusShippingbill= true and U.chuyenChoAdmin=true and  U.shipper.idUser = :idUser")
	List<ShippingBill> hienThiNhungHoaDonThanhCongCuaShipper(@Param("idUser") String idUser);
	
	
}

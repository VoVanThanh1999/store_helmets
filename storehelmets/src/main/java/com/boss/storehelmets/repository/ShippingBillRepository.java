package com.boss.storehelmets.repository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.boss.storehelmets.model.ShippingBill;

@Repository
public interface ShippingBillRepository extends JpaRepository<ShippingBill, String>{
	@Query(
		value= "Select * FROM ShippingBill u Where u.shipper.idUser = :idUser ",
		nativeQuery = true
			)
	List<ShippingBill>  findShippingBillsByShipper(@Param("idUser") String idUser);
}

package com.boss.storehelmets.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import org.springframework.data.repository.query.Param;

import org.springframework.stereotype.Repository;

import com.boss.storehelmets.model.Invoice;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, String>{
	
	@Query(
		value = "Select i From Invoice i where i.userCreate.idUser = : idUser",
			nativeQuery = true)
	public List<Invoice>  findInvoiceByUserIdCreate(@Param("idUser") String idUser);
	
	@Query(
			value="SELECT i FROM Invoice i Where i.statusConfim = true and i.statusSuccess= false and i.statusTransport = false"
			)
	Page<Invoice> findInvoiceGetStatusConfimIsTrueAndSuccessIsFalse(Pageable pageable);
	
	@Query(
		value = "Select count from Invoice"	,nativeQuery = true
			)
	public Integer countInvoice(); 
	
	@Query(
			value="Select i FROM Invoice i Where i.statusConfim = false"
			
			)
	Page<Invoice> findInvoiceAndGetInPage(Pageable pageable);
	
	
	@Query(
			value="SELECT i FROM Invoice i Where i.statusConfim = true and i.statusSuccess= false and i.statusTransport = true"
			)
	Page<Invoice> findInvoiceBeingShipped(Pageable pageable);
	
	@Query(
		value="SELECT i FROM Invoice i Where i.xacNhanHoanThanhTuAdmin = true and i.statusCancel= true"	
			)
	List<Invoice> hienThiDonDatHangDaHuy();
	
	@Query(
			value="SELECT i FROM Invoice i Where i.xacNhanHoanThanhTuAdmin = true and i.statusCancel= false"	
				)
	List<Invoice> hienThiHoaDonThanhCong();
}

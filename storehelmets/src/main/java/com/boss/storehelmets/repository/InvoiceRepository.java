package com.boss.storehelmets.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.boss.storehelmets.model.Invoice;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, String>{
	
	@Query(
		value="SELECT * FROM Invoice i Where i.StatusConfim = true",
			nativeQuery=true
	)
	List<Invoice> findInvoiceGetStatusConfimIsTrue();
}

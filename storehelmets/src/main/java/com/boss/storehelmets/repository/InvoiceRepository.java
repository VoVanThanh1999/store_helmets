package com.boss.storehelmets.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
<<<<<<< HEAD
import org.springframework.data.repository.query.Param;
=======
>>>>>>> 69f4ebac9dd39095ac195171f7566c3acda2acec
import org.springframework.stereotype.Repository;

import com.boss.storehelmets.model.Invoice;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, String>{
	
	@Query(
<<<<<<< HEAD
			value = "Select * From Invoice i where i.userCreate.idUser = : idUser",
			nativeQuery = true)
	public List<Invoice>  findInvoiceByUserIdCreate(@Param("idUser") String idUser);
=======
		value="SELECT * FROM Invoice i Where i.StatusConfim = true",
			nativeQuery=true
	)
	List<Invoice> findInvoiceGetStatusConfimIsTrue();
>>>>>>> 69f4ebac9dd39095ac195171f7566c3acda2acec
}

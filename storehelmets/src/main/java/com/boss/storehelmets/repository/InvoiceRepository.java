package com.boss.storehelmets.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.boss.storehelmets.model.Invoice;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, String>{

}

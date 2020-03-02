package com.boss.storehelmets.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.boss.storehelmets.model.HistoryCreateShippingbill;


@Repository
public interface HistoryCreateShippingbillRepository extends JpaRepository<HistoryCreateShippingbill, String>{

}

package com.boss.storehelmets.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.boss.storehelmets.model.BastketTotal;

@Repository
public interface BasketTotalRepository extends JpaRepository<BastketTotal, String>{

}

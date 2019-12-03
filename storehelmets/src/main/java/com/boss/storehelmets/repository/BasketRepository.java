package com.boss.storehelmets.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.boss.storehelmets.model.Basket;

@Repository
public interface BasketRepository extends JpaRepository<Basket, String>{

}

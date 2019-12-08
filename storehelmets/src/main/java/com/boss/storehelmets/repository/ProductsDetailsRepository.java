package com.boss.storehelmets.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.boss.storehelmets.model.ProductsDetails;

@Repository
public interface ProductsDetailsRepository extends JpaRepository<ProductsDetails, String>{

}

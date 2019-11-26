package com.boss.storehelmets.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.boss.storehelmets.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, String>{
	List<Product> findByNameProduct(String nameProduct);
	
}

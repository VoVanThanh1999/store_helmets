package com.boss.storehelmets.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.boss.storehelmets.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, String>{
	List<Product> findByNameProduct(String nameProduct);
	
//	get sản phẩm được nhiều người mua nhất
	@Query("Select u from Product  u order by u.productsDetails.quantityExists desc")
	List<Product> findByOrderByPopular();
	
///	get sản phẩm theo giá tăng dần
	@Query("Select u from Product  u order by u.productsDetails.amount asc")
	List<Product> findByOrderByAmountAsc();
	
//	get sản phẩm theo giá giảm dần
	@Query("Select u from Product  u order by u.productsDetails.amount desc")
	List<Product> findByOrderByAmountDest();
}

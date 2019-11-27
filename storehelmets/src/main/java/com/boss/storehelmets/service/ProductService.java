package com.boss.storehelmets.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;

import com.boss.storehelmets.model.Product;

public interface ProductService {
	public	List<Product> getAll();
	public Optional<Product> getById(String id);
	public List<Product> getProductNewlyAdd();
//	get sản phẩm được nhiều người mua nhất
	public List<Product> getProductByPopular();
	public List<Product> getByOrderByAmountAsc();
	public List<Product> getByOrderByAmountDesc();
}

package com.boss.storehelmets.service;

import java.util.List;
import java.util.Optional;

import com.boss.storehelmets.model.Product;

public interface ProductService {
	public	List<Product> getAll();
	public Optional<Product> getById(String id);
	
}

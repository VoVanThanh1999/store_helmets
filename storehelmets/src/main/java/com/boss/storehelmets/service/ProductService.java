package com.boss.storehelmets.service;

import java.util.List;

import com.boss.storehelmets.model.Product;

public interface ProductService {
	public	List<Product> getAll();
	public	List<Product> getByName(String nameProduct);
	
}

package com.boss.storehelmets.service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.boss.storehelmets.model.Product;
import com.boss.storehelmets.repository.ProductRepository;

@Service
public class ProductServiceImlp implements ProductService{
	@Autowired
	ProductRepository productRepository;
	
	@Override
	public List<Product> getAll() {
		// TODO Auto-generated method stub
		List<Product> product = productRepository.findAll()
								.stream()
								.collect(Collectors.toList());
		return product;
	}

	@Override
	public List<Product> getByName(String productName) {
		// TODO Auto-generated method stub
		List<Product> products = productRepository.findByNameProduct(productName);
		try {
			return products;
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println(e.getMessage());
		}
		return null;
	}

	

}

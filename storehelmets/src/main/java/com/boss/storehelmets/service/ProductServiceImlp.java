package com.boss.storehelmets.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.boss.storehelmets.model.Product;
import com.boss.storehelmets.repository.ProductRepository;

@Service
public class ProductServiceImlp implements ProductService{
	@Autowired
	ProductRepository productRepository;
	@Transactional
	@Override
	public List<Product> getAll() {
		// TODO Auto-generated method stub
		List<Product> product = productRepository.findAll()
								.stream()
								.collect(Collectors.toList());
		try {
			return product;
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}
	@Transactional
	@Override
	public Optional<Product> getById(String id) {
		// TODO Auto-generated method stub
		Optional<Product> optionalProduct=productRepository.findById(id);
		if (optionalProduct.isPresent()) {
			return optionalProduct;
		}
		return null;
		
	}

	

	

}

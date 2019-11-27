package com.boss.storehelmets.service;

import java.util.ArrayList;
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
		try {
			List<Product> product = productRepository.findAll()
					.stream()
					.collect(Collectors.toList());
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
//	get sản phẩm mới thêm vào
	@Transactional
	@Override
	public List<Product> getProductNewlyAdd() {
		// TODO Auto-generated method stub
		try {
			List<Product> products = productRepository.findAll();
			List<Product> products2 = new ArrayList<Product>();
			for (int i = products.size()-1; i >=0; i--) {
				products2.add(products.get(i));
			}
			return products2;
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}
	
//	get sản phẩm được nhiều người mua nhất
	@Transactional
	@Override
	public List<Product> getProductByPopular() {
		// TODO Auto-generated method stub
		try {
			List<Product> products = productRepository.findByOrderByPopular();
			return products;
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
		
	}
	@Transactional
	@Override
	public List<Product> getByOrderByAmountAsc() {
		// TODO Auto-generated method stub
		try {
			List<Product> products = productRepository.findByOrderByAmountAsc();
			return products;
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}
	
	@Transactional
	@Override
	public List<Product> getByOrderByAmountDesc() {
		// TODO Auto-generated method stub
		try {
			List<Product> products = productRepository.findByOrderByAmountDest();
			return products;
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}

	

	

}

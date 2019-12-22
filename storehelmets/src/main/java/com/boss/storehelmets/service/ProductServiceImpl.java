package com.boss.storehelmets.service;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Service;

import com.boss.storehelmets.app.utils.AppConstants;
import com.boss.storehelmets.model.Product;
import com.boss.storehelmets.model.User;
import com.boss.storehelmets.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService{
	@Autowired
	ProductRepository productRepository;
	
	@Transactional
	@Override
	@Cacheable(value = "products")
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
	@Cacheable(value = "products")
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
	@Cacheable(value = "products")
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
	@Cacheable(value = "products")
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
	@Cacheable(value = "products")
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
	
	@Transactional
	@Override
	public String deleteProduct(String id) {
		// TODO Auto-generated method stub
		try {
			Optional<Product> optionalProduct = getById(id);
			productRepository.delete(optionalProduct.get());
			return "xoa thanh cong";
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println(e.getMessage());
		}
		return null;
	}
	
	@Transactional
	@Override
	public String updateProduct(Product productInput,User user) {
		// TODO Auto-generated method stub
		try {
			Product product = getById(productInput.getIdProduct()).get();
			if (product!= null) {
				product.setNameProduct(productInput.getNameProduct());
				product.setProductsDetails(productInput.getProductsDetails());
				product.setIdUserUpdate(user.getIdUser());
				productRepository.save(product);
			
			}
			return AppConstants.SUCCESS_UPDATE;	
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println(e.getMessage());
			return null;
		}

	}
	
	@Transactional
	@Override
	public String addProduct(Product product) {
		// TODO Auto-generated method stub
		try {
			if (product != null) {
				productRepository.save(product);
				return AppConstants.SUCCESS_CREATE;
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		return null;
	}

}

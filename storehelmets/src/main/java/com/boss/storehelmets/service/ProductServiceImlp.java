package com.boss.storehelmets.service;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.boss.storehelmets.model.Product;
import com.boss.storehelmets.model.User;
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
				product.setCategoryDetails(productInput.getCategoryDetails());
				product.setProductsDetails(productInput.getProductsDetails());
				product.setIdUserUpdate(user.getIdUser());
				productRepository.save(product);
			
			}
			return "update thanh cong";	
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println(e.getMessage());
			return null;
		}

	}
	
	@Transactional
	@Override
	public String addProduct(Product productInput, User user) {
		// TODO Auto-generated method stub
		try {
			if (productInput != null && user!= null) {
				Product product = new Product();
				Date date1 = new Date();
				java.sql.Date date = new java.sql.Date(date1.getYear(), date1.getMonth(), date1.getDate());
				product.setNameProduct(productInput.getNameProduct());
				product.setDateCreate(date);
				product.setProductsDetails(productInput.getProductsDetails());
				product.setCategoryDetails(productInput.getCategoryDetails());
				product.setUserCreat(user);
				productRepository.save(product);
				return "them thanh cong";
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		return null;
	}

}

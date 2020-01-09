package com.boss.storehelmets.service;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import com.boss.storehelmets.app.utils.AppConstants;
import com.boss.storehelmets.model.HistoryImportProduct;
import com.boss.storehelmets.model.HistoryStoreEvent;
import com.boss.storehelmets.model.Product;
import com.boss.storehelmets.model.SalesHistory;
import com.boss.storehelmets.model.User;
import com.boss.storehelmets.repository.HistoryStoreEventRepository;
import com.boss.storehelmets.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService{
	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	HistoryStoreEventRepository  historyStoreEventRepository;
	
	@Override
	@Cacheable(value  = "products")
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
	@Cacheable(value  = "products")
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
	@Override
	@Cacheable(value  = "products")
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
	@Override
	@Cacheable(value  = "products")
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
	
	@Override
	@Cacheable(value  = "products")
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
	
	@CacheEvict(value = "products",allEntries = true)
	@CachePut
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
	

	@CachePut
	@CacheEvict(value = "products",allEntries = true)
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
	
	@CachePut
	@CacheEvict(value = "products",allEntries = true)
	@Transactional
	@Override
	public String addProduct(Product product,User user) {
		// TODO Auto-generated method stub
		try {
			if (product != null) {
				java.util.Date dateData = new java.util.Date();
				Date date = new Date(dateData.getYear(), dateData.getMonth(), dateData.getDate());
			
				if (historyStoreEventRepository.findByDate(date) == null) {
					HistoryStoreEvent historyStoreEvent = new HistoryStoreEvent();
					Set<HistoryImportProduct> productImportHistories = new HashSet<>();
					Set<SalesHistory> salesHistories = new HashSet<>();
					historyStoreEvent.setSalesHistory(salesHistories);
					HistoryImportProduct historyImportProduct = new HistoryImportProduct();
					historyImportProduct.setProducts(product);
					historyImportProduct.setUser(user);
					productImportHistories.add(historyImportProduct);
					historyStoreEvent.setProductImportHistories(productImportHistories);
					int priceProduct = product.getProductsDetails().getAmount();
					historyStoreEvent.setTotalMoneySold(priceProduct);
					historyStoreEventRepository.save(historyStoreEvent);
				}else {
					HistoryStoreEvent event = historyStoreEventRepository.findByDate(date);
					Set<HistoryImportProduct> importProducts = event.getProductImportHistories();
					HistoryImportProduct historyImportProduct = new HistoryImportProduct();
					historyImportProduct.setProducts(product);
					historyImportProduct.setUser(user);
					importProducts.add(historyImportProduct);
					event.setProductImportHistories(importProducts);
					float priceProduct = (float) (event.getTotalMoneySold() + product.getProductsDetails().getAmount());
					event.setTotalMoneySold(priceProduct);
					historyStoreEventRepository.save(event);
				}
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

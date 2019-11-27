package com.boss.storehelmets.service;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.boss.storehelmets.model.Category;
import com.boss.storehelmets.model.CategoryDetails;
import com.boss.storehelmets.model.Product;
import com.boss.storehelmets.repository.CategoryRepository;
import com.boss.storehelmets.repository.ProductRepository;
@Service
public class CategoryServiceImlp implements CategoryService{
	@Autowired
	CategoryRepository categoryRepository;
	
	@Autowired
	ProductRepository productRepository;
	
	@Transactional
	@Override
	public List<Category> getAllCategory() {
		// TODO Auto-generated method stub
		List<Category> categories = categoryRepository.findAll()
					.stream()
				.collect(Collectors.toList());
		return categories;
	}
	
//	get chi tiết danh mục bằng id
	@Transactional
	@Override
	public CategoryDetails getCategoryDetailsById(String id) {
		// TODO Auto-generated method stub
		List<Category> categories = categoryRepository.findAll();	
		try {
			for (Category category : categories) {
				Set<CategoryDetails> categoryDetails =  category.getDetailsCategories();
				for (CategoryDetails categoryDetail : categoryDetails) {
					if (categoryDetail.getId().equalsIgnoreCase(id)) {
						return  categoryDetail;
					}
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		return null;
	}
	
//	get sản phẩm bằng id chi tiết danh mục có danh mục sản phẩm
	@Transactional
	@Override
	public List<Product> getProductsByCategoryDetails(String id) {
		// TODO Auto-generated method stub
		try {
			List<Product> products = productRepository.findAll().stream()
					.filter(e->e.getProductsDetails().getIdProductsDetails().equalsIgnoreCase(id))
					.collect(Collectors.toList());
			return products;
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}
	

}

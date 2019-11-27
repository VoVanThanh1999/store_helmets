package com.boss.storehelmets.service;

import java.util.List;
import com.boss.storehelmets.model.Category;
import com.boss.storehelmets.model.CategoryDetails;
import com.boss.storehelmets.model.Product;

public interface CategoryService {
	public List<Category> getAllCategory();
	
	public CategoryDetails getCategoryDetailsById(String id);
	
	public List<Product> getProductsByCategoryDetails(String id);
}

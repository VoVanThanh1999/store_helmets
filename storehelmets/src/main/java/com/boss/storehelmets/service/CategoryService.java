package com.boss.storehelmets.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.boss.storehelmets.model.Category;
import com.boss.storehelmets.model.CategoryDetails;
import com.boss.storehelmets.model.Product;
import com.boss.storehelmets.model.User;

public interface CategoryService {
	public List<Category> getAllCategory();
	
	public CategoryDetails getCategoryDetailsById(String id);
	
	public List<Product> getProductsByCategoryDetails(String id);
	
	public String addCategory(Category category, User user);
	
	public String deleteCategory(String id);
	
	public String updateCategory(Category category, User user);

	Optional<Category> getCategoryById(String id);

	String updateCategoryDetails(CategoryDetails categoryDetails, User user);

	String deleteCategoryDetails(String id);

	String addCategoryDetails(CategoryDetails categoryDetailsInput);
	
	List<CategoryDetails> getCategoryDetailsByIdCategory(String id);
	
	Set<Product> getProductByIdCategoryDetails(String id);
}

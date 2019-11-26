package com.boss.storehelmets.service;

import java.util.List;
import com.boss.storehelmets.model.Category;
import com.boss.storehelmets.model.CategoryDetails;

public interface CategoryService {
	public List<Category> getAllCategory();
	
	public List<CategoryDetails> getCategoryDetatailsById(String id);
}

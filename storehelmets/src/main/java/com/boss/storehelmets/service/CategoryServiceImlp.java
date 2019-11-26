package com.boss.storehelmets.service;


import java.util.List;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.boss.storehelmets.model.Category;
import com.boss.storehelmets.model.CategoryDetails;
import com.boss.storehelmets.repository.CategoryRepository;
@Service
public class CategoryServiceImlp implements CategoryService{
	@Autowired
	CategoryRepository categoryRepository;
	
	@Transactional
	@Override
	public List<Category> getAllCategory() {
		// TODO Auto-generated method stub
		List<Category> categories = categoryRepository.findAll()
					.stream()
				.collect(Collectors.toList());
		return categories;
	}
	
	@Transactional
	@Override
	public List<CategoryDetails> getCategoryDetatailsById(String id) {
		// TODO Auto-generated method stub
		try {
			List<Category> categories = categoryRepository.findAll();			
			for (Category category : categories) {
				List<CategoryDetails> categoryDetails = category.getDetailsCategories().stream()
										.filter(e->e.getId().equalsIgnoreCase(id))
										.collect(Collectors.toList());
				return categoryDetails;
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		return null;
	}
	

}

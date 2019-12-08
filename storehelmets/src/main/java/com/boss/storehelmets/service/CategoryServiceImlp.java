package com.boss.storehelmets.service;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.boss.storehelmets.model.Category;
import com.boss.storehelmets.model.CategoryDetails;
import com.boss.storehelmets.model.Product;
import com.boss.storehelmets.model.User;
import com.boss.storehelmets.repository.CategoryDetailsRepository;
import com.boss.storehelmets.repository.CategoryRepository;
import com.boss.storehelmets.repository.ProductRepository;
@Service
public class CategoryServiceImlp implements CategoryService{
	@Autowired
	CategoryRepository categoryRepository;
	
	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	CategoryDetailsRepository categoryDetailsRepository;
	
	@Transactional
	@Override
	public Optional<Category> getCategoryById(String id){
		Optional<Category> category = categoryRepository.findById(id);
		if (category!= null) {
			return category;
		}
		return null;
	}
	
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
//			List<Product> products = productRepository.findAll().stream()
//					.filter(e->e.getProductsDetails().stream().filter(productDetails ->productDetails.getIdProductsDetails().equalsIgnoreCase(id)).collect(Collectors.toList()))
//					.collect(Collectors.toList());
//			return products;
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
		return null;
	}
	
	@Transactional
	@Override
	public String addCategory(Category categoryInput, User user) {
		// TODO Auto-generated method stub
		if (categoryInput!= null) {
			Category category = new Category();
			category.setNameCategory(categoryInput.getNameCategory());
			category.setDetailsCategories(categoryInput.getDetailsCategories());
			category.setUser(user);
			categoryRepository.save(category);
			return "them thanh cong";
		}
		return null;
	}
	
	@Transactional
	@Override
	public String deleteCategory(String id) {
		// TODO Auto-generated method stub
		try {
			if (getCategoryById(id) != null) {
				Optional<Category> category = getCategoryById(id);
				categoryRepository.delete(category.get());
				return "xoa thanh cong";
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println(e.getMessage());
		}
		return null;
	}
	
	@Transactional
	@Override
	public String updateCategory(Category categoryInput, User user) {
		// TODO Auto-generated method stub
		try {
			Optional<Category> category = getCategoryById(categoryInput.getIdCategory());
			category.ifPresent(e ->{
				e.setNameCategory(categoryInput.getNameCategory());
				e.setDetailsCategories(categoryInput.getDetailsCategories());
				e.setUser(user);
			});
			categoryRepository.save(category.get());
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println(e.getMessage());
		}
		return null;
	}
	
	@Transactional
	@Override
	public String updateCategoryDetails(CategoryDetails categoryDetails,User user) {
		try {
			Optional<CategoryDetails> optional = categoryDetailsRepository.findById(categoryDetails.getId());
			optional.ifPresent(e->{
				e.setNameDetailsCategory(categoryDetails.getNameDetailsCategory());
			});
			categoryDetailsRepository.save(optional.get());
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println(e.getMessage());
		}
		return null;
	}
	
	@Transactional
	@Override
	public String addCategoryDetails( CategoryDetails categoryDetailsInput) {
		try {
			CategoryDetails categoryDetails = new CategoryDetails();
			categoryDetails.setNameDetailsCategory(categoryDetailsInput.getNameDetailsCategory());
			Optional<Category> optionalCategory = getCategoryById(categoryDetailsInput.getCategory().getIdCategory());
			if (optionalCategory.isPresent()) {
				categoryDetails.setCategory(optionalCategory.get());
				categoryDetailsRepository.save(categoryDetails);
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return null;
	}
	
	@Transactional
	@Override
	public String deleteCategoryDetails(String id) {
		try {
			Optional<CategoryDetails> categoryDetails = categoryDetailsRepository.findById(id);
			if (categoryDetails!= null) {
				categoryDetailsRepository.deleteById(id);
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println(e.getMessage());
			return null;
		}
		return null;
	}

}

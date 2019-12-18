package com.boss.storehelmets.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.boss.storehelmets.model.CategoryDetails;

@Repository
public interface CategoryDetailsRepository extends JpaRepository<CategoryDetails, String>{
	
	@Query("SELECT u FROM CategoryDetails u where u.category.idCategory=:id")
	List<CategoryDetails> findCategoryDetailsByCategoryId(@Param("id") String id);
	

}

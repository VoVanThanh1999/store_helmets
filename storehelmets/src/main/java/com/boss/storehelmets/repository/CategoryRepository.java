package com.boss.storehelmets.repository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.boss.storehelmets.model.Category;
import com.boss.storehelmets.model.User;

@Repository
public interface CategoryRepository extends JpaRepository<Category, String>{
	List<Category> findByNameCategory(String name);
	
}

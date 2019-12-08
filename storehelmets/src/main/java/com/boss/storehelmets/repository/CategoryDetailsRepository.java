package com.boss.storehelmets.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.boss.storehelmets.model.CategoryDetails;

@Repository
public interface CategoryDetailsRepository extends JpaRepository<CategoryDetails, String>{

}

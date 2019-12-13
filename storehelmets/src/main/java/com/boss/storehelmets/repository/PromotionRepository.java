package com.boss.storehelmets.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.boss.storehelmets.model.Promotion;

@Repository
public interface PromotionRepository extends JpaRepository<Promotion, String>{
	
	

}

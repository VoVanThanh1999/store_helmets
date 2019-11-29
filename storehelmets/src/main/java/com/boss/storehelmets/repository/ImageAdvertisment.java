package com.boss.storehelmets.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.boss.storehelmets.model.ImageOfAdvertisment;

@Repository
public interface ImageAdvertisment extends JpaRepository<ImageOfAdvertisment, String>{
	
}

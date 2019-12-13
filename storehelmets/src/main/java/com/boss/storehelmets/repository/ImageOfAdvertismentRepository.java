package com.boss.storehelmets.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.boss.storehelmets.model.ImageOfAdvertisment;

@Repository
public interface ImageOfAdvertismentRepository extends JpaRepository<ImageOfAdvertisment, String>{

}

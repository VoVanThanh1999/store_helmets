package com.boss.storehelmets.service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boss.storehelmets.app.utils.AppConstants;
import com.boss.storehelmets.model.ImageOfAdvertisment;
import com.boss.storehelmets.model.User;
import com.boss.storehelmets.repository.ImageOfAdvertismentRepository;

@Service
public class ImageOfAdverstismentServiceImpl implements ImageOfAdverstismentService{
	@Autowired
	ImageOfAdvertismentRepository imageOfAdvertismentRepository;
	
	@Override
	public List<ImageOfAdvertisment> getAll() {
		// TODO Auto-generated method stub
		return imageOfAdvertismentRepository.findAll();
	}

	@Override
	public String addImageAdverstiment(ImageOfAdvertisment advertisment, User user) {
		// TODO Auto-generated method stub
		imageOfAdvertismentRepository.save(advertisment);
		return AppConstants.SUCCESS_CREATE;
	}

	

}

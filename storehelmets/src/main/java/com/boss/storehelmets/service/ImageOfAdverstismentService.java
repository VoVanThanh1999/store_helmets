package com.boss.storehelmets.service;

import java.util.List;

import com.boss.storehelmets.model.ImageOfAdvertisment;
import com.boss.storehelmets.model.User;

public interface ImageOfAdverstismentService {
	public List<ImageOfAdvertisment> getAll();
	public String addImageAdverstiment(ImageOfAdvertisment advertisment, User user);


}

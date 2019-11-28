package com.boss.storehelmets.service;

import com.boss.storehelmets.dto.UserDto;
import com.boss.storehelmets.model.User;

public interface UserSevice {
	public boolean checkUserExist(User user);
	
	public String registerAccount(UserDto userDto);
}

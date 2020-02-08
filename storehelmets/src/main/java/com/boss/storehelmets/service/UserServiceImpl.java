package com.boss.storehelmets.service;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.boss.storehelmets.dto.UserDto;
import com.boss.storehelmets.model.Roles;
import com.boss.storehelmets.model.User;
import com.boss.storehelmets.repository.UserRepository;

@Service
public class UserServiceImpl implements UserSevice{
	@Autowired
	UserRepository userRepository;
	
	@Transactional
	@Override
	public boolean checkUserExist(User user) {
		// TODO Auto-generated method stub
		try {
			Optional<User> optionalUser = userRepository.findByEmail(user.getEmail());
			if (optionalUser.isPresent()) {
				return false;
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println(e.getMessage());
		}
		return true;
	}

	@Transactional
	@Override
	public String registerAccount(@Valid UserDto userDto) {
		// TODO Auto-generated method stub
		try {

			User user = new User();
			user.setEmail(userDto.getEmail());
			user.setFullName(userDto.getFullName());
			user.setPassword(userDto.getPassword());
			user.setTel(userDto.getTel());
			user.setGender(userDto.getGender());
			user.setAddress1(userDto.getAddress1());
			user.setAddress2(userDto.getAddress2());
			Roles role = new Roles();
			role.setRoleName("ROLE_USER");
			Set<Roles> roles = new HashSet<Roles>();
			roles.add(role);
			user.setRoles(roles);
			if (checkUserExist(user)) {
				userRepository.save(user);
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println(e.getMessage());
		}
		return null;
	}
	
	@Cacheable("user")
	@Override
	public Optional<User> findUserById(String id) {
		// TODO Auto-generated method stub
		Optional<User> user = userRepository.findById(id);
		return user;
	}

	@Override
	public Optional<User> findUserByEmail(String email) {
		// TODO Auto-generated method stub
		return userRepository.findByEmail(email);
	}



}

package com.boss.storehelmets.service;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.boss.storehelmets.model.CustomUserDetails;
import com.boss.storehelmets.model.User;
import com.boss.storehelmets.repository.UserRepository;
@Service
public class UserDetailServiceImpl implements UserDetailsService{
	@Autowired
	UserRepository userRepository;
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		Optional<User> optionalUser = userRepository.findByEmail(username);
		if (!optionalUser.isPresent()) {
			return null;
		}
		CustomUserDetails customUserDetail = new CustomUserDetails(optionalUser);
		return customUserDetail;
	}
	
	public UserDetails loadUserById(String idUser) {
		Optional<User> optionalUser = userRepository.findById(idUser);
		if (!optionalUser.isPresent()) {
			return null;
		}
		CustomUserDetails customUserDetail = new CustomUserDetails(optionalUser);
		return customUserDetail;
	}

	

}

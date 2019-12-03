package com.boss.storehelmets.application;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.boss.storehelmets.model.Roles;
import com.boss.storehelmets.model.User;
import com.boss.storehelmets.repository.UserRepository;


@SpringBootApplication
@EntityScan("com.boss.storehelmets.model")
@ComponentScan(basePackages = {"com.boss.storehelmets.dto",
						"com.boss.storehelmets.securityconfig",
					"com.boss.storehelmets.securityjwt",
				  "com.boss.storehelmets.repository",
				"com.boss.storehelmets.user.resources",
			"com.boss.storehelmets.service"})
@EnableJpaRepositories(basePackages = {"com.boss.storehelmets.repository"})
public class StorehelmetsApplication implements CommandLineRunner{
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	public static void main(String[] args) {
		SpringApplication.run(StorehelmetsApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
//		User user = new User();
//		user.setFullName("Vo van thanh");
//		user.setEmail("thanhboss@yahoo.com");
//		user.setPassword(passwordEncoder.encode("123456"));
//		Set<Roles> roles = new  HashSet<Roles>();
//		Roles role = new Roles();
//		role.setRoleName("ROLE_USER");
//		roles.add(role);
//		user.setRoles(roles);
//		userRepository.save(user);
	}

}

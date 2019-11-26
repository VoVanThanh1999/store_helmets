package com.boss.storehelmets.application;

import java.util.HashSet;
import java.util.Set;

import javax.management.relation.Role;

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
	
		
	}

}

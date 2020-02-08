package com.boss.storehelmets.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.boss.storehelmets.repository.UserRepository;
import com.boss.storehelmets.service.InvoiceService;


@SpringBootApplication
@EntityScan("com.boss.storehelmets.model")
@ComponentScan(basePackages = {"com.boss.storehelmets.dto",
					"com.boss.storehelmets.securityconfig",
					 "com.boss.storehelmets.securityjwt",
				   "com.boss.storehelmets.repository",
				 "com.boss.storehelmets.user.resources",
			   "com.boss.storehelmets.admin.resources",
			  "com.boss.storehelmets.app.utils",
			"com.boss.storehelmets.app.utils",
			"com.boss.storehelmets.springmvc.config",
		 "com.boss.storehelmets.service",
		"com.boss.storehelmets.exception"})
@EnableJpaRepositories(basePackages = {"com.boss.storehelmets.repository"})
@ComponentScan("com.boss.storehelmets.admin.resources")
@EnableCaching
public class StorehelmetsApplication implements CommandLineRunner{
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	InvoiceService invoiceService;
	
	@Autowired
	UserRepository userRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(StorehelmetsApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
//		User user = new User(); 
//		user.setEmail("mrt.boss@yahoo.com");
//		user.setPassword(passwordEncoder.encode("123456"));
//		Set<Roles> roles = new HashSet<Roles>();	
//		Roles role = new Roles();
//		role.setRoleName("ROLE_ADMIN"); 
//		roles.add(role);
		
//		user.setRoles(roles);
//		userRepository.save(user);
		invoiceService.deleteInvoice();																											

	}

}

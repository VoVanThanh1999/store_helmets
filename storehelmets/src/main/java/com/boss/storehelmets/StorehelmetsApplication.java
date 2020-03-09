package com.boss.storehelmets;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.boss.storehelmets.model.Roles;
import com.boss.storehelmets.model.User;
import com.boss.storehelmets.repository.InvoiceRepository;
import com.boss.storehelmets.repository.ShippingBillRepository;
import com.boss.storehelmets.repository.UserRepository;
import com.boss.storehelmets.service.InvoiceService;

@SpringBootApplication
@EnableCaching
public class StorehelmetsApplication implements CommandLineRunner{
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	InvoiceService invoiceService;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	InvoiceRepository invoiceRepository;
	
	@Autowired
	ShippingBillRepository shippingBillRepository;

	
	public static void main(String[] args) {
		SpringApplication.run(StorehelmetsApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		
		/*User user = new User(); 
		user.setEmail("taixene@yahoo.com");
		user.setPassword(passwordEncoder.encode("123456"));
		user.setFullName("Bac tai");
		Set<Roles> roles = new HashSet<Roles>();	
		Roles role = new Roles();	
		role.setRoleName("ROLE_ADMIN"); 
		roles.add(role);
		Roles roles2 = new Roles();
		roles2.setRoleName("ROLE_SHIPPER");
		roles.add(roles2);
		user.setRoles(roles);
		userRepository.save(user);
		invoiceService.deleteInvoice();		*/																			
	}
	
}

package com.boss.storehelmets.securityconfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.boss.storehelmets.securityjwt.JwtAuthenticationFilter;
import com.boss.storehelmets.service.UserDetailServiceImlp;

@EnableWebSecurity
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
	@Autowired
	UserDetailServiceImlp userDetailService;
	
	@Bean
	protected BCryptPasswordEncoder passwordEncoder() {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		return bCryptPasswordEncoder;
	}
	
	 @Bean
	 public JwtAuthenticationFilter jwtAuthenticationFilter() {
		 return new com.boss.storehelmets.securityjwt.JwtAuthenticationFilter();
	 }
	 @Bean
	 @Override
	 public AuthenticationManager authenticationManagerBean() throws Exception {
	        // Get AuthenticationManager Bean
	        return super.authenticationManagerBean();
	 }
	 

	 @Override
	 protected void configure(HttpSecurity http) throws Exception {
	    http.csrf().disable();
	    http.authorizeRequests().antMatchers("/api/v1/user/login",
	    		"logout",
	    		"/api/v1/user/category",
	    		"/api/v1/user/category/{id}",
	    		"/api/v1/user/products",
	    		"/api/v1/user/products/{id}",
	    		"/api/v1/user/products/{id}",
	    		"/api/v1/user/products/newlyadded",
	    		"/api/v1/user/products/popular",
	    		"/api/v1/user/products/amountdesc",
	    		"/api/v1/user/products/amountasc",
	    		"/api/v1/user/products/categorydetails/{id}",
	    		"/api/v1/user/products/basket",
	    		"/api/v1/user/news",
	    		"/api/v1/user/news/{id}",
	    		"/api/v1/user/news/newspost",
	    		"/api/v1/user/basket",
	    		"/api/v1/user/basket/{id}",
	    		"/api/v1/user/basket/total",
	    		"/api/v1/user/basket/invoice"
	    		).permitAll();
	    
	    http.authorizeRequests().antMatchers(
	    		"/api/users"
	    	).access("hasAnyRole('ROLE_ADMIN','ROLE_USER')");
	    http.authorizeRequests()
	    .antMatchers("/api/user/invoice").access("hasRole('ROLE_USER')")
	    .anyRequest().authenticated();
	    
	    http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
	 }
}

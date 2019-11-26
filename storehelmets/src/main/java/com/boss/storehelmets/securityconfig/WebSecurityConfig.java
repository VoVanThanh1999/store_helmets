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
	    		"/api/v1/products",
	    		"/api/v1/products/{id}",
	    		"/api/v1/category"
	    		).permitAll();
	    http.authorizeRequests().antMatchers("/api/users").access("hasAnyRole('ROLE_ADMIN','ROLE_USER')");
	    http.authorizeRequests()
	    .antMatchers("/admin").access("hasRole('ROLE_ADMIN')");	   
	    http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
	 }
}

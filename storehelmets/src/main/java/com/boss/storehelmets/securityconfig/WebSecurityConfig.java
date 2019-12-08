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
	    		"/api/v1/admin/login",
	    		"logout",
	    		"/api/v1/user/categorys",
	    		"/api/v1/user/categorys/{id}",
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
	    		"/api/v1/user/baskets",
	    		"/api/v1/user/baskets/{id}",
	    		"/api/v1/user/baskets/total",
	    		"/api/v1/user/baskets/invoice"
	    		).permitAll();
	    
	    http.authorizeRequests().antMatchers(
	    		"/api/v1/user/invoice"
	    	).access("hasAnyRole('ROLE_ADMIN','ROLE_USER')");
	    http.authorizeRequests()
	    .antMatchers(
	    		"/api/v1/admin/products/{id}",
	    		"/api/v1/admin/products",
	    		"/api/v1/admin/categorys",
	    		"/api/v1/admin/categorys/{id}",
	    		"/api/v1/admin/categorys/details",
	    		"/api/v1/admin/categorys/details/{id}"
	    		).access("hasRole('ROLE_ADMIN')")
	    .anyRequest().authenticated();
	    http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
	 }
}

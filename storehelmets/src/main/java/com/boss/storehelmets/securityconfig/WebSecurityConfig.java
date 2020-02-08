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
import com.boss.storehelmets.service.UserDetailServiceImpl;

@EnableWebSecurity
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
	@Autowired
	UserDetailServiceImpl userDetailService;
	
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
	    http.authorizeRequests().antMatchers("/api/v1/users/login",
	    		"/",
	    		"/thanh-toan",
	    		"/login",
	    		"/chi-tiet-san-pham",
	    		"/chi-tiet-gio-hang",
	    		"/api/v1/users/advertisments",
	    		"/api/v1/users/resources/image/{fileName:.+}",
	    		"/api/v1/admin/login",
	    		"logout",
	    		"/favicon.ico",
	    		"/static/css/style.css",
	    		"/api/v1/users/username",
	    		"/api/v1/users/categorys",
	    		"/api/v1/users/categorys/{id}",
	    		"/api/v1/users/categorys/details/{id}",
	    		"/api/v1/users/products",
	    		"/api/v1/users/products/{id}",
	    		"/api/v1/users/products/{id}",
	    		"/api/v1/users/products/generalCategorys/{idProduct}",
	    		"/api/v1/users/products/newlyadded",
	    		"/api/v1/users/products/popular",
	    		"/api/v1/users/products/amountdesc",
	    		"/api/v1/users/products/amountasc",
	    		"/api/v1/users/products/categorydetails/{id}",
	    		"/api/v1/users/products/basket",
	    		"/api/v1/users/news",
	    		"/api/v1/users/news/{id}",
	    		"/api/v1/users/news/newspost",
	    		"/api/v1/users/baskets",
	    		"/api/v1/users/baskets/{id}",
	    		"/api/v1/users/baskets/total",
	    		"/api/v1/users/auth/me"
	    		
	    		).permitAll();
	    
	    http.authorizeRequests().antMatchers(
	    		"/api/v1/users/invoice",
	    		"/api/v1/users/baskets/invoice/{iduser}",
	    		"/api/v1/users/baskets/invoice"
	    	).access("hasAnyRole('ROLE_ADMIN','ROLE_USER')");
	    http.authorizeRequests()
	    .antMatchers(
	    		"/admins/index",
	    		"/api/v1/admins/advertisments",
	    		"/api/v1/admins/news",
	    		"/api/v1/admins/news/{id}",
	    		"/api/v1/admins/products/{id}",
	    		"/api/v1/admins/products",
	    		"/api/v1/admins/categorys",
	    		"/api/v1/admins/categorys/{id}",
	    		"/api/v1/admins/categorys/details",
	    		"/api/v1/admins/categorys/details/{id}"
	    		).access("hasRole('ROLE_ADMIN')")
	    .anyRequest().authenticated();
	    http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
	 }
}

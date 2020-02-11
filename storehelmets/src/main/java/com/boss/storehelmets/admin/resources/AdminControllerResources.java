package com.boss.storehelmets.admin.resources;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/admins")
public class AdminControllerResources {
	
	@RequestMapping("/trangchu")
	public ModelAndView defaultIndex() {
		ModelAndView modelAndView = new ModelAndView("index_admin");
		
		return modelAndView;
	}
	
	@RequestMapping(value="/login")
	public String login() {
		return "login_admin";
	}
	
	
	
	
	
}

package com.boss.storehelmets.admin.resources;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/admin")
public class AdminControllerResources {
	
	@RequestMapping(value="/login")
	public String login() {
		return "login_admin";
	}
	
	@RequestMapping(value="/index")
	public ModelAndView defaultIndex() {
		ModelAndView modelAndView = new ModelAndView("index_admin");
		
		return modelAndView;
	}
	
	
	
}

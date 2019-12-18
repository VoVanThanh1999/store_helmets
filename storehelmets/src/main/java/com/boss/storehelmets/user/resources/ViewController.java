package com.boss.storehelmets.user.resources;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/")
public class ViewController {

	@RequestMapping(value = "/")
	public ModelAndView defaultView() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("product");
		return modelAndView;
	}

}

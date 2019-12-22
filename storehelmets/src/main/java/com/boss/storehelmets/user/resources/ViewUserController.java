package com.boss.storehelmets.user.resources;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.boss.storehelmets.model.Product;
import com.boss.storehelmets.service.ProductService;

@Controller
@RequestMapping(value = "/")
public class ViewUserController {
	
	@Autowired
	ProductService productService;
	
	@RequestMapping(value = "/")
	public ModelAndView defaultView() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("product");
		return modelAndView;
	}
	
	@RequestMapping(value = "/chi-tiet-san-pham")
	public ModelAndView viewProduct(@RequestParam(value = "id") String id) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("detail-product");
		Optional<Product> product = productService.getById(id);
		modelAndView.addObject("product", product.get());
		
		return modelAndView;
	}
}

package com.boss.storehelmets.user.resources;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.boss.storehelmets.model.Product;
import com.boss.storehelmets.service.ProductService;

@Controller
@RestController
@RequestMapping("/api/v1")
public class RestApiProductController {
	@Autowired
	private ProductService productService;
	
	
	@RequestMapping(value = "/products",method = RequestMethod.GET)
	public List<Product>   getAllProduct(Model model) {
		try {
			List<Product> products = productService.getAll();
			model.addAttribute("products", products);
			return products;
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}
	
	@RequestMapping(value = "/products/{id}",method = RequestMethod.GET)
	public Optional<Product> getProductId(@PathVariable("id") String id,Model model ){
		try {
			Optional<Product> product = productService.getById(id);
			model.addAttribute("product", product);
			return product;
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
		
	}
	

}

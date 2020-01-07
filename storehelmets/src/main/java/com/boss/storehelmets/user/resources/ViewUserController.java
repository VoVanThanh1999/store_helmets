package com.boss.storehelmets.user.resources;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import com.boss.storehelmets.model.Product;
import com.boss.storehelmets.repository.CategoryDetailsRepository;
import com.boss.storehelmets.service.CategoryService;
import com.boss.storehelmets.service.ProductService;

@Controller
@RequestMapping(value = "/")
public class ViewUserController {
	
	@Autowired
	ProductService productService;
	
	@Autowired
	CategoryDetailsRepository categoryDetailsRepository; 
	
	@Autowired
	CategoryService categoryService;
	
	@RequestMapping(value = "/")
	public ModelAndView defaultView() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("home-page");
		return modelAndView;
	}
	
	@RequestMapping(value = "/chi-tiet-san-pham")
	public ModelAndView viewProduct(@RequestParam(value = "id") String id) {
		try {
			ModelAndView modelAndView = new ModelAndView();
			modelAndView.setViewName("detail-product");
			Optional<Product> productOptinal = productService.getById(id);
			
			modelAndView.addObject("images", productOptinal.get().getProductsDetails().getProductImages());
			modelAndView.addObject("product", productOptinal.get());
			return modelAndView;
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println(e.getMessage());
			return null;
		}
	}
	
	@RequestMapping(value="/chi-tiet-gio-hang")
	public ModelAndView thongTinGioHang() {
		try {
			ModelAndView andView = new ModelAndView("chitietgiohang");
			return andView;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
			return null;
		}
	}
}

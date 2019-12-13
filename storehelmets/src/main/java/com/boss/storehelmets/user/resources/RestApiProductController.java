package com.boss.storehelmets.user.resources;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import com.boss.storehelmets.app.utils.AppConstants;
import com.boss.storehelmets.model.ImageOfAdvertisment;
import com.boss.storehelmets.model.Product;
import com.boss.storehelmets.model.ProductImage;
import com.boss.storehelmets.service.CategoryService;
import com.boss.storehelmets.service.FileStorageService;
import com.boss.storehelmets.service.ImageOfAdverstismentService;
import com.boss.storehelmets.service.ProductService;

@Controller
@RestController
@RequestMapping("/api/v1/users")
public class RestApiProductController {
	@Autowired
	private ProductService productService;
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private FileStorageService fileStorageService; 
	
	@Autowired
	private ImageOfAdverstismentService imageOfAdverstismentService;
	
	@RequestMapping(value = "/products",method = RequestMethod.GET)
	private ModelAndView loadAllProduct() {
		try {
			List<Product> products = productService.getAll();
			ModelAndView modelAndView = new ModelAndView();
			modelAndView.setViewName("product");
			modelAndView.addObject("products", products);
			return modelAndView;
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}
	
	@RequestMapping(value = "/products/{id}",method = RequestMethod.GET)
	private Optional<Product> loadProductById(@PathVariable("id") String id,Model model ){
		try {
			Optional<Product> product = productService.getById(id);
			Set<ProductImage> images = product.get().getProductsDetails().getProductImages();
			System.out.println(images);
			model.addAttribute("images", product);
			return product;
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}
	
	@RequestMapping(value = "/products/categorydetails/{id}",method = RequestMethod.GET)
	private List<Product> loadProductsByCategoryDetailsId(@PathVariable("id")String id){
		try {
			List<Product> products = categoryService.getProductsByCategoryDetails(id);
			return products;
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}
	
	@RequestMapping(value = "/products/newlyadded",method = RequestMethod.GET)
	private List<Product> loadProductNewlyAdd (){
		try {
			List<Product> products = productService.getProductNewlyAdd();
			return products;
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}
	
	@RequestMapping(value = "/products/popular",method = RequestMethod.GET)
	private List<Product> loadProductByPopular(){
		try {
			List<Product> products = productService.getProductByPopular();
			return products;
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}
	
	@RequestMapping(value = "products/amountasc",method = RequestMethod.GET)
	private List<Product> loadProductByAmountAsc(){
		try {
			List<Product> products = productService.getByOrderByAmountAsc();
			return products;
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}
	
	@RequestMapping(value = "products/amountdesc",method = RequestMethod.GET)
	private List<Product> loadProductByAmountDesc(){
		try {
			List<Product> products = productService.getByOrderByAmountDesc();
			return products;
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}

	@RequestMapping(value = "/resources/image/{fileName:.+}", method = RequestMethod.GET)
	public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request) {
		Resource resource = fileStorageService.loadFileAsResource(fileName);
		String contentType = null;
		try {
			contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		if (contentType == null) {
			contentType = AppConstants.DEFAULT_CONTENT_TYPE;
		}
		return ResponseEntity.ok().contentType(MediaType.parseMediaType(contentType))
				.header(HttpHeaders.CONTENT_DISPOSITION,
						String.format(AppConstants.FILE_DOWNLOAD_HTTP_HEADER, resource.getFilename()))
				.body(resource);
	}
	
	@RequestMapping(value = "/advertisments",method = RequestMethod.GET)
	public List<ImageOfAdvertisment> loadAllAdvertisment(){
		try {
			List<ImageOfAdvertisment> advertisments = imageOfAdverstismentService.getAll();
			return advertisments;
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println(e.getMessage());
		}
		return null;
	}


}

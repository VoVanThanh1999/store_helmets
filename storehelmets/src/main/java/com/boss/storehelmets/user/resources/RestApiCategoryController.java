package com.boss.storehelmets.user.resources;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.boss.storehelmets.model.Category;
import com.boss.storehelmets.model.CategoryDetails;
import com.boss.storehelmets.service.CategoryService;

@Controller
@RestController
@RequestMapping("/api/v1/user")
public class RestApiCategoryController {
	
	@Autowired
	CategoryService categoryService;
	
	@RequestMapping(value = "category",method = RequestMethod.GET)
	private List<Category> loadAllCategory(){
		try {
			List<Category> categories = categoryService.getAllCategory();
			return categories;
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}
	
	@RequestMapping(value = "category/{id}",method = RequestMethod.GET)
	private CategoryDetails loadCategoryDetailsByid(@PathVariable("id") String id){
		try {
			CategoryDetails categories = categoryService.getCategoryDetailsById(id);
			return categories;
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}
	
	

}

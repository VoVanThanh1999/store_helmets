package com.boss.storehelmets.user.resources;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.boss.storehelmets.model.Category;
import com.boss.storehelmets.model.CategoryDetails;
import com.boss.storehelmets.service.CategoryService;

@Controller
@RestController
@RequestMapping("/api/v1/users")
public class RestApiCategoryController {
	
	@Autowired
	CategoryService categoryService;
	
	@RequestMapping(value = "/categorys",method = RequestMethod.GET)
	private List<Category> loadAllCategory(ModelMap modelMap){
		try {
			List<Category> categories = categoryService.getAllCategory();
			modelMap.addAttribute("categories", categories);
			return categories;
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}
	
	@RequestMapping(value = "categorys/{id}",method = RequestMethod.GET)
	private CategoryDetails loadCategoryDetailsByid(@PathVariable("id") String id){
		try {
			CategoryDetails categories = categoryService.getCategoryDetailsById(id);
			return categories;
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}
	
	@RequestMapping(value = "/categorys/details/{id}",method = RequestMethod.GET)
	private List<CategoryDetails> getCategoryDetails(@PathVariable("id")String id){
		try {
			List<CategoryDetails> categoryDetails = categoryService.getCategoryDetailsByIdCategory(id);
			return categoryDetails;
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}

}

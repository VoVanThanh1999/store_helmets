package com.boss.storehelmets.user.resources;
import java.util.List;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import com.boss.storehelmets.dto.BasketDto;
import com.boss.storehelmets.model.Product;
import com.boss.storehelmets.model.User;
import com.boss.storehelmets.repository.CategoryDetailsRepository;
import com.boss.storehelmets.securityjwt.JwtAuthenticationFilter;
import com.boss.storehelmets.securityjwt.JwtTokenProvider;
import com.boss.storehelmets.service.BasketDtoService;
import com.boss.storehelmets.service.CategoryService;
import com.boss.storehelmets.service.ProductService;
import com.boss.storehelmets.service.UserSevice;

@Controller
@RequestMapping(value = "/")
public class ViewUserController {
	
	@Autowired
	ProductService productService;
	
	@Autowired
	CategoryDetailsRepository categoryDetailsRepository; 
	
	@Autowired
	CategoryService categoryService;
	
	@Autowired
	BasketDtoService basketDtoService; 
	
	@Autowired(required = false)
    AuthenticationManager authenticationManager;;
    
	@Autowired
    private JwtTokenProvider tokenProvider;
	
	@Autowired
	JwtAuthenticationFilter authenticationFilter;
	
	@Autowired
	private UserSevice userSevice;
	
	@RequestMapping(value = "/")
	public ModelAndView defaultView() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("home-page");
		return modelAndView;
	}
	
	@RequestMapping(value="/login")
	public String loginUser(HttpServletRequest httpServletRequest) {
		try {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			String username = auth.getName();
			Optional<User> optional = userSevice.findUserByEmail(username);
			if (optional.get()!=null) {
				return "redirect:/";
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}	
		return "login-user";
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
	
	@RequestMapping(value="/thanh-toan")
	public String thanhToan(HttpServletRequest httpServletRequest,ModelMap modelMap) {
		try {
			HttpSession session = httpServletRequest.getSession();
			List<BasketDto> basketDtoSession = (List<BasketDto>) session.getAttribute("basketDtoSession");
			if(basketDtoSession != null) {
				modelMap.addAttribute("basketDtos", basketDtoSession);
				modelMap.addAttribute("basketDtoTotal",  basketDtoService.getTotalBasketDto(httpServletRequest));
				return "thanhtoan";
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		return "redirect:/";
	}
}

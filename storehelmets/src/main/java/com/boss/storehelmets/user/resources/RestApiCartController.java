package com.boss.storehelmets.user.resources;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.boss.storehelmets.dto.BasketDto;
import com.boss.storehelmets.model.Product;
import com.boss.storehelmets.service.BasketDtoService;


@Controller
@RestController
@RequestMapping("/api/v1")
public class RestApiCartController {
	
	@Autowired
	BasketDtoService basketDtoService;
	
	@RequestMapping(value = "/basket",method = RequestMethod.POST)
	public String addProductToCart(@RequestBody Product product,HttpServletRequest httpServletRequest) {
		HttpSession httpSession = httpServletRequest.getSession();
		try {
			BasketDto basketDto = new BasketDto();
			basketDto.setIdBasket(product.getIdProduct());
			basketDto.setNameProduct(product.getNameProduct());
			basketDto.setPrice(product.getProductsDetails().getAmount());
			basketDtoService.addProductToBasket(basketDto, httpServletRequest);
			return "them thanh cong";
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}

		
	}
	@RequestMapping(value = "/basket",method = RequestMethod.GET)
	public List<BasketDto> getBasketProduct (HttpServletRequest httpServletRequest) {
		try {
			HttpSession httpSession = httpServletRequest.getSession();
			List<BasketDto> basketDtoSession = (List<BasketDto>) httpSession.getAttribute("basketDtoSession");
			int number = (int) httpSession.getAttribute("numberOfCart");
			System.out.println(number);
			return basketDtoSession;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		return null;
	}

}

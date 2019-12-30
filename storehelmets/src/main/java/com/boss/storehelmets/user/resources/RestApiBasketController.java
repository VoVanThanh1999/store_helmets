package com.boss.storehelmets.user.resources;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.boss.storehelmets.app.utils.AppConstants;
import com.boss.storehelmets.dto.BasketDto;
import com.boss.storehelmets.dto.BastketDtoTotal;
import com.boss.storehelmets.model.Product;
import com.boss.storehelmets.model.ProductImage;
import com.boss.storehelmets.repository.ProductRepository;
import com.boss.storehelmets.service.BasketDtoService;


@Controller
@RestController
@RequestMapping("/api/v1/users")
public class RestApiBasketController {
	
	@Autowired
	BasketDtoService basketDtoService;
	
	@Autowired
	ProductRepository productRepository;
	
	@RequestMapping(value = "/baskets",method = RequestMethod.POST)
	private String addProductToCart(@RequestBody Product productDTO,HttpServletRequest httpServletRequest) {
		HttpSession httpSession = httpServletRequest.getSession();
		try {
			BasketDto basketDto = new BasketDto();
			basketDto.setIdBasket(productDTO.getIdProduct());
			basketDto.setNameProduct(productDTO.getNameProduct());
			for (int i = 0; i < 1; i++) {
				ProductImage image = productDTO.getProductsDetails().getProductImages().iterator().next();
				basketDto.setImageProduct(image.getImageName());
			}
			basketDto.setPrice(productDTO.getProductsDetails().getAmount());
			basketDtoService.addProductToBasket(basketDto, httpServletRequest);
		
			return AppConstants.SUCCESS_BASKET;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
			return AppConstants.ERROR_UPDATE;
		}
		
	}
	@RequestMapping(value = "/baskets",method = RequestMethod.GET)
	private List<BasketDto> getBasketProduct (HttpServletRequest httpServletRequest) {
		try {
			HttpSession session = httpServletRequest.getSession();
			List<BasketDto> basketDtoSession = (List<BasketDto>) session.getAttribute("basketDtoSession");
			session.setAttribute("numberOfCart", basketDtoSession.size());
			return basketDtoSession;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		return null;
	}
	
	@RequestMapping(value = "/baskets/{id}",method = RequestMethod.DELETE)
	private String delelteBasketProduct(HttpServletRequest httpServletRequest,@PathVariable("id") String id) {
		try {
			basketDtoService.deleteProductInBasket(id, httpServletRequest);
			return AppConstants.SUCCESS_DELETE;
		} catch (Exception e) {
			// TODO: handle exception
			return AppConstants.ERROR_DELETE;
		}
	}
	
	@RequestMapping(value = "/baskets",method = RequestMethod.PUT)
	private String updateBasketProduct(HttpServletRequest httpServletRequest, @RequestBody BasketDto basketDto) {
		try {
			basketDtoService.updateProductInBasket(basketDto, httpServletRequest);
			return  AppConstants.SUCCESS_UPDATE;
		} catch (Exception e) {
			// TODO: handle exception
			return AppConstants.ERROR_UPDATE;
		}
	}
	
	@RequestMapping(value = "/baskets/total",method = RequestMethod.GET)
	private BastketDtoTotal getBastketDtoTotal (HttpServletRequest request) {
		try {
			BastketDtoTotal basketDetailsDto = basketDtoService.getTotalBasketDto(request);
			return basketDetailsDto;
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}
	
}

package com.boss.storehelmets.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boss.storehelmets.dto.BasketDto;
import com.boss.storehelmets.dto.BastketDtoTotal;
import com.boss.storehelmets.model.Product;

@Service
public class BasketDtoServiceImpl implements BasketDtoService {
	static List<BasketDto> basketDtos = new ArrayList<BasketDto>();

	@Autowired
	ProductService productService;

	@Override
	public void addProductToBasket(BasketDto basketInput, HttpServletRequest request) {
		// TODO Auto-generated method stub
		int i = 0;
		HttpSession session = request.getSession();
		List<BasketDto> basketDtoSession = (List<BasketDto>) session.getAttribute("basketDtoSession");
		Optional<Product> product = productService.getById(basketInput.getIdBasket());
		if (product.get() != null && product.get().getProductsDetails().getQuantityExists() > 1) {
			if (basketInput != null) {
				if (basketDtoSession == null) {
					BasketDto basketDto = new BasketDto();
					basketDto.setIdBasket(basketInput.getIdBasket());
					basketDto.setNameProduct(basketInput.getNameProduct());
					basketDto.setPrice(basketInput.getPrice());
					basketDto.setImageProduct(basketInput.getImageProduct());
					basketDto.setNumOfCart(+1);
					float totalMoney = basketDto.getPrice() * basketDto.getNumOfCart();
					basketDto.setTotalMoney(totalMoney);
					basketDtos.add(basketDto);
					session.setAttribute("basketDtoSession", basketDtos);
				} else {
					if (checkProductToBasket(basketInput, request) == null) {
						BasketDto basketDto = new BasketDto();
						basketDto.setIdBasket(basketInput.getIdBasket());
						basketDto.setNameProduct(basketInput.getNameProduct());
						basketDto.setPrice(basketInput.getPrice());
						basketDto.setImageProduct(basketInput.getImageProduct());
						basketDto.setNumOfCart(+1);
						basketDto.setPrice(basketInput.getPrice());
						float totalMoney = basketDto.getPrice() * basketDto.getNumOfCart();
						basketDto.setTotalMoney(totalMoney);
						basketDtos.add(basketDto);
						session.setAttribute("basketDtoSession", basketDtos);
					} else {
						BasketDto basketDto = checkProductToBasket(basketInput, request);
						basketDto.setNumOfCart(basketDto.getNumOfCart() + 1);
						float totalMoney = basketDto.getPrice() * basketDto.getNumOfCart();
						basketDto.setTotalMoney(totalMoney);
						for (BasketDto basket : basketDtos) {
							if (basket.getIdBasket().equals(basketDto.getIdBasket())) {
								basketDtos.set(i, basketDto);
							}
							i++;
						}
						session.setAttribute("basketDtoSession", basketDtos);
					}
				}
			}
		}

	}

	@Override
	public BasketDto checkProductToBasket(BasketDto basketDto, HttpServletRequest request) {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		List<BasketDto> basketDtos = (List<BasketDto>) session.getAttribute("basketDtoSession");
		for (BasketDto basket : basketDtos) {
			if (basket.getIdBasket().equals(basketDto.getIdBasket())) {
				return basket;
			}
		}
		return null;
	}

	@Override
	public void deleteProductInBasket(String id, HttpServletRequest request) {
		// TODO Auto-generated method stub
		try {
			HttpSession session = request.getSession();
			List<BasketDto> basketDtos = (List<BasketDto>) session.getAttribute("basketDtoSession");
			for (BasketDto basket : basketDtos) {
				if (basket.getIdBasket().equalsIgnoreCase(id)) {
					basketDtos.remove(basket);
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			return;
		}
	}

	@Override
	public void updateProductInBasket(BasketDto basketDto, HttpServletRequest request) {
		// TODO Auto-generated method stub
		try {
			HttpSession session = request.getSession();
			List<BasketDto> basketDtos = (List<BasketDto>) session.getAttribute("basketDtoSession");
			for (BasketDto basket : basketDtos) {
				if (basket.getIdBasket().equalsIgnoreCase(basketDto.getIdBasket())) {
					basket.setNumOfCart(basketDto.getNumOfCart());
					float totalMoney = basketDto.getNumOfCart() * basket.getPrice();
					basket.setTotalMoney(totalMoney);
				}
			}
			session.setAttribute("basketDtoSession", basketDtos);
		} catch (Exception e) {
			// TODO: handle exception
			return;
		}
	}

	@Override
	public BastketDtoTotal getTotalBasketDto(HttpServletRequest request) {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		List<BasketDto> basketDtos = (List<BasketDto>) session.getAttribute("basketDtoSession");
		if (basketDtos != null) {
			float totalMoneyBasket = 0;
			if (basketDtos != null) {
				BastketDtoTotal basketDetailsDto = new BastketDtoTotal();
				basketDetailsDto.setBasketDtos(basketDtos);
				for (BasketDto basketDto : basketDtos) {
					totalMoneyBasket += basketDto.getTotalMoney();
				}
				basketDetailsDto.setTotalMoneyBasket(totalMoneyBasket);
				session.setAttribute("basketDetailsDto", basketDetailsDto);
				return basketDetailsDto;
			}
		}
		return null;
	}

}

package com.boss.storehelmets.service;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.boss.storehelmets.dto.BasketDto;
import com.boss.storehelmets.dto.BastketDtoTotal;
import com.boss.storehelmets.model.Basket;
import com.boss.storehelmets.model.BastketTotal;
import com.boss.storehelmets.model.Invoice;
import com.boss.storehelmets.model.User;
import com.boss.storehelmets.repository.BasketRepository;
import com.boss.storehelmets.repository.BasketTotalRepository;
import com.boss.storehelmets.repository.InvoiceRepository;
import com.boss.storehelmets.repository.ProductRepository;

@Service
public class InvoiceServiceImlp implements InvoiceService{
	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	InvoiceRepository invoiceRepository;
	
	@Autowired
	BasketDtoService basketDtoService;
	
	@Autowired
	BasketTotalRepository basketTotalRepository;
	
	@Autowired
	BasketRepository basketRepository;
	
	@Transactional
	@Override
	public void inserNewInvoice(HttpServletRequest request, User user) {
		// TODO Auto-generated method stub
		try {
			HttpSession session = request.getSession();
			BastketDtoTotal bastketDtoTotal = basketDtoService.getTotalBasketDto(request);
			List<BasketDto> basketDtoSession = (List<BasketDto>) session.getAttribute("basketDtoSession");
			if (basketDtoSession != null ) {
				Invoice invoice = new Invoice();
				invoice.setNameCustomer(user.getFullName());
				invoice.setEmail(user.getEmail());
				invoice.setAddress1(user.getAddress1());
				invoice.setAddress2(user.getAddress2());
				invoice.setStatus(false);
				invoice.setTel(user.getTel());
				BastketTotal bastketTotal = new BastketTotal();
				Set<Basket> batkets = new HashSet<Basket>();
				for (BasketDto basketDto : basketDtoSession) {
					Basket basket = new Basket();
					basket.setPrice(basketDto.getPrice());
					basket.setIdProduct(basketDto.getIdBasket());
					basket.setNameProduct(basketDto.getNameProduct());
					basket.setImageProduct(basketDto.getImageProduct());
					basket.setPrice(basket.getPrice());
					basket.setNumOfCart(basketDto.getNumOfCart());
					basket.setTotalMoney(basketDto.getTotalMoney());
					basketRepository.save(basket);
					batkets.add(basket);
				}
				bastketTotal.setTotalMoneyBasket(bastketDtoTotal.getTotalMoneyBasket());
				bastketTotal.setBaskets(batkets);
				invoice.setBastketTotal(bastketTotal);
				invoiceRepository.save(invoice);
			}

		
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
			return ;
		}
		
	}
	

	
	
}

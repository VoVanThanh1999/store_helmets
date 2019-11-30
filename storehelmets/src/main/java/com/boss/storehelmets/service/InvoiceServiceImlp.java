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
import com.boss.storehelmets.model.BasketTotal;
import com.boss.storehelmets.model.Invoice;
import com.boss.storehelmets.model.Product;
import com.boss.storehelmets.model.User;
import com.boss.storehelmets.repository.ProductRepository;

@Service
public class InvoiceServiceImlp implements InvoiceService{
	@Autowired
	ProductRepository productRepository;
	
	
	@Override
	@SuppressWarnings("unchecked")
	public void inserNewInvoice(HttpServletRequest request, User user) {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		List<Product> products = new ArrayList<Product>();
//		lay value sesion tu basketdto
		List<BasketDto> basketDtos = (List<BasketDto>) session.getAttribute("basketDtoSession");
		
//		lay tong value sesion tu basketDetailsDto
		BastketDtoTotal bastketDtoTotal = (BastketDtoTotal) session.getAttribute("basketDetailsDto");
		try {
			if (basketDtos!= null && bastketDtoTotal != null) {
//				save value cho bill
				Invoice invoice = new Invoice();
				invoice.setNameCustomer(user.getFullName());
				invoice.setEmail(user.getEmail());
				invoice.setStatus(false);
				invoice.setAddress1(user.getAddress1());
				invoice.setAddress2(user.getAddress2());
				invoice.setTel(user.getTel());
				BasketTotal basketTotal = new BasketTotal();
				basketTotal.setTotalMoney(bastketDtoTotal.getTotalMoneyBasket());
//				set value cho basketTotal bang value tu bastkettotaldto
				bastketDtoTotal.getBasketDtos().forEach(temp ->{
					Optional<Product> productOptinal = productRepository.findById(temp.getIdBasket());
					if (productOptinal.isPresent()) {
						products.add(productOptinal.get());
					}
				});
				basketTotal.setProducts(products);
				invoice.setBasketTotal(basketTotal);
			}		
		} catch (Exception e) {
			// TODO: handle exception
		}
	
	}

	
	
}

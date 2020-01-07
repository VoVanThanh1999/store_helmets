package com.boss.storehelmets.service;
import java.sql.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boss.storehelmets.app.utils.AppConstants;
import com.boss.storehelmets.dto.BasketDto;
import com.boss.storehelmets.dto.BastketDtoTotal;
import com.boss.storehelmets.model.Basket;
import com.boss.storehelmets.model.BastketTotal;
import com.boss.storehelmets.model.HistoryStoreEvent;
import com.boss.storehelmets.model.Invoice;
import com.boss.storehelmets.model.Product;
import com.boss.storehelmets.model.ProductsDetails;
import com.boss.storehelmets.model.User;
import com.boss.storehelmets.repository.BasketRepository;
import com.boss.storehelmets.repository.BasketTotalRepository;
import com.boss.storehelmets.repository.HistoryStoreEventRepository;
import com.boss.storehelmets.repository.InvoiceRepository;
import com.boss.storehelmets.repository.ProductRepository;

@Service
public class InvoiceServiceImpl implements InvoiceService{
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
	
	@Autowired
	ProductService productService;
	
	@Autowired
	HistoryStoreEventRepository historyStoreEventRepository;
	
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
				java.util.Date dateData = new java.util.Date();
				Date date = new Date(dateData.getYear(), dateData.getMonth(), dateData.getDate());
				invoice.setNameCustomer(user.getFullName());
				invoice.setEmail(user.getEmail());
				invoice.setAddress1(user.getAddress1());
				invoice.setAddress2(user.getAddress2());
				invoice.setStatus(false);
				invoice.setDateCreat(date);
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
	
	@Transactional
	@Override
	public String confimInvoice( User user,String id) {
		// TODO Auto-generated method stub
		Optional<Invoice> invoice = invoiceRepository.findById(id);
		if (invoice.isPresent()) {
		   if  (!invoice.get().isStatus()) {
					invoice.get().setStatus(true);
					invoice.get().setUserConfirm(user);
					Set<Basket> bastketTotals = invoice.get().getBastketTotal().getBaskets();
					for (Basket basket : bastketTotals) {
						Optional<Product> product = productService.getById(basket.getIdProduct());
						ProductsDetails productsDetails = product.get().getProductsDetails();			
						int quantitySold = productsDetails.getQuantitySold() + basket.getNumOfCart();
						productsDetails.setQuantitySold(quantitySold);
						productsDetails.setQuantityExists(productsDetails.getNumberEntered() - productsDetails.getQuantitySold());
						product.get().setProductsDetails(productsDetails);
						if (productsDetails.getQuantityExists() > 1) {
							productRepository.save(product.get());
						}
					}
			
				
				invoiceRepository.save(invoice.get());
				return AppConstants.SUCCESS_UPDATE;
			}
		}
		return null;
	}

	@Override
	public List<Invoice> getAllInvoice() {
		// TODO Auto-generated method stub
		if (invoiceRepository.findAll() != null) {
			return invoiceRepository.findAll();
		}
		return null;
	}

	@Override
	public void deleteInvoice() {
		// TODO Auto-generated method stub
		List<Invoice> invoices = invoiceRepository.findAll();
		java.util.Date dateData = new java.util.Date();
		Date date = new Date(dateData.getYear(), dateData.getMonth(), dateData.getDate());
		for (Invoice invoice : invoices) {
			int year = dateData.getYear() - invoice.getDateCreat().getYear();
			int mounth = dateData.getMonth() - invoice.getDateCreat().getMonth();
			if (year>1 || mounth >2) {
				invoiceRepository.delete(invoice);
			}
		}
	}
	
	
	
	
}

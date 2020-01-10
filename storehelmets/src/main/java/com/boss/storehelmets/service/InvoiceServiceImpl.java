package com.boss.storehelmets.service;
import java.sql.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

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
	public String inserNewInvoice(HttpServletRequest request, User user) {
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
				invoice.setStatusConfim(false);
				invoice.setDateCreat(date);
				invoice.setTel(user.getTel());
				invoice.setUserCreate(user);
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
				return AppConstants.ADD_INVOICE_SUCCESS;
			}
		
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
			return null;
		}
		return AppConstants.ADD_INVOICE_ERROR;
	}
	
	@Transactional
	@Override
	public String confimInvoice( User user,String id) {
		// TODO Auto-generated method stub
		Optional<Invoice> invoice = invoiceRepository.findById(id);
		if (invoice.isPresent()) {
		   if  (!invoice.get().isStatusConfim()) {
					invoice.get().setStatusConfim(true);
					invoice.get().setUserConfirm(user);
							
				invoiceRepository.save(invoice.get());
				return AppConstants.SUCCESS_UPDATE;
			}
		}
		return null;
	}
	
	public List<Invoice> getInvoiceHaveStatusConfimIsTrue(){
		List<Invoice> invoices = invoiceRepository.findInvoiceGetStatusConfimIsTrue();
		if (invoices != null) {
			return invoices.stream()
					.filter(invoice -> invoice.isStatusSuccess() == false && invoice.isStatusTransport() == true)
					.collect(Collectors.toList());
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
	public String deleteInvoice() {
		// TODO Auto-generated method stub
		
	}

/*	@Override
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
				return "xoa thanh cong";
			}
		}
<<<<<<< HEAD
		return null;
	}

	@Override
	public List<Invoice> getInvoicesByUserId(User user) {
		// TODO Auto-generated method stub
		List<Invoice> invoice = invoiceRepository.findInvoiceByUserIdCreate(user.getIdUser());
		return invoice;
	}
=======
	}*/
>>>>>>> 69f4ebac9dd39095ac195171f7566c3acda2acec
	
	
	
	
}

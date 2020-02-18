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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.boss.storehelmets.app.utils.AppConstants;
import com.boss.storehelmets.dto.BasketDto;
import com.boss.storehelmets.dto.BastketDtoTotal;
import com.boss.storehelmets.model.Basket;
import com.boss.storehelmets.model.BastketTotal;
import com.boss.storehelmets.model.HistoryComfirmInvoice;
import com.boss.storehelmets.model.HistoryStoreEvent;
import com.boss.storehelmets.model.Invoice;
import com.boss.storehelmets.model.Product;
import com.boss.storehelmets.model.ProductsDetails;
import com.boss.storehelmets.model.SalesHistory;
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
	public String inserNewInvoice(HttpServletRequest request, User user,Invoice invoiceDto) {
		// TODO Auto-generated method stub
		try {
			HttpSession session = request.getSession();
			BastketDtoTotal bastketDtoTotal = basketDtoService.getTotalBasketDto(request);
			List<BasketDto> basketDtos = (List<BasketDto>) session.getAttribute("basketDtoSession");
			if (basketDtos != null ) {
				Invoice invoice = new Invoice();
				java.util.Date dateData = new java.util.Date();
				Date date = new Date(dateData.getYear(), dateData.getMonth(), dateData.getDate());
				invoice.setNameCustomer(invoiceDto.getNameCustomer());
				invoice.setEmail(invoiceDto.getEmail());
				invoice.setAddress1(invoiceDto.getAddress1());
				invoice.setAddress2(invoiceDto.getAddress2());
				invoice.setStatusConfim(false);
				invoice.setDateCreat(date);
				invoice.setTel(invoiceDto.getTel());
				invoice.setNote(invoiceDto.getNote());
				invoice.setUserCreate(user);
				BastketTotal bastketTotal = new BastketTotal();
				Set<Basket> batkets = new HashSet<Basket>();
				for (BasketDto basketDto : basketDtos) {
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
		try {
			Optional<Invoice> invoice = invoiceRepository.findById(id);
			if (invoice.isPresent()) {
			   if  (!invoice.get().isStatusConfim()) {
	
				   	invoice.get().setStatusConfim(true);
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
							}else {
								return null;
							}
						}		
					java.util.Date dateData = new java.util.Date();
					Date date = new Date(dateData.getYear(), dateData.getMonth(), dateData.getDate());	
					HistoryStoreEvent event  = historyStoreEventRepository.findByDate(date);
					if (event ==  null) {
						event = new HistoryStoreEvent();
						event.setDate(date);
						Set<HistoryComfirmInvoice> historyComfirmInvoices = new HashSet<>();
						HistoryComfirmInvoice historyComfirmInvoice = new HistoryComfirmInvoice();
						historyComfirmInvoice.setInvoice(invoice.get());
						historyComfirmInvoice.setThoiGianDuyet(date);
						historyComfirmInvoice.setUser(user);
						historyComfirmInvoices.add(historyComfirmInvoice);
						event.setHistoryComfirmInvoices(historyComfirmInvoices);
						historyStoreEventRepository.save(event);
					}else {
						Set<HistoryComfirmInvoice> historyComfirmInvoices =  event.getHistoryComfirmInvoices();
						HistoryComfirmInvoice historyComfirmInvoice = new HistoryComfirmInvoice();
						historyComfirmInvoice.setInvoice(invoice.get());
						historyComfirmInvoice.setUser(user);
						historyComfirmInvoice.setThoiGianDuyet(date);
						historyComfirmInvoices.add(historyComfirmInvoice);
						event.setHistoryComfirmInvoices(historyComfirmInvoices);
						historyStoreEventRepository.save(event);
					}
					historyStoreEventRepository.save(event);
					invoiceRepository.save(invoice.get());
					return AppConstants.SUCCESS_UPDATE;
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println(e.getMessage());
			System.err.println(e.getLocalizedMessage());
			System.err.println(e.getSuppressed());
		}
		return null;
	}
	
	public List<Invoice> getInvoiceHaveStatusConfimIsTrue(){
		
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
		return null;
	}

	@Override
	public List<Invoice> getInvoicesByUserId(User user) {
		// TODO Auto-generated method stub
		List<Invoice> invoices = invoiceRepository.findInvoiceByUserIdCreate(user.getIdUser());
		
		return invoices;
	}

	@Override
	public Long getAmountInvoice() {
		// TODO Auto-generated method stub
		return invoiceRepository.count();
	}

	@Override
	public Page<Invoice> getInvoiceByPageRequest(String valuekey) {
		// TODO Auto-generated method stub
		try {
			Page<Invoice> page = invoiceRepository.findInvoiceAndGetInPage(PageRequest.of(0, 5));
			switch (valuekey) {
			case "loadAtStart":
				return	page;
			case "previousPageable":
				Page<Invoice> pagePrevious = invoiceRepository.findInvoiceAndGetInPage(page.previousPageable());
				return pagePrevious;
			case "nextPageable":
				Page<Invoice> pageNext = invoiceRepository.findInvoiceAndGetInPage(page.nextPageable());
				return pageNext;
			default:
				
				return null;
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println(e.getMessage());
		}
		return null;
	}

	@Override
	public int getTotalPages() {
		// TODO Auto-generated method stub
		Page<Invoice> page = invoiceRepository.findAll(PageRequest.of(0, 5));
		return page.getTotalPages();
	}

	@Override
	public Invoice getInvoiceById(String id) {
		// TODO Auto-generated method stub
		Optional<Invoice> invoice = invoiceRepository.findById(id);
		return invoice.get();
	}
	

	@Override
	public Page<Invoice> getInvoicesByStatusConfimIsTrueAndSuccesIsFalse(String key) {
		// TODO Auto-generated method stub
		try {
			Page<Invoice> page = invoiceRepository.findInvoiceGetStatusConfimIsTrueAndSuccessIsFalse(PageRequest.of(0, 5));
			switch (key) {
			case "loadAtStart":
				return	page;
			case "previousPageable":
				Page<Invoice> pagePrevious = invoiceRepository.findInvoiceGetStatusConfimIsTrueAndSuccessIsFalse(page.previousPageable());
				return pagePrevious;
			case "nextPageable":
				Page<Invoice> pageNext = invoiceRepository.findInvoiceGetStatusConfimIsTrueAndSuccessIsFalse(page.nextPageable());
				return pageNext;
			default:
				return null;
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println(e.getMessage());
		}
		return null;
	}
//	get tong so tien ma hoa don đang  vận chuyện
	@Override
	public int getTotalMoneyInvouceAwaitingApproval() {
		// TODO Auto-generated method stub
		Page<Invoice> page = invoiceRepository.findInvoiceBeingShipped(PageRequest.of(0, 5));
		List<Invoice> invoices = page.getContent();
		int tempMoney = 0;
		for (Invoice invoice : invoices) {
			tempMoney += invoice.getBastketTotal().getTotalMoneyBasket();
		}
		return tempMoney;
	}

	@Override
	public List<Invoice> getInvoiceStatusTransportIsTrue() {
		// TODO Auto-generated method stub
		try {
			List<Invoice> invoices = invoiceRepository.findAll()
					.stream()
					.filter(i ->i.isStatusTransport() == true && i.isStatusSuccess() == false)
					.map(temp->{
						Invoice invoice = new Invoice();
						invoice.setIdInvoice(temp.getIdInvoice());
						invoice.setNameCustomer(temp.getNameCustomer());
						invoice.setBastketTotal(temp.getBastketTotal());
						invoice.setDateConfirm(temp.getDateConfirm());
						invoice.setNhanVienGiaoHang(temp.getNhanVienGiaoHang());
						invoice.setUserConfirm(temp.getUserConfirm());
						return invoice;
					})
					.collect(Collectors.toList());
			return invoices;
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println(e.getMessage());
		}
		return null;
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

	
	
	
	
}

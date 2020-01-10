package com.boss.storehelmets.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;

import com.boss.storehelmets.app.utils.AppConstants;
import com.boss.storehelmets.model.Basket;
import com.boss.storehelmets.model.BastketTotal;
import com.boss.storehelmets.model.HistoryImportProduct;
import com.boss.storehelmets.model.HistoryStoreEvent;
import com.boss.storehelmets.model.Invoice;
import com.boss.storehelmets.model.Product;
import com.boss.storehelmets.model.ProductsDetails;
import com.boss.storehelmets.model.SalesHistory;
import com.boss.storehelmets.model.ShippingBill;
import com.boss.storehelmets.model.User;
import com.boss.storehelmets.repository.HistoryStoreEventRepository;
import com.boss.storehelmets.repository.InvoiceRepository;
import com.boss.storehelmets.repository.ProductRepository;
import com.boss.storehelmets.repository.ShippingBillRepository;
import com.boss.storehelmets.repository.UserRepository;

public class ShippingBillServiceImpl implements ShippingBillService{
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	InvoiceRepository invoiceRepository;
	
	@Autowired
	InvoiceService invoiceService;
	
	@Autowired
	ShippingBillRepository shippingBillRepository;
	
	@Autowired
	ProductService productService;
	
	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	HistoryStoreEventService historyStoreEventService;
	
	@Autowired
	HistoryStoreEventRepository historyStoreEventRepository;
	
	@Override
	public void addNewsShippingBill(User user, List<Invoice> invoicesDto) {
		// TODO Auto-generated method stub
//		check hoa don da thoa dieu kien chua
		List<Invoice> invoices = new ArrayList<>();
		invoices.addAll(invoicesDto.stream()
					.filter(invoice -> invoice.isStatusConfim()==true && invoice.isStatusSuccess() == false && invoice.isStatusTransport() == false)
					.collect(Collectors.toList()));
		ShippingBill shippingBill = new ShippingBill();
		shippingBill.setInvoices(invoices);
		float totalMoneyBill = 0;
		for (Invoice invoice : invoices) {
			invoice.setStatusTransport(true);
			totalMoneyBill += invoice.getBastketTotal().getTotalMoneyBasket();
		}
		shippingBill.setTotalMoneyInvoice(totalMoneyBill);
		shippingBill.setUser(user);
		shippingBillRepository.save(shippingBill);
	}

	@Override
	public ShippingBill checkShippingBill(User user, ShippingBill shippingBillDto) {
		// TODO Auto-generated method stub 
		Optional<ShippingBill> shippingBill = shippingBillRepository.findById(shippingBillDto.getIdShippingBill());
		return shippingBill.get();
	}
	
	
	@Transactional
	@Override
	public String confimInvoiceByShipper(User user, Invoice invoiceDto) {
		// TODO Auto-generated method stub
		List<ShippingBill> shippingBills = shippingBillRepository.findShippingBillsByIdUser(user.getIdUser());
		for (ShippingBill shippingBill : shippingBills) {
			for (Invoice invoice : shippingBill.getInvoices()) {
				if (invoice.getIdInvoice().equalsIgnoreCase(invoiceDto.getIdInvoice())) {
					if (invoice.isStatusConfim() == true && invoice.isStatusTransport() == true) {
						invoice.setStatusSuccess(true);
						Set<Basket> bastketTotals = invoice.getBastketTotal().getBaskets();
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
						HistoryStoreEvent historyStoreEventByCheck = historyStoreEventService.checkHistoryInDay();
						if (historyStoreEventByCheck == null) {
							HistoryStoreEvent historyStoreEvent = new HistoryStoreEvent();
							java.util.Date dateData = new java.util.Date();
							Date date = new Date(dateData.getYear(), dateData.getMonth(), dateData.getDate());	
							historyStoreEvent.setDate(date);
							historyStoreEvent.setTotalMoneySold(invoice. getBastketTotal().getTotalMoneyBasket());
							Set<SalesHistory> salesHistories = new HashSet<>();
							SalesHistory salesHistory = new SalesHistory();
							salesHistory.setBastketTotal(invoice.getBastketTotal());
							salesHistory.setUser(user);
							salesHistories.add(salesHistory);
							historyStoreEvent.setSalesHistory(salesHistories);
							Set<HistoryImportProduct> historyImportProducts = new HashSet<>();
							HistoryImportProduct historyImportProduct = new HistoryImportProduct();
							historyImportProducts.add(historyImportProduct);
							historyStoreEvent.setProductImportHistories(historyImportProducts);
							historyStoreEvent.setTotalDeductibleAmount(invoice.getTotalMoneyInvoice());
							historyStoreEventRepository.save(historyStoreEvent);
						}else {
							Set<SalesHistory> histories = historyStoreEventByCheck.getSalesHistory();
							SalesHistory salesHistory = new SalesHistory();
							salesHistory.setBastketTotal(invoice.getBastketTotal());
							salesHistory.setUser(user);
							histories.add(salesHistory);
							historyStoreEventByCheck.setSalesHistory(histories);
							double totalMoney = historyStoreEventByCheck.getTotalDeductibleAmount() + invoice.getTotalMoneyInvoice();
							historyStoreEventByCheck.setTotalDeductibleAmount(totalMoney);
							historyStoreEventRepository.save(historyStoreEventByCheck);
						}
					}
					return AppConstants.SUCESS_CONFIM_INVOICE;
				}
			}
		}
		return AppConstants.ERROR_CONFIM_INVOICE;
	}

	@Override
	public List<ShippingBill> getShippingBillHaveUserId(User user) {
		// TODO Auto-generated method stub
		List<ShippingBill> shippingBills = shippingBillRepository.findShippingBillsByIdUser(user.getIdUser());
		
		return shippingBills;
	}
	
	
	

}

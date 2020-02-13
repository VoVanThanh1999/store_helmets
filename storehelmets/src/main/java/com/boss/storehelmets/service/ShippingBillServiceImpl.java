package com.boss.storehelmets.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boss.storehelmets.app.utils.AppConstants;
import com.boss.storehelmets.model.Basket;
import com.boss.storehelmets.model.BastketTotal;
import com.boss.storehelmets.model.HistoryCreateShippingbill;
import com.boss.storehelmets.model.HistoryImportProduct;
import com.boss.storehelmets.model.HistoryStoreEvent;
import com.boss.storehelmets.model.Invoice;
import com.boss.storehelmets.model.Product;
import com.boss.storehelmets.model.ProductsDetails;
import com.boss.storehelmets.model.Roles;
import com.boss.storehelmets.model.SalesHistory;
import com.boss.storehelmets.model.ShippingBill;
import com.boss.storehelmets.model.User;
import com.boss.storehelmets.repository.HistoryStoreEventRepository;
import com.boss.storehelmets.repository.InvoiceRepository;
import com.boss.storehelmets.repository.ProductRepository;
import com.boss.storehelmets.repository.ShippingBillRepository;
import com.boss.storehelmets.repository.UserRepository;

@Service
public class ShippingBillServiceImpl implements ShippingBillService {
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
	
	@Autowired
	UserSevice userSevice;
	
	@Transactional
	@Override
	public String addNewsShippingBill(User adminCreate, User shipper, List<Invoice> invoicesDto) {
		// TODO Auto-generated method stub
//		check hoa don da thoa dieu kien chua
		try {
			if (invoicesDto != null) {
				List<Invoice> invoices = new ArrayList<>();
				invoices.addAll(invoicesDto
						.stream().filter(invoice -> invoice.isStatusConfim() == true
								&& invoice.isStatusSuccess() == false && invoice.isStatusTransport() == false)
						.collect(Collectors.toList()));
				ShippingBill shippingBill = new ShippingBill();
				shippingBill.setInvoices(invoices);
				float totalMoneyBill = 0;
				for (Invoice invoice : invoices) {
					invoice.setStatusTransport(true);
					totalMoneyBill += invoice.getBastketTotal().getTotalMoneyBasket();
				}
				shippingBill.setTotalMoneyInvoice(totalMoneyBill);
				shippingBill.setAdminCreate(adminCreate);
				shippingBill.setShipper(shipper);
				java.util.Date dateData = new java.util.Date();
				Date date = new Date(dateData.getYear(), dateData.getMonth(), dateData.getDate());	
				HistoryStoreEvent historyStoreEvent = historyStoreEventRepository.findByDate(date);
				if (historyStoreEvent == null) {
					historyStoreEvent = new HistoryStoreEvent();
					historyStoreEvent.setDate(date);
					Set<HistoryCreateShippingbill> createShippingbills = new HashSet<>();
					HistoryCreateShippingbill historyCreateShippingbill = new HistoryCreateShippingbill();
					historyCreateShippingbill.setDate(date);
					historyCreateShippingbill.setAdminCreate(adminCreate);
					historyCreateShippingbill.setShippingBill(shippingBill);
					historyCreateShippingbill.setDate(date);
					historyCreateShippingbill.setUserShipper(shipper);
					createShippingbills.add(historyCreateShippingbill);
					historyStoreEvent.setHistoryCreateShippingbills(createShippingbills);
				}else {
					Set<HistoryCreateShippingbill> createShippingbills = new HashSet<>();
					HistoryCreateShippingbill historyCreateShippingbill = new HistoryCreateShippingbill();
					historyCreateShippingbill.setDate(date);
					historyCreateShippingbill.setAdminCreate(adminCreate);
					historyCreateShippingbill.setShippingBill(shippingBill);
					historyCreateShippingbill.setDate(date);
					historyCreateShippingbill.setUserShipper(shipper);
					createShippingbills.add(historyCreateShippingbill);
					historyStoreEvent.setHistoryCreateShippingbills(createShippingbills);
				}
					
				
				shippingBillRepository.save(shippingBill);
				return AppConstants.SUCCESS_ADD_SHIPPINGBILL;
			}

		} catch (Exception e) {
			// TODO: handle exception
			System.err.println(e.getMessage());
		}
		return null;
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
		List<ShippingBill> shippingBills = shippingBillRepository.findShippingBillsByShipper(user.getIdUser());
		for (ShippingBill shippingBill : shippingBills) {
			for (Invoice invoice : shippingBill.getInvoices()) {
				if (invoice.getIdInvoice().equalsIgnoreCase(invoiceDto.getIdInvoice())
						&& shippingBill.getShipper().getIdUser().equalsIgnoreCase(user.getIdUser())) {

					if (invoice.isStatusConfim() == true && invoice.isStatusTransport() == true) {
						invoice.setStatusSuccess(true);
						HistoryStoreEvent historyStoreEventByCheck = historyStoreEventService.checkHistoryInDay();
						if (historyStoreEventByCheck == null) {
							HistoryStoreEvent historyStoreEvent = new HistoryStoreEvent();
							java.util.Date dateData = new java.util.Date();
							Date date = new Date(dateData.getYear(), dateData.getMonth(), dateData.getDate());
							historyStoreEvent.setDate(date);
							historyStoreEvent.setTotalMoneySold(invoice.getBastketTotal().getTotalMoneyBasket());
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
							historyStoreEvent.setTotalDeductibleAmount(invoice.getBastketTotal().getTotalMoneyBasket());
							historyStoreEventRepository.save(historyStoreEvent);
						} else {
							Set<SalesHistory> histories = historyStoreEventByCheck.getSalesHistory();
							SalesHistory salesHistory = new SalesHistory();
							salesHistory.setBastketTotal(invoice.getBastketTotal());
							salesHistory.setUser(user);
							histories.add(salesHistory);
							historyStoreEventByCheck.setSalesHistory(histories);
							double totalMoney = historyStoreEventByCheck.getTotalDeductibleAmount()
									+ invoice.getBastketTotal().getTotalMoneyBasket();
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
		List<ShippingBill> shippingBills = shippingBillRepository.findShippingBillsByShipper(user.getIdUser());

		return shippingBills;
	}

	@Override
	public List<Invoice> getInvoiceHaveInShippingBill(User shippier, ShippingBill shippingBillDto) {
		// TODO Auto-generated method stub
		List<ShippingBill> shippingBills = getShippingBillHaveUserId(shippier);
		for (ShippingBill shippingBill : shippingBills) {
			if (shippingBill.getIdShippingBill().equalsIgnoreCase(shippingBillDto.getIdShippingBill())) {
				return shippingBill.getInvoices();
			}
		}
		return null;
	}

	@Override
	public String confimShippingBill(ShippingBill shippingBill, User shippier) {
		// TODO Auto-generated method stub
		int temp = 0;
		if (shippingBill.getShipper().getIdUser().equalsIgnoreCase(shippier.getIdUser())) {
			for (Invoice invoice : shippingBill.getInvoices()) {
				if (invoice.isStatusConfim() == false || invoice.isStatusSuccess() == false
						|| invoice.isStatusTransport() == false) {
					temp++;
				}
			}
		}
		if (temp == 0) {
			shippingBill.setStatusShippingbill(true);
			shippingBillRepository.save(shippingBill);
			return AppConstants.SUCESS_CONFIM_SHIPPINGBIL;
		}
		return AppConstants.EROR_CONFIM_SHIPPINGBIL;
	}

	@Override
	public String cancelInvoice(ShippingBill shippingBill, User shippier, String idInvoice) {
		// TODO Auto-generated method stub
		if (shippingBill.getShipper().getIdUser().equalsIgnoreCase(shippier.getIdUser())) {
			for (Invoice invoice : shippingBill.getInvoices()) {
				if (invoice.getIdInvoice().equals(idInvoice)) {
					invoice.setStatusCancel(true);
					invoiceRepository.save(invoice);
					float totalMoney = shippingBill.getTotalMoneyInvoice()
							- invoice.getBastketTotal().getTotalMoneyBasket();
					shippingBill.setTotalMoneyInvoice(totalMoney);
					for (Basket basket : invoice.getBastketTotal().getBaskets()) {
						Optional<Product> product = productRepository.findById(basket.getIdBasket());
						ProductsDetails details = product.get().getProductsDetails();
						
					}

				}
			}
		}
		return null;
	}

	@Override
	public List<User> getUserHaveRoleIsShippers() {
		// TODO Auto-generated method stub 
		List<User> users = userRepository.findAll();
		for (User user : users) {
			for (Roles roles : user.getRoles()) {
				if(roles.getRoleName().equalsIgnoreCase("ROLE_SHIPPER"));
				List<User> shipper = Arrays.asList(user);
				return shipper;
			}
		}
		return null;
	}

}

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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
import com.boss.storehelmets.repository.HistoryCreateShippingbillRepository;
import com.boss.storehelmets.repository.HistoryStoreEventRepository;
import com.boss.storehelmets.repository.InvoiceRepository;
import com.boss.storehelmets.repository.ProductRepository;
import com.boss.storehelmets.repository.ShippingBillRepository;
import com.boss.storehelmets.repository.UserRepository;

@Service
public class ShippingBillServiceImpl implements ShippingBillService {

	public static final String XACNHANHOADONTHANHCONG = "Bạn đã xác nhận hóa đơn thành công";
	public static final String XACNHANHOADONTHATBAI = "Xác nhận hóa đơn thất bại";

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

	@Autowired
	HistoryCreateShippingbillRepository historyCreateShippingbillRepository;

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
				java.util.Date dateData = new java.util.Date();
				Date date = new Date(dateData.getYear(), dateData.getMonth(), dateData.getDate());
				float totalMoneyBill = 0;
				for (Invoice invoice : invoices) {
					invoice.setNhanVienGiaoHang(shipper);
					invoice.setDateConfirm(date);
					invoice.setStatusTransport(true);
					totalMoneyBill += invoice.getBastketTotal().getTotalMoneyBasket();
				}
				shippingBill.setTotalMoneyInvoice(totalMoneyBill);
				shippingBill.setAdminCreate(adminCreate);
				shippingBill.setShipper(shipper);
				shippingBill.setDate(date);
				invoiceRepository.saveAll(invoices);
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
					historyCreateShippingbillRepository.save(historyCreateShippingbill);
					createShippingbills.add(historyCreateShippingbill);
					historyStoreEvent.setHistoryCreateShippingbills(createShippingbills);

					historyStoreEventRepository.save(historyStoreEvent);
				} else {
					Set<HistoryCreateShippingbill> createShippingbills = historyStoreEvent
							.getHistoryCreateShippingbills();
					HistoryCreateShippingbill historyCreateShippingbill = new HistoryCreateShippingbill();
					historyCreateShippingbill.setAdminCreate(adminCreate);
					historyCreateShippingbill.setShippingBill(shippingBill);
					historyCreateShippingbill.setDate(date);
					historyCreateShippingbill.setUserShipper(shipper);
					historyCreateShippingbillRepository.save(historyCreateShippingbill);
					createShippingbills.add(historyCreateShippingbill);
					historyStoreEvent.setHistoryCreateShippingbills(createShippingbills);
					historyStoreEventRepository.save(historyStoreEvent);
				}

				shippingBillRepository.save(shippingBill);
				return AppConstants.SUCCESS_ADD_SHIPPINGBILL;
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();

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
		try {
			List<User> users = userRepository.findAll();
			List<User> shipper = new ArrayList<>();
			for (User user : users) {
				for (Roles roles : user.getRoles()) {
					if (roles.getRoleName().equalsIgnoreCase("ROLE_SHIPPER")) {
						shipper.add(user);
					}
				}
			}
			return shipper;
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println(e.getMessage());
		}
		return null;
	}

	@Override
	public List<ShippingBill> getShipppingBillBeingTransported() {
		// TODO Auto-generated method stub
		try {
			List<ShippingBill> shippingBills = shippingBillRepository.findAll().stream()
					.filter(s -> s.isStatusShippingbill() == false).map(temp -> {
						ShippingBill shippingBill = new ShippingBill();
						shippingBill.setIdShippingBill(temp.getIdShippingBill());
						shippingBill.setDate(temp.getDate());
						shippingBill.setInvoices(temp.getInvoices());
						shippingBill.setTotalMoneyInvoice(temp.getTotalMoneyInvoice());
						shippingBill.setShipper(temp.getShipper());
						shippingBill.setAdminCreate(temp.getAdminCreate());
						return shippingBill;
					}).collect(Collectors.toList());
			return shippingBills;
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println(e.getMessage());
		}
		return null;
	}

	@Override
	public Page<ShippingBill> getShipppingBillBeingTransportedByPage(PageRequest pageRequest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public float getMoneyShippingBillBeingTransported() {
		// TODO Auto-generated method stub
		List<ShippingBill> shippingBills = getShipppingBillBeingTransported();
		float money = 0;
		for (ShippingBill shippingBill : shippingBills) {
			money += shippingBill.getTotalMoneyInvoice();
		}
		return money;
	}

	@Transactional
	@Override
	public String xacNhanHoanThanhHoaDon(String idShippingBill, String idManger) {
		// TODO Auto-generated method stub
		try {
			Optional<ShippingBill> shippingBill = shippingBillRepository.findById(idShippingBill);
			if (shippingBill.get().isXacNhanTuTaiXe() == true && shippingBill.get().isStatusShippingbill() == false) {
				java.util.Date dateData = new java.util.Date();
				Date date = new Date(dateData.getYear(), dateData.getMonth(), dateData.getDate());
				shippingBill.get().setNgayAdminXacNhanThanhCong(date);
				shippingBill.get().setStatusShippingbill(true);
				Optional<User> adminManager = userSevice.findUserById(idManger);
				shippingBill.get().setAdminXacNhanHoanThanhHoaDon(adminManager.get());
				List<Invoice> invoices = shippingBill.get().getInvoices();
				for (Invoice invoice : invoices) {
					invoice.setXacNhanHoanThanhTuAdmin(true);
					if (invoice.isStatusCancel() == true) {
						Set<Basket> baskets = invoice.getBastketTotal().getBaskets();
						for (Basket basket : baskets) {
							Optional<Product> product = productRepository.findById(basket.getIdProduct());
							ProductsDetails productsDetails = product.get().getProductsDetails();
							productsDetails.setQuantitySold(productsDetails.getQuantitySold() - basket.getNumOfCart());
							productsDetails
									.setQuantityExists(productsDetails.getQuantityExists() + basket.getNumOfCart());
							product.get().setProductsDetails(productsDetails);
							productRepository.save(product.get());

						}
					}
					 invoiceRepository.save(invoice); 
				}

				 shippingBillRepository.save(shippingBill.get()); 
				return ShippingBillServiceImpl.XACNHANHOADONTHANHCONG;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return ShippingBillServiceImpl.XACNHANHOADONTHATBAI;
	}

	@Override
	public List<ShippingBill> hienThiNhungHoaDonThanhCong() {
		// TODO Auto-generated method stub
		try {
			return shippingBillRepository.findAll().stream().filter(s -> s.isChuyenChoAdmin() == true)
					.collect(Collectors.toList());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;

	}

	@Override
	public List<ShippingBill> hienThiNhungHoaDonDangChoDuyet() {
		// TODO Auto-generated method stub
		return shippingBillRepository.hienThiNhungHoaDonDangChoXacNhan();
	}

	@Override
	public List<ShippingBill> hienThiShippingbillsThanhCong() {
		// TODO Auto-generated method stub
		return shippingBillRepository.hienThiNhungHoaDonDaGiaoThanhCong();
	}

}

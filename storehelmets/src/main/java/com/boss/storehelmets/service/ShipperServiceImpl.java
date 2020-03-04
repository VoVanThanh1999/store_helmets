package com.boss.storehelmets.service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.boss.storehelmets.model.HistoryShipper;
import com.boss.storehelmets.model.HistoryShipperInvoice;
import com.boss.storehelmets.model.HistoryShipperShippingBill;
import com.boss.storehelmets.model.Invoice;
import com.boss.storehelmets.model.ShippingBill;
import com.boss.storehelmets.repository.HistoryShipperRepository;
import com.boss.storehelmets.repository.InvoiceRepository;
import com.boss.storehelmets.repository.ShippingBillRepository;

@Service
public class ShipperServiceImpl implements ShipperService {

	public static final String SuccessConfirmInvoice = "Bạn đã giao đơn đặt hàng thành công";
	public static final String ErrorConfirmInvoice = "Xác nhận giao đơn đặt hàng thất bại";
	public static final String SuccessCancelInvoice = "Bạn đã hủy đơn đặt thành công";
	public static final String ErrorSuccessCancelInvoice = "Bạn đã hủy đơn đặt hàng thất bại";
	public static final String SuccessConfirmShippingBill = "Bạn đã xác nhận hóa đơn thành công";
	public static final String ErrorConfirmShippingBill = "Bạn đã xác nhận hóa đơn thất bại";
	public static final String SuccessTransferredToTheManager = "Chuyển cho tài xế thành công";
	public static final String ErrorTransferredToTheManager = "Chuyển cho tài xế thành công";

	public static final Logger LOGGER = LogManager.getLogger(ShipperServiceImpl.class);

	@Autowired
	ShippingBillRepository shippingBillRepository;

	@Autowired
	HistoryShipperRepository historyShipperRepository;

	@Autowired
	InvoiceRepository invoiceRepository;

	@Transactional
	@Override
	public List<ShippingBill> getShippingBillByIdShipper(String idShipper) {
		// TODO Auto-generated method stub
		try {
			List<ShippingBill> shippingBills = shippingBillRepository.findAll().stream()
					.filter(s -> s.getShipper().getIdUser().equalsIgnoreCase(idShipper)
							&& s.isStatusShippingbill() == false && !s.isXacNhanTuTaiXe())
					.map(temp -> {
						ShippingBill shippingBill = new ShippingBill();
						shippingBill.setIdShippingBill(temp.getIdShippingBill());
						shippingBill.setInvoices(temp.getInvoices());
						shippingBill.setTotalMoneyInvoice(temp.getTotalMoneyInvoice());
						shippingBill.setTotalMoneyCollected(getMoneyProceeds(temp.getIdShippingBill()));
						shippingBill.setTienDaHuy(getMoneyCancelInShippingBill(temp.getIdShippingBill()));
						shippingBill.setTotalMoneyNotYetCollected(
								temp.getTotalMoneyInvoice() - temp.getTotalMoneyCollected()
										- getMoneyCancelInShippingBill(temp.getIdShippingBill()));
						return shippingBill;
					}).collect(Collectors.toList());
			return shippingBills;
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println(e.getMessage());
			System.err.println(e.getLocalizedMessage());
		}
		return null;
	}

	@Transactional
	@Override
	public List<Invoice> getInvoiceByShipping(String idShipping) {
		// TODO Auto-generated method stub
		try {
			Optional<ShippingBill> shippingBill = shippingBillRepository.findById(idShipping);
			if (shippingBill != null)
				return shippingBill.get().getInvoices();

		} catch (Exception e) {
			// TODO: handle exception
			LOGGER.getName();
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	@Transactional
	@Override
	public String confirmInvoiceInShipping(String idShipping, String idInvoice, String idShipper) {
		// TODO Auto-generated method stub
		try {
			Optional<ShippingBill> shippingBill = shippingBillRepository.findById(idShipping);
			List<Invoice> invoices = shippingBill.get().getInvoices();
			for (Invoice invoice : invoices) {

				if (invoice.getIdInvoice().equalsIgnoreCase(idInvoice) && invoice.isXacNhanTuTaiXe() == false) {
					System.out.println("test nhe");
					shippingBill.get().setTotalMoneyCollected(shippingBill.get().getTotalMoneyCollected()
							+ invoice.getBastketTotal().getTotalMoneyBasket());
					invoice.setXacNhanTuTaiXe(true);
					invoice.setStatusCancel(false);
					java.util.Date dateData = new java.util.Date();
					Date date = new Date(dateData.getYear(), dateData.getMonth(), dateData.getDate());
					invoice.setDateDeliverySuccessOrCancel(date);
					HistoryShipper historyShipper = historyShipperRepository.findByDate(date);
					if (historyShipper == null) {
						historyShipper = new HistoryShipper();
						Set<HistoryShipperInvoice> historyShipperInvoices = new HashSet<>();
						HistoryShipperInvoice historyShipperInvoice = new HistoryShipperInvoice();
						historyShipperInvoice.setInvoice(invoice);
						float moneyInvoice = 0;
						for (Invoice invoiceHistory : invoices) {
							moneyInvoice += invoiceHistory.getBastketTotal().getTotalMoneyBasket();
						}
						historyShipperInvoice.setMoneyInvoice(moneyInvoice);
						historyShipperInvoices.add(historyShipperInvoice);
						historyShipper.setDate(date);
						historyShipper.setHistoryShipperInvoices(historyShipperInvoices);
						historyShipperRepository.save(historyShipper);
					} else {
						Set<HistoryShipperInvoice> historyShipperInvoices = historyShipper.getHistoryShipperInvoices();
						HistoryShipperInvoice historyShipperInvoice = new HistoryShipperInvoice();
						historyShipperInvoice.setInvoice(invoice);
						float moneyInvoice = 0;
						for (Invoice invoiceHistory : invoices) {
							moneyInvoice += invoiceHistory.getBastketTotal().getTotalMoneyBasket();
						}
						historyShipperInvoice.setMoneyInvoice(historyShipperInvoice.getMoneyInvoice() + moneyInvoice);
						historyShipperInvoice.setNgayHoanThanh(date);
						historyShipperInvoices.add(historyShipperInvoice);
						historyShipper.setHistoryShipperInvoices(historyShipperInvoices);
						historyShipperRepository.save(historyShipper);
					}
					shippingBillRepository.save(shippingBill.get());
					invoiceRepository.save(invoice);
					return ShipperServiceImpl.SuccessConfirmInvoice;
				}

			}

		} catch (Exception e) {
			// TODO: handle exception
			System.err.println(e.getMessage());
			System.err.println(e.getLocalizedMessage());
		}
		return ShipperServiceImpl.ErrorConfirmInvoice;
	}

	@Transactional
	@Override
	public String confirmShipping(String idShipping, String idShipper) {
		// TODO Auto-generated method stub
		try {
			Optional<ShippingBill> shippingBill = shippingBillRepository.findById(idShipping);
			if (shippingBill.get().getShipper().getIdUser().equals(idShipper)) {
				int temp = 0;
				float moneyCancel = 0;
				for (Invoice invoice : shippingBill.get().getInvoices()) {
					if (invoice.isXacNhanTuTaiXe() == false) {
						temp++;
					}
				}
				if (temp == 0) {
					List<Invoice> invoicesCancel = checkInvoicesAreCancel(shippingBill.get().getInvoices());
					for (Invoice invoice : invoicesCancel) {
						moneyCancel += invoice.getBastketTotal().getTotalMoneyBasket();
					}
					java.util.Date dateData = new java.util.Date();
					Date date = new Date(dateData.getYear(), dateData.getMonth(), dateData.getDate());
					shippingBill.get().setNgayHoanThanh(date);
					shippingBill.get().setTotalMoneyNotYetCollected(moneyCancel);
					shippingBill.get().setTotalMoneyCollected(shippingBill.get().getTotalMoneyInvoice() - moneyCancel);
					shippingBill.get().setTotalMoneyInvoice(shippingBill.get().getTotalMoneyInvoice());
					shippingBill.get().setXacNhanTuTaiXe(true);
					HistoryShipper historyShipper = historyShipperRepository.findByDate(date);
					if (historyShipper == null) {
						historyShipper = new HistoryShipper();
						historyShipper.setDate(date);
						Set<HistoryShipperShippingBill> historyShipperShippingBills = new HashSet<>();
						HistoryShipperShippingBill historyShipperShippingBill = new HistoryShipperShippingBill();
						historyShipperShippingBill.setSoTienCuaBill(shippingBill.get().getTotalMoneyInvoice());
						historyShipperShippingBill.setSoTienHuy(moneyCancel);
						historyShipperShippingBill.setNgayHoanThanh(date);
						historyShipperShippingBill
								.setSoTienThuDuoc(shippingBill.get().getTotalMoneyInvoice() - moneyCancel);
						historyShipperShippingBills.add(historyShipperShippingBill);
						historyShipper.setHistoryShipperShippingBills(historyShipperShippingBills);
						historyShipperRepository.save(historyShipper);
					} else {
						Set<HistoryShipperShippingBill> historyShipperShippingBills = historyShipper
								.getHistoryShipperShippingBills();
						HistoryShipperShippingBill historyShipperShippingBill = new HistoryShipperShippingBill();
						historyShipperShippingBill.setSoTienCuaBill(shippingBill.get().getTotalMoneyInvoice());
						historyShipperShippingBill.setSoTienHuy(moneyCancel);
						historyShipperShippingBill
								.setSoTienThuDuoc(shippingBill.get().getTotalMoneyInvoice() - moneyCancel);
						historyShipperShippingBills.add(historyShipperShippingBill);
						historyShipper.setHistoryShipperShippingBills(historyShipperShippingBills);
						historyShipperRepository.save(historyShipper);
					}
					historyShipperRepository.save(historyShipper);
					shippingBillRepository.save(shippingBill.get());
					return ShipperServiceImpl.SuccessConfirmShippingBill;
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println(e.getMessage());
			System.err.println(e.getLocalizedMessage());
		}

		return ShipperServiceImpl.ErrorConfirmShippingBill;
	}

	@Transactional
	@Override
	public String cancelInvoice(String idInvoice, String idShipper, String idShippingBill) {
		// TODO Auto-generated method stub
		try {
			Optional<ShippingBill> shippingBill = shippingBillRepository.findById(idShippingBill);
			java.util.Date dateData = new java.util.Date();
			Date date = new Date(dateData.getYear(), dateData.getMonth(), dateData.getDate());
			if (shippingBill.get().getShipper().getIdUser().equalsIgnoreCase(idShipper)) {
				for (Invoice invoice : shippingBill.get().getInvoices()) {
					if (invoice.getIdInvoice().equals(idInvoice) && !invoice.isXacNhanTuTaiXe()
							&& !invoice.isStatusCancel()) {
						invoice.setStatusCancel(true);
						invoice.setXacNhanTuTaiXe(true);
						invoice.setDateDeliverySuccessOrCancel(date);
						return ShipperServiceImpl.SuccessCancelInvoice;
					}
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return ShipperServiceImpl.SuccessCancelInvoice;
	}

	@Transactional
	@Override
	public List<Invoice> checkInvoicesAreCancel(List<Invoice> invoices) {
		// TODO Auto-generated method stub
		return invoices.stream().filter(invoice -> invoice.isStatusCancel()).collect(Collectors.toList());
	}

	@Transactional
	@Override
	public float getMoneyCancelInShippingBill(String idShippingBill) {
		// TODO Auto-generated method stub
		try {
			Optional<ShippingBill> shippingBill = shippingBillRepository.findById(idShippingBill);
			float moneyCancel = 0;
			for (Invoice invoice : shippingBill.get().getInvoices()) {
				if (invoice.isStatusCancel()) {
					moneyCancel += invoice.getBastketTotal().getTotalMoneyBasket();
				}
			}
			return moneyCancel;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public float getMoneyProceeds(String idShippingBill) {
		// TODO Auto-generated method stub
		try {
			Optional<ShippingBill> shippingBill = shippingBillRepository.findById(idShippingBill);
			float getProceeds = 0;
			for (Invoice invoice : shippingBill.get().getInvoices()) {
				if (invoice.isXacNhanTuTaiXe() && !invoice.isStatusCancel()) {
					getProceeds += invoice.getBastketTotal().getTotalMoneyBasket();
				}
			}
			return getProceeds;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public String chuyenDonGiaoHangChoAdminQuanLy(String idShipping, String idShipper) {
		// TODO Auto-generated method stub
		try {
			Optional<ShippingBill> shippingBill = shippingBillRepository.findById(idShipping);
			if (shippingBill.get().isXacNhanTuTaiXe() == true && shippingBill.get().isStatusShippingbill() == false) {
				shippingBill.get().setChuyenChoAdmin(true);
				java.util.Date dateData = new java.util.Date();
				Date date = new Date(dateData.getYear(), dateData.getMonth(), dateData.getDate());
				shippingBill.get().setNgayChuyenChoAdmin(date);
				shippingBillRepository.save(shippingBill.get());
				return ShipperServiceImpl.SuccessTransferredToTheManager;
			}
			shippingBill.get().setChuyenChoAdmin(true);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return ShipperServiceImpl.ErrorTransferredToTheManager;
	}

}

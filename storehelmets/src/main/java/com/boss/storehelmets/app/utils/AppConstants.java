package com.boss.storehelmets.app.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AppConstants {
	
	public static final String SUCCESS_CREATE = "thêm thành công";
	public static final String SUCCESS_DELETE = "xóa thành công";
	public static final String SUCCESS_UPDATE = "cập nhập thành công";
	public static final String ERROR_CREATE   = "thêm thất bại";
	public static final String ERROR_DELETE   = "xóa thất bại";
	public static final String ERROR_UPDATE   = "cập nhập thất bại";
	public static final String ERROR_FIND_NOT_USER     = "user không tồn tại";
	public static final String SUCCESS_BASKET = "thêm giỏ hàng vào thành công";
	public static final String ERROR_BASKET   = "thêm giỏ hàng bị lỗi";
	public static final String ERROR_LOAD     = "lỗi load data";
	public static final String SUCCESS_CREATE_USER = "đăng ký thành công";
	public static final String ERROR_CREATE_USER = "đăng ký thất bại";
	public static final String RESOURCES_BASKET = "baskets";
	public static final String SUCCESS_invoice_approval ="hóa đơn đã được duyệt";
	public static final String ERROR_invoice_approval ="duyệt hóa đơn thất bại";
	public static final String EMPLOYEE_FILE_PARAM = "file";
	public static final String SUCCESS_CODE = "EMP-200";
	public static final String SUCCESS_MSG = "Employee created successfully";
	public static final String FILE_SEPERATOR = "_";
	public static final String DOWNLOAD_PATH = "/api/v1/users/";
	public static final String DOWNLOAD_URI = "/downloadFile/{fileName:.+}";
	public static final String DEFAULT_CONTENT_TYPE = "application/octet-stream";
	public static final String FILE_DOWNLOAD_HTTP_HEADER = "attachment; filename=\"%s\"";
	public static final String FILE_PROPERTIES_PREFIX = "file";
	public static final String FILE_STORAGE_EXCEPTION_PATH_NOT_FOUND = "Could not create the directory where the uploaded files will be stored";
	public static final String INVALID_FILE_PATH_NAME = "Sorry! Filename contains invalid path sequence";
	public static final String FILE_NOT_FOUND = "File not found ";
	public static final String FILE_STORAGE_EXCEPTION = "Could not store file %s !! Please try again!";
	public static final CharSequence INVALID_FILE_DELIMITER = "..";
	public static final String INDEX_PAGE_URI = "/index";
	public static final String INDEX_PAGE = "index";
	public static final String TEMP_DIR = "C:\\Users\\Administrator\\Desktop\\image\\";
	public static final String INVALID_FILE_DIMENSIONS = "Invalid file dimensions. File dimension should note be more than 300 X 300";
	public static final String INVALID_FILE_FORMAT = "Only PNG, JPEG and JPG images are allowed";
	public static final String PNG_FILE_FORMAT = ".png";
	public static final String JPEG_FILE_FORMAT = ".jpeg";
	public static final String JPG_FILE_FORMAT = ".jpg";
	public static final String RESOURCES_IMAGE= "/resources/image";
	public static final String ADD_INVOICE_SUCCESS ="Thêm đơn hàng thành công";
	public static final String ADD_INVOICE_ERROR ="Thêm giỏ hàng thất bại";
	public static final String SUCESS_CONFIM_INVOICE = "Xác nhận Giao hàng thành công";
	public static final String ERROR_CONFIM_INVOICE = "Xác nhận Giao hàng thất bại";
	public static final String SUCCESS_ADD_SHIPPINGBILL = "Thêm bill thành công";
	public static final String ERROR_ADD_SHIPPINGBILL = "Thêm bill thất bại";
	public static final String SUCESS_CONFIM_SHIPPINGBIL = "Xác nhận Giao bill thành công";
	public static final String EROR_CONFIM_SHIPPINGBIL ="Xác nhận Giao bill thất bại";
	public static final String HISTORY_IMPORT_PRODUCT = "Thêm sản phẩm vào kho";
	public static final String HEADER_STRING = "Authorization";
	public static final String SUCCESS_ADD_INVOICE = "Bạn đã đặt hàng thành công";
	public static final String ERROR_ADD_INVOICE  = "Bạn đã đặt hàng thất bại";
			
}

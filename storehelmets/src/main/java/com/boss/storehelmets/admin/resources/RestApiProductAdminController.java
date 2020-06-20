package com.boss.storehelmets.admin.resources;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import com.boss.storehelmets.app.utils.AppConstants;
import com.boss.storehelmets.dto.MockMultipartFile;
import com.boss.storehelmets.model.ImageOfAdvertisment;
import com.boss.storehelmets.model.Product;
import com.boss.storehelmets.model.ProductImage;
import com.boss.storehelmets.model.ProductsDetails;
import com.boss.storehelmets.model.User;
import com.boss.storehelmets.repository.UserRepository;
import com.boss.storehelmets.securityjwt.JwtAuthenticationFilter;
import com.boss.storehelmets.securityjwt.JwtTokenProvider;
import com.boss.storehelmets.service.FileStorageService;
import com.boss.storehelmets.service.ImageOfAdverstismentService;
import com.boss.storehelmets.service.ProductService;
import com.boss.storehelmets.service.UserDetailServiceImpl;
import com.boss.storehelmets.service.UserSevice;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@Controller
@RequestMapping(value = "/api/v1/admins")
@Api(value="Product APIs")
public class RestApiProductAdminController {

	@Autowired
	private ProductService productService;
	
	@Autowired
	UserDetailServiceImpl userDetailsServiceImlp;
	
	@Autowired
	UserSevice userSevice;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	JwtTokenProvider tokenProvider;
	
	@Autowired
	JwtAuthenticationFilter authenticationFilter;
	
	@Autowired
	FileStorageService fileStorageService;
	
	@Autowired
	ImageOfAdverstismentService imageOfAdverstismentService; 
	
	
	
	@ApiOperation(value = "Thêm sản phẩm vào kho")
	@ApiResponses(value = {
            @ApiResponse(code=200,message="Thành công"),
            @ApiResponse(code = 401, message = "Chưa xác thực"),
            @ApiResponse(code = 403, message = "Truy cập bị cấm"),
            @ApiResponse(code = 404, message = "Không tìm thấy")
    })
	@RequestMapping(value = "/products",method = RequestMethod.POST)
	public String addNewProduct(@RequestBody Product productInput ,HttpServletRequest request) {
		try {
			String jwt = authenticationFilter.getJwtFromRequest(request);
			String userId = tokenProvider.getUserIdFromJWT(jwt);
			Optional<User> user  = userSevice.findUserById(userId);
			if (productInput != null && user != null) {
				Product product = new Product();
				Date date1 = new Date();
				java.sql.Date date = new java.sql.Date(date1.getYear(), date1.getMonth(), date1.getDate());
				product.setNameProduct(productInput.getNameProduct());
				ProductsDetails productsDetails = productInput.getProductsDetails();
				Set<ProductImage> productImages = new HashSet<ProductImage>();
				for (ProductImage productImage : productInput.getProductsDetails().getProductImages()) {
					ProductImage image = new ProductImage();
					File file = new File(AppConstants.TEMP_DIR+productImage.getImageName());
					FileInputStream input = new FileInputStream(file);
					MultipartFile multipartFile = new MockMultipartFile("file",file.getName(),
													"text/plain", IOUtils.toByteArray(input));
					String fileName = fileStorageService.storeFile(multipartFile);
					String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path(AppConstants.DOWNLOAD_PATH+AppConstants.RESOURCES_IMAGE+"/")
							.path(fileName).toUriString();	
					image.setImageName(fileDownloadUri);
					productImages.add(image);
				}
				productsDetails.setProductImages(productImages);
				product.setProductsDetails(productInput.getProductsDetails());
				product.setDateCreate(date);
				product.setUserCreat(user.get());
				productService.addProduct(product,user.get());
			}
			return AppConstants.SUCCESS_CREATE;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
			return AppConstants.ERROR_CREATE;
		}
	
	}
	
	@RequestMapping(value = "products/{id}",method = RequestMethod.DELETE)
	public String deleteProductById(@PathVariable("id") String id,HttpServletRequest request) {
		try {
			String jwt = authenticationFilter.getJwtFromRequest(request);
			String userId = tokenProvider.getUserIdFromJWT(jwt);
			UserDetails userDetails  = userDetailsServiceImlp.loadUserById(userId);
			if (userDetails!= null) {
				Collection<? extends GrantedAuthority> role = userDetails.getAuthorities();
				for (GrantedAuthority grantedAuthority : role) {
					if (grantedAuthority.getAuthority().equalsIgnoreCase("ROLE_ADMIN")) {
						productService.deleteProduct(id);
					}
				}	
			}
				return AppConstants.SUCCESS_DELETE;
		} catch (Exception e) {
			// TODO: handle exception
			return AppConstants.ERROR_DELETE;
		}
	}
	
	@RequestMapping(value = "/products",method = RequestMethod.PUT)
	public String updateProduct(@RequestBody Product productInput,HttpServletRequest request) {
		try {
			String jwt = authenticationFilter.getJwtFromRequest(request);
			String userId = tokenProvider.getUserIdFromJWT(jwt);
			Optional<User> user  = userSevice.findUserById(userId);
			if (productInput != null && user != null) {
				productService.updateProduct(productInput, user.get());
			}
			return AppConstants.SUCCESS_UPDATE;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
			return AppConstants.ERROR_UPDATE;
		}
	
	}
	
	@RequestMapping(value = "/advertisments",method = RequestMethod.POST)
	public String createAdvertisment(@RequestBody ImageOfAdvertisment advertismentInput,HttpServletRequest request) throws IOException {
		try {
			String jwt = authenticationFilter.getJwtFromRequest(request);
			String userId = tokenProvider.getUserIdFromJWT(jwt);
			Optional<User> user  = userSevice.findUserById(userId);
			
			ImageOfAdvertisment advertisment = new ImageOfAdvertisment();
			File file = new File(AppConstants.TEMP_DIR+advertismentInput.getImage());
			FileInputStream input = new FileInputStream(file);
			MultipartFile multipartFile = new MockMultipartFile("file",file.getName(),
											"text/plain", IOUtils.toByteArray(input));
			String fileName = fileStorageService.storeFile(multipartFile);
			String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path(AppConstants.DOWNLOAD_PATH+AppConstants.RESOURCES_IMAGE+"/")
					.path(fileName).toUriString();	
			advertisment.setImage(fileDownloadUri);
			advertisment.setUser(user.get());
			imageOfAdverstismentService.addImageAdverstiment(advertisment, user.get());
			return AppConstants.SUCCESS_CREATE;
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println(e.getMessage());
			
		}
		return AppConstants.ERROR_CREATE;
		
	}
	

}

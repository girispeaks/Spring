package com.hcl.retail.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.retail.entity.WishList;
import com.hcl.retail.entity.CustomerRegistration;
import com.hcl.retail.entity.ProductDetails;
import com.hcl.retail.repository.CustomerRegistrationRepository;
import com.hcl.retail.repository.ProductRepository;
import com.hcl.retail.repository.WishListRepository;

@Service
public class WishListService {
	private static final Logger logger = LoggerFactory.getLogger(WishListService.class);
	@Autowired
	ProductRepository productRepository;

	@Autowired
	WishListRepository wishListRepository;

	@Autowired
	CustomerRegistrationRepository loginRepository;

	public WishList wishListAdd(int userId, int productId, String productDeliveryTime) {
		logger.info("YOU GOT ASSESS INTO WISHLIST SERVICE>>>>>>>>> ");

		String productName = null;
		int price = 0;
		String description = null;

		List<ProductDetails> productDetails = productRepository.findByproductId(productId);
		for (ProductDetails details : productDetails) {

			productName = details.getProductName();
			price = details.getProductPrice();
			description = details.getProductDescription();
		}
		WishList addWishList = new WishList();
		addWishList.setUserId(userId);
		addWishList.setProductId(productId);
		addWishList.setProductName(productName);
		addWishList.setProductPrice(price);
		addWishList.setProductDescription(description);
		addWishList.setProductDeliveryTime(productDeliveryTime);

		WishList products = wishListRepository.save(addWishList);
		return products;
	}

	public List<CustomerRegistration> userCheck(int userId) {
		List<CustomerRegistration> users = loginRepository.findByuserId(userId);
		return users;
	}
}
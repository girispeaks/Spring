package com.hcl.retail.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.retail.entity.AddCart;
import com.hcl.retail.entity.AddProductDetails;
import com.hcl.retail.repository.AddCartRepository;
import com.hcl.retail.repository.AddProductRepository;

@Service
public class AddCartService
{
	@Autowired
	AddProductRepository productRepository;

	@Autowired
	AddCartRepository cartRepository;

	public AddCart addCheck(int userId,int productId,String productDeliveryTime)
	{

		String productname=null;
		int price=0;
		String description=null;
		
		List<AddProductDetails> productdetails =  productRepository.findByproductId(productId);
		for(AddProductDetails details:productdetails)
		{
			
			productname=details.getProductName();
			price=details.getProductPrice();
			description=details.getProductDescription();
		}
		AddCart addcartdetails=new AddCart();
		addcartdetails.setUserId(userId);
		addcartdetails.setProductId(productId);
		addcartdetails.setProductName(productname);
		addcartdetails.setProductPrice(price);
		addcartdetails.setProductDescription(description);
		addcartdetails.setProductDeliveryTime(productDeliveryTime);
		
		AddCart users =  cartRepository.save(addcartdetails);
		return users;
	}
}
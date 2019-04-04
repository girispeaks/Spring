package com.hcl.retail.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.retail.entity.AddProductDetails;
import com.hcl.retail.repository.AddProductRepository;

@Service
public class AddProductService
{
	@Autowired
	AddProductRepository productRepository;

	public AddProductDetails productCheck(AddProductDetails products)
	{
		AddProductDetails items =  productRepository.save(products);
		return items;
	}
}
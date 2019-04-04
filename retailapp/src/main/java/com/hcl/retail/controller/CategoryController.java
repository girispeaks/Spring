package com.hcl.retail.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.retail.entity.CategoryDetails;
import com.hcl.retail.responsemodel.CategoryResponse;
import com.hcl.retail.service.CategoryService;

@CrossOrigin
@RestController
@RequestMapping(value = "/admin")
public class CategoryController {
	@Autowired
	CategoryService categoryService;
	private static final Logger logger = LoggerFactory.getLogger(CategoryController.class);

	@PostMapping(value = "/category/add", produces = "application/json")
	public CategoryResponse categoryCheck(@RequestBody CategoryDetails categorydetails) {
		logger.info("You are in CategoryController ");
		CategoryDetails categoryDetail = categoryService.categoryCheck(categorydetails);
		if (categoryDetail.getCategoryImage() != null) {
			CategoryResponse categoryResponse = new CategoryResponse();
			categoryResponse.setCategoryDetail(categoryDetail);
			categoryResponse.setMessage("Category added successfully..");
			logger.debug("YOU HAVE ADDED CATEGORY SUCCESSFULLY ");
			categoryResponse.setStatusCode("200");
			categoryResponse.setStatusMessage("OK");
			return categoryResponse;
		} else {
			CategoryResponse categoryResponse = new CategoryResponse();
			categoryResponse.setCategoryDetail(categoryDetail);
			categoryResponse.setMessage("sorry can't add category...");
			logger.warn("CATEGORY CAN'T ADDED..... ");
			categoryResponse.setStatusCode("200");
			categoryResponse.setStatusMessage("OK");
			return categoryResponse;
		}
	}
}

package com.hcl.retail.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.retail.entity.AddCategoryDetails;
import com.hcl.retail.entity.CustomerRegistration;
import com.hcl.retail.responsemodel.CustomerRegistrationResponse;
import com.hcl.retail.service.AddCategoryService;

@RestController
@RequestMapping(value = "/admin")
public class AddCategoryController {
	@Autowired
	AddCategoryService categoryService;
	@PostMapping(value = "/category", produces = "application/json")
	public String categoryCheck(@RequestBody AddCategoryDetails categorydetails) {
		AddCategoryDetails categorydetail = categoryService.categoryCheck(categorydetails);
		if(categorydetail!=null){
			return "category added sucessfully";
		}
		else{
			return "category doesnt added";
		}
}
}

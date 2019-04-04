package com.hcl.retail.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.retail.entity.WishList;
import com.hcl.retail.repository.WishListRepository;

@Service
public class MyWishListService {
	private static final Logger logger = LoggerFactory.getLogger(MyWishListService.class);
	@Autowired
	WishListRepository listRepository;

	public List<WishList> myListCheck(int userId) {
		logger.info("YOU GOT ASSESS INTO MY WISHLIST SERVICE>>>>>>>>> ");
		List<WishList> listdetails = listRepository.findByuserId(userId);
		return listdetails;
	}
}
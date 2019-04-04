package com.hcl.retail.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hcl.retail.entity.CartDetails;
import com.hcl.retail.entity.ProductDetails;

@Repository
public interface CartRepository extends JpaRepository<CartDetails, String>{
	public List<CartDetails> findByuserId(int userId);
}
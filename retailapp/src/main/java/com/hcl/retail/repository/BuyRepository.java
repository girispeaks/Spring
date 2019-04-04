package com.hcl.retail.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hcl.retail.entity.WishList;
import com.hcl.retail.entity.BoughtProducts;

@Repository
public interface BuyRepository extends JpaRepository<BoughtProducts, String> {

	public List<BoughtProducts> findByuserId(int userId);
}
package com.hcl.retail.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hcl.retail.entity.WishList;

@Repository
public interface WishListRepository extends JpaRepository<WishList, String> {
	public List<WishList> findByuserId(int userId);
}
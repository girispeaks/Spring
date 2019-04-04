package com.hcl.retail.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "wish_list")
public class WishList implements Serializable {

	private static final long serialVersionUID = -5901129703546095407L;

	@Id
	@Column(name = "list_id")
	private int cartId;

	@Column(name = "user_id")
	private int userId;

	@Column(name = "product_id")
	private int productId;

	@Column(name = "product_name")
	private String productName;

	@Column(name = "product_price")
	private int productPrice;

	@Column(name = "product_description")
	private String productDescription;

	@Column(name = "product_delivery_time")
	private String productDeliveryTime;

	public int getCartId() {
		return cartId;
	}

	public void setCartId(int cartId) {
		this.cartId = cartId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public int getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(int productPrice) {
		this.productPrice = productPrice;
	}

	public String getProductDescription() {
		return productDescription;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	public String getProductDeliveryTime() {
		return productDeliveryTime;
	}

	public void setProductDeliveryTime(String productDeliveryTime) {
		this.productDeliveryTime = productDeliveryTime;
	}

}

package com.hcl.retail.entity;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "category_details")
public class CategoryDetails implements Serializable {

	private static final long serialVersionUID = -5901129703546095407L;

	@Id
	@Column(name = "category_id")
	private int categoryId;
	
	@Column(name = "category_type")
	private String categoryType;
	
	@Column(name = "category_image")
	private String categoryImage;

	
	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryType() {
		return categoryType;
	}

	public void setCategoryType(String categoryType) {
		this.categoryType = categoryType;
	}

	public String getCategoryImage() {
		return categoryImage;
	}

	public void setCategoryImage(String categoryImage) {
		this.categoryImage = categoryImage;
	}
	
	
	
}
	
	
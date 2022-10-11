package com.sbw.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="product_tbl")
public class Product {
	
	@Id
	private String productId;
	
	@NotNull(message = "ProductName is required")
	private String productName;
	
	@Min(1)
	private double productQty;
	
	@Min(1)
	private double productPrice;
	
	@NotNull(message = "ProductName is required")
	private String productType;
	
	public Product() {
		super();
		
	}
	@Override
	public String toString() {
		return "Product [productId=" + productId + ", productName=" + productName + ", productQty=" + productQty
				+ ", productPrice=" + productPrice + ", productType=" + productType + "]";
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public @Min(1) double getProductQty() {
		return productQty;
	}
	public void setProductQty(double productQty) {
		this.productQty = productQty;
	}
	public double getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(double productPrice) {
		this.productPrice = productPrice;
	}
	public String getProductType() {
		return productType;
	}
	public void setProductType(String productType) {
		this.productType = productType;
	}
	public Product(String productId, String productName, double productQty, double productPrice, String productType) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.productQty = productQty;
		this.productPrice = productPrice;
		this.productType = productType;
	}
	
	
	

}

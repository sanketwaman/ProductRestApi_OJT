package com.sbw.dao;

import java.util.List;

import com.sbw.entity.Product;

public interface ProductDao {
	public boolean saveProduct(Product product);

	public List<Product> getAllProduct();

	public Product getProductById(String productId);

	public boolean deleteProduct(String productId);

	public boolean updateProduct(Product product);
	
	public int uploadProductList(List<Product> list);

}

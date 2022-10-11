package com.sbw.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.sbw.entity.Product;

public interface ProductService {

	public boolean saveProduct(Product product);

	public List<Product> getAllProduct();

	public Product getProductById(String productId);

	public boolean deleteProduct(String productId);

	public boolean updateProduct(Product product);

	public List<Product> sortProducts(String sortBy);

	public double sumOfProductPrice();

	public int getTotalCountOfProducts();

	public Product getMaxPriceProducts();

	public Map<String, Object> uploadSheet(CommonsMultipartFile file, HttpSession httpSession); // read data from excel

	// and write into DB

	public String exportToExcel(HttpSession session);

}

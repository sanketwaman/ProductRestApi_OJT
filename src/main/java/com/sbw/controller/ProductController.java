package com.sbw.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.sbw.entity.Product;
import com.sbw.exeption.ProductNotFoundException;
import com.sbw.service.ProductService;

@RestController
public class ProductController {

	@Autowired
	private ProductService service;

	@PostMapping(value = "/saveProduct")
	public ResponseEntity<Boolean> saveProduct(@Valid @RequestBody Product product) {

		boolean isAdded = service.saveProduct(product);
		if (isAdded) {
			return new ResponseEntity<Boolean>(isAdded, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<Boolean>(isAdded, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@GetMapping(value = "getAllProduct")
	public ResponseEntity<List<Product>> getAllProduct() {
		List<Product> allProducts = service.getAllProduct();
		if (!allProducts.isEmpty()) {
			return new ResponseEntity<List<Product>>(allProducts, HttpStatus.OK);
		}
		throw new ProductNotFoundException("Product Not Found ");
	}

	@GetMapping(value = "getProductById")
	public ResponseEntity<Product> getProductById(@PathVariable String productId) {
		Product product = service.getProductById(productId);
		if (product == null) {
			return new ResponseEntity<Product>(HttpStatus.NO_CONTENT);
		} else
			throw new ProductNotFoundException("Product Not Found For ID" + productId); // **********
	}

	@DeleteMapping(value = "deleteProduct")
	public ResponseEntity<Boolean> deleteProductById(@RequestParam String productId) {
		boolean isDeleted = service.deleteProduct(productId);
		if (isDeleted) {
			return new ResponseEntity<Boolean>(isDeleted, HttpStatus.OK);
		} else {
			throw new ProductNotFoundException("Product Not Found For ID" + productId);
		}

	}

	@PutMapping(value = "updateProduct")
	public ResponseEntity<Boolean> updateProduct(@RequestBody Product product) {
		boolean isUpdated = service.updateProduct(product);
		if (isUpdated) {
			return new ResponseEntity<Boolean>(isUpdated, HttpStatus.OK);
		} else
			throw new ProductNotFoundException("Product Not Found For Update" + product.getProductId());

	}

	@GetMapping(value = "sortProduct")
	public ResponseEntity<List<Product>> sortProducts(@RequestParam String sortBy) {
		List<Product> allProduct = service.sortProducts(sortBy);
		if (allProduct.isEmpty()) {
			return new ResponseEntity<List<Product>>(HttpStatus.NO_CONTENT);
		} else
			return new ResponseEntity<List<Product>>(allProduct, HttpStatus.OK);
	}

	@GetMapping(value = "/getMaxPriceProduct")
	public ResponseEntity<Product> getMaxPriceProduct() {
		Product product = service.getMaxPriceProducts();
		if (product != null) {
			return new ResponseEntity<Product>(product, HttpStatus.OK);
		} else {
			return new ResponseEntity<Product>(HttpStatus.NO_CONTENT);
		}

	}

	@GetMapping(value = "/getTotalCountOfProducts")
	public ResponseEntity<Integer> getTotalCountOfProducts() {
		int count = service.getTotalCountOfProducts();
		if (count > 0)
			return new ResponseEntity<Integer>(count, HttpStatus.OK);
		else {
			return new ResponseEntity<Integer>(HttpStatus.NO_CONTENT);

		}

	}

	@GetMapping(value = "/sumOfProductPrice")
	public ResponseEntity<Double> sumOfProductPrice() {
		double sum = service.sumOfProductPrice();

		if (sum > 0)
			return new ResponseEntity<Double>(sum, HttpStatus.OK);
		else {
			return new ResponseEntity<Double>(HttpStatus.NO_CONTENT);

		}

	}

	@PostMapping(value = "/uploadSheet")
	public ResponseEntity<Map<String, Object>> uploadSheet(@RequestParam CommonsMultipartFile file,
			HttpSession session) {
		Map<String, Object> map = service.uploadSheet(file, session);
		return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);

	}

	@GetMapping(value = "/exportToExcel")
	public ResponseEntity<String> exportToExcel(HttpSession session) {
		String path = service.exportToExcel(session);
		return new ResponseEntity<String>(path, HttpStatus.OK);

	}

}

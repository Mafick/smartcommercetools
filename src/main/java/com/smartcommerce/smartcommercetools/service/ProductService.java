package com.smartcommerce.smartcommercetools.service;

import com.commercetools.api.models.product.Product;

import java.util.List;

public interface ProductService {

	List<Product> getAllProducts();

	void createProduct(String name, String description, String key);

	void deleteProduct(String id, String version);

}

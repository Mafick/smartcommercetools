package com.smartcommerce.smartcommercetools.service;

import com.commercetools.api.models.product.Product;

import java.util.List;

public interface ProductService {

	List<Product> getAllProducts();

	Product getProduct(String id);

	void createProduct(String name, String description, String key);

	void updateProduct(String id, Long version, String name, String description);

	void deleteProduct(String id, String version);

}

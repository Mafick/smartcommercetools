package com.smartcommerce.smartcommercetools.service.impl;

import com.commercetools.api.client.ProjectApiRoot;
import com.commercetools.api.models.product_type.ProductType;
import com.commercetools.api.models.product_type.ProductTypePagedQueryResponse;
import org.springframework.stereotype.Service;

import java.util.List;

import com.smartcommerce.smartcommercetools.client.CommercetoolsClient;
import com.smartcommerce.smartcommercetools.service.ProductTypeService;

@Service
public class CommercetoolsProductTypeService implements ProductTypeService {

	private final CommercetoolsClient commercetoolsClient;

	public CommercetoolsProductTypeService(CommercetoolsClient commercetoolsClient) {
		this.commercetoolsClient = commercetoolsClient;
	}

	@Override
	public List<ProductType> getAllProductTypes() {
		ProjectApiRoot apiRoot = commercetoolsClient.createApiClient();

		// Get the new ProductType
		ProductTypePagedQueryResponse allProductTypes = apiRoot
				.productTypes()
				.get()
				.executeBlocking()
				.getBody();

		return allProductTypes.getResults();
	}
}

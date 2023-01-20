package com.smartcommerce.smartcommercetools.service.impl;

import com.commercetools.api.client.ProjectApiRoot;
import com.commercetools.api.models.product.Product;
import com.commercetools.api.models.product.ProductDraft;
import com.commercetools.api.models.product.ProductPagedQueryResponse;
import com.commercetools.api.models.product.ProductUpdate;
import io.vrap.rmf.base.client.ApiHttpResponse;
import org.springframework.stereotype.Service;

import java.util.List;

import com.smartcommerce.smartcommercetools.client.CommercetoolsClient;
import com.smartcommerce.smartcommercetools.constant.LocConst;
import com.smartcommerce.smartcommercetools.service.ProductService;


@Service
public class CommercetoolsProductService implements ProductService {

	private final CommercetoolsClient commercetoolsClient;

	public CommercetoolsProductService(CommercetoolsClient commercetoolsClient) {
		this.commercetoolsClient = commercetoolsClient;
	}

	@Override
	public List<Product> getAllProducts() {
		ProjectApiRoot apiRoot = commercetoolsClient.createApiClient();

		ProductPagedQueryResponse allProducts = apiRoot
				.products()
				.get()
				.executeBlocking()
				.getBody();

		return allProducts.getResults();
	}

	@Override
	public Product getProduct(String id) {
		ProjectApiRoot apiRoot = commercetoolsClient.createApiClient();

		Product product = apiRoot
				.products()
				.withId(id)
				.get()
				.executeBlocking()
				.getBody();

		return product;
	}

	@Override
	public void createProduct(String name, String description, String key) {
		ProjectApiRoot apiRoot = commercetoolsClient.createApiClient();

		ProductDraft newProductDetails = ProductDraft
				.builder()
				.name(stringBuilder ->
						stringBuilder
								.addValue(LocConst.LOC_EN_US, name)
								.addValue(LocConst.LOC_DE_DE, name)
				)
				.productType(typeBuilder -> typeBuilder.id("5da6ed0e-ef5a-4a1f-86ea-c3ab57556531"))
				.slug(stringBuilder ->
						stringBuilder
								.addValue(LocConst.LOC_EN_US, String.format("%s-%s", key, LocConst.LOC_EN_US))
								.addValue(LocConst.LOC_DE_DE, String.format("%s-%s", key, LocConst.LOC_DE_DE))
				)
				.description(stringBuilder ->
						stringBuilder
								.addValue(LocConst.LOC_EN_US, description)
								.addValue(LocConst.LOC_DE_DE, description)
				)
				.key(key)
				.build();

		ApiHttpResponse<Product> response = apiRoot
				.products()
				.post(newProductDetails)
				.executeBlocking();

		System.out.println(response);
	}

	@Override
	public void updateProduct(String id, Long version, String name, String description) {
		ProjectApiRoot apiRoot = commercetoolsClient.createApiClient();

		ProductUpdate productUpdate = ProductUpdate
				.builder()
				.version(version)
				.plusActions(actionBuilderName -> actionBuilderName
						.changeNameBuilder()
						.name(stringBuilder -> stringBuilder
								.addValue(LocConst.LOC_EN_US, name)
								.addValue(LocConst.LOC_DE_DE, name)
						)
				)
				.plusActions(actionBuilderDescription -> actionBuilderDescription
						.setDescriptionBuilder()
						.description(stringBuilder -> stringBuilder
								.addValue(LocConst.LOC_EN_US, description)
								.addValue(LocConst.LOC_DE_DE, description)
						)
				)
				.build();

		ApiHttpResponse<Product> response = apiRoot
				.products()
				.withId(id)
				.post(productUpdate)
				.executeBlocking();

		System.out.println(response);
	}

	@Override
	public void deleteProduct(String id, String version) {
		ProjectApiRoot apiRoot = commercetoolsClient.createApiClient();

		ApiHttpResponse<Product> response = apiRoot
				.products()
				.withId(id)
				.delete()
				.withVersion(version)
				.executeBlocking();

		System.out.println(response);
	}

}

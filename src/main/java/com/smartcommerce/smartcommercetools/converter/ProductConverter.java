package com.smartcommerce.smartcommercetools.converter;

import com.commercetools.api.models.product.Product;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.smartcommerce.smartcommercetools.constant.LocConst;
import com.smartcommerce.smartcommercetools.data.ProductData;

@Component
public class ProductConverter implements Converter<Product, ProductData> {

	@Override
	public ProductData convert(Product source) {
		ProductData productData = new ProductData();
		productData.setId(source.getId());
		productData.setVersion(source.getVersion());
		productData.setName(source.getMasterData().getCurrent().getName().get(LocConst.LOC_DE_DE));
		productData.setDescription(source.getMasterData().getCurrent().getDescription().get(LocConst.LOC_DE_DE));
		productData.setKey(source.getKey());

		return productData;
	}
}

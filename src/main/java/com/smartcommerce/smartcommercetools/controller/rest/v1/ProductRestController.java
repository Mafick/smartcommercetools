package com.smartcommerce.smartcommercetools.controller.rest.v1;

import com.commercetools.api.models.product.Product;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

import com.smartcommerce.smartcommercetools.constant.CtrlConst;
import com.smartcommerce.smartcommercetools.converter.ProductConverter;
import com.smartcommerce.smartcommercetools.data.ProductData;
import com.smartcommerce.smartcommercetools.service.ProductService;

@RestController
public class ProductRestController {

	private final ProductService productService;
	private final ProductConverter productConverter;

	public ProductRestController(ProductService productService, ProductConverter productConverter) {
		this.productService = productService;
		this.productConverter = productConverter;
	}

	@ResponseBody
	@RequestMapping(value = CtrlConst.V1 + CtrlConst.PRODUCT_URL, method = RequestMethod.GET, consumes = {MediaType.APPLICATION_JSON_VALUE})
	public List<ProductData> productPage() {
		List<Product> allProducts = productService.getAllProducts();
		List<ProductData> allProductData = new ArrayList<>();
		for (Product product : allProducts) {
			ProductData productData = productConverter.convert(product);
			allProductData.add(productData);
		}

		return allProductData;
	}
}

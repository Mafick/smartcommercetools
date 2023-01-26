package com.smartcommerce.smartcommercetools.controller.page;

import com.commercetools.api.models.product.Product;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

import com.smartcommerce.smartcommercetools.constant.CtrlConst;
import com.smartcommerce.smartcommercetools.converter.ProductConverter;
import com.smartcommerce.smartcommercetools.data.ProductData;
import com.smartcommerce.smartcommercetools.service.ProductService;

@Controller
public class ProductPageController extends AbstractPageController {

	private final ProductService productService;
	private final ProductConverter productConverter;

	public ProductPageController(ProductService productService, ProductConverter productConverter) {
		this.productService = productService;
		this.productConverter = productConverter;
	}

	@RequestMapping(value = CtrlConst.PRODUCT_URL, method = RequestMethod.GET)
	public String productPage(Model model) {

		List<Product> allProducts = productService.getAllProducts();
		model.addAttribute("allProducts", allProducts);

		return CtrlConst.PRODUCT_PAGE;
	}


	@RequestMapping(value = CtrlConst.PRODUCT_URL, method = RequestMethod.POST)
	public String createProduct(@ModelAttribute("productData") ProductData productData) {
		System.out.println("--- CREATE PRODUCT ---");
		System.out.println(productData);

		productService.createProduct(productData.getName(), productData.getDescription(), productData.getKey());

		return CtrlConst.REDIRECT + CtrlConst.PRODUCT_URL;
	}

	@RequestMapping(value = CtrlConst.PRODUCT_DELETE_URL + "{id}/{version}", method = RequestMethod.POST)
	public String deleteProduct(@PathVariable("id") String id, @PathVariable("version") String version) {
		System.out.println("--- DELETE PRODUCT ---");
		System.out.println("ID: " + id);
		System.out.println("Version: " + version);

		productService.deleteProduct(id, version);

		return CtrlConst.REDIRECT + CtrlConst.PRODUCT_URL;
	}

	@RequestMapping(value = CtrlConst.PRODUCT_DETAILS_URL + "{id}", method = RequestMethod.GET)
	public String productDetailsPage(@PathVariable("id") String id, Model model) {
		Product product = productService.getProduct(id);
		ProductData productData = productConverter.convert(product);
		model.addAttribute("productData", productData);

		return CtrlConst.PRODUCT_DETAILS_PAGE;
	}

	@RequestMapping(value = CtrlConst.PRODUCT_UPDATE_URL, method = RequestMethod.POST)
	public String updateProduct(@ModelAttribute ProductData productData) {
		System.out.println("--- UPDATE PRODUCT ---");
		System.out.println("ID: " + productData.getId());
		System.out.println("Version: " + productData.getVersion());

		productService.updateProduct(productData.getId(), productData.getVersion(), productData.getName(), productData.getDescription());

		return CtrlConst.REDIRECT + CtrlConst.PRODUCT_URL;
	}
}

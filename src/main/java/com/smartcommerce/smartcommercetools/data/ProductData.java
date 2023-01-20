package com.smartcommerce.smartcommercetools.data;

import lombok.Data;

@Data
public class ProductData {

	private String id;
	private Long version;

	private String name;
	private String description;
	private String key;
}

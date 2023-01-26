package com.smartcommerce.smartcommercetools.controller.page;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;

public abstract class AbstractPageController {

	@ModelAttribute("lang")
	public String setLang(String lang) {
		return StringUtils.isNotEmpty(lang) ? lang : "en-US";
	}
}

package com.runying.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.runying.po.Product;
import com.runying.service.ProductService;

@Controller
@RequestMapping(value={"font-design/warehouse_main/"})
public class ProductController {
	@Autowired
	private ProductService productService;
	
	@RequestMapping("/getProducts.do")
	@ResponseBody
	public List<Product> getProducts() {
		return productService.getAllUndeletedProduct();
	}
	
}

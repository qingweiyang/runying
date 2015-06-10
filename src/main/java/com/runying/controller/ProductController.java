package com.runying.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.runying.dao.ProductDao;
import com.runying.po.Product;

@Controller
@RequestMapping(value={"font-design/warehouse_main/"})
public class ProductController {
	@Autowired
	private ProductDao productDaoProxy;
	
	@RequestMapping("/getProducts.do")
	@ResponseBody
	public List<Product> getProducts() {
		return productDaoProxy.findAll();
	}
	
}

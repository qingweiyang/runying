package com.runying.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.runying.dao.ProductDao;
import com.runying.po.Product;

@Controller
@RequestMapping(value={"font-design/warehouse_main/"})
public class ProductController {
	
	private ProductDao productDao = new ProductDao();
	
	@RequestMapping("/getProducts.do")
	@ResponseBody
	public List<Product> getProducts() {
		return productDao.findAll();
	}
	
}

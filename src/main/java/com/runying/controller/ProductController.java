package com.runying.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	public List<Product> getProducts(String search) {
		Map<String, Object> conds = new HashMap<String, Object>();
		//支持根据 产品名，规格型号1，规格型号2，物料长代码，材质，产品名拼音首字母缩写 模糊搜索
		conds.put("materialName", search);
		conds.put("size1", search);
		conds.put("size2", search);
		conds.put("materialCode", search);
		conds.put("material", search);
		conds.put("pinyin", search);
		
		return productService.findProducts(conds, 1, productService.size()).getRows();
	}
	
}

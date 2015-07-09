package com.runying.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.runying.po.Product;
import com.runying.service.ProductService;
import com.runying.util.Constants;
import com.runying.util.Msg;
import com.runying.vo.TableVO;

@Controller
@RequestMapping(value={"font-design/admConfig/"})
public class AdmProductController {
	@Autowired
	private ProductService productService;
	
	@RequestMapping(value = "getProductsByConditions.do")
	@ResponseBody
	public TableVO<Product> getProductsByConditions(int currentPage, int countPerPage, String search) {
		Map<String, Object> conds = new HashMap<String, Object>();
		//支持根据 产品名，规格型号1，规格型号2，物料长代码，材质，产品名拼音首字母缩写 模糊搜索
		conds.put("materialName", search);
		conds.put("size1", search);
		conds.put("size2", search);
		conds.put("materialCode", search);
		conds.put("material", search);
		conds.put("pinyin", search);
		
		return productService.findProducts(conds, currentPage, countPerPage);
	}
	
	@RequestMapping(value = "getSingleProduct.do")
	@ResponseBody
	public Product getSingleProduct(int id) {
		return productService.findByID(id);
	}
	
	/**
	 * 删除产品
	 * 
	 * @param currentPage
	 * @param countPerPage
	 * @return
	 */
	@RequestMapping(value = "deleteProduct.do")
	@ResponseBody
	public Msg deleteProduct(int id) {
		return productService.deleteProduct(id, Constants.user);
	}
	
	/**
	 * 添加用户
	 * 
	 * @param 　user
	 * @return
	 */
	@RequestMapping(value = "addProduct.do")
	@ResponseBody
	public Msg addProduct(@RequestBody Product p) {
		return productService.addProduct(p, Constants.user);
	}
	
	/**
	 * 修改产品
	 * @param currentPage
	 * @param countPerPage
	 * @return
	 */
	@RequestMapping(value = "editProduct.do")
	@ResponseBody
	public Msg editProduct(@RequestBody Product p) {
		return productService.editProduct(p, Constants.user);
	}
	
	/**
	 * 模糊搜索
	 * 
	 * @param currentPage
	 * @param countPerPage
	 * @param os
	 * @return
	 */
//	@RequestMapping(value = "searchProduct.do")
//	@ResponseBody
//	public TableVO<Product> searchProduct(int currentPage, int countPerPage, String cons) {
//		Map<String, Object> condition = new HashMap<String, Object>();
//		System.out.println("......................"+cons);
//		//参数列表 [产品名]
//		condition.put("materialName", cons);
//		
//		return productService.searchProduct(condition, currentPage, countPerPage);
//	}
}

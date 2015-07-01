package com.runying.controller;

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
	
	@RequestMapping(value = "getUndeletedProduct.do")
	@ResponseBody
	public TableVO<Product> getUndeletedProduct(int currentPage, int countPerPage) {
		return productService.getUndeletedProduct(currentPage, countPerPage);
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
}

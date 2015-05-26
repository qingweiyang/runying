package com.runying.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.runying.dao.ProductDao;
import com.runying.po.Orders;
import com.runying.po.Product;
import com.runying.util.Msg;

@Controller
@RequestMapping(value={"font-design/warehouse_main/"})
public class OrdersController {
	
	private ProductDao productDao = new ProductDao();
	
	@RequestMapping("/ordersIn.do")
	public Msg ordersIn(Orders o) {
		Msg msg = new Msg();
		msg.setStatus(1);
		
		productDao.addObject(o);
		
		return msg;
	}
	
	@RequestMapping("/getProducts.do")
	@ResponseBody
	public List<Product> getProducts() {
		return productDao.findAll();
	}
	
}

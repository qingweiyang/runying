package com.runying.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.runying.dao.OrdersDao;
import com.runying.po.Orders;
import com.runying.util.Constants;
import com.runying.util.Msg;

@Controller
@RequestMapping(value={"font-design/warehouse_main/"})
public class OrdersController {
	
	private OrdersDao ordersDao = new OrdersDao();
	
	@RequestMapping("/ordersIn.do")
	@ResponseBody
	public Msg ordersIn(@RequestBody Orders o) {
		Msg msg;
		
		o.setOperator(Constants.user);
		msg = ordersDao.addOrders(o);
		
		return msg;
	}
	
	@RequestMapping(value = "getAllOrders.do")
	@ResponseBody
	public List<Orders> getOrders() {
		return ordersDao.findAll();
	}
	
}

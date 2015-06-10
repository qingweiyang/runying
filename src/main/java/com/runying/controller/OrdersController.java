package com.runying.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
	@Autowired
	private OrdersDao ordersDaoProxy;
	
	@RequestMapping("/ordersIn.do")
	@ResponseBody
	public Msg ordersIn(@RequestBody Orders o) {
		Msg msg;
		
		o.setOperator(Constants.user);
		msg = ordersDaoProxy.addOrders(o);
		
		return msg;
	}
	
	@RequestMapping(value = "getAllOrders.do")
	@ResponseBody
	public List<Orders> getOrders() {
		return ordersDaoProxy.findAll();
	}
	
	/**
	 * 获取未经录入工序的订单
	 * 
	 * @return
	 */
	@RequestMapping(value = "getUncheckedOrders.do")
	@ResponseBody
	public List<Orders> getUncheckedOrders() {
		return ordersDaoProxy.findByStatus(1);
	}
	
	/**
	 * 获取已经录入工序的订单
	 * 
	 * @return
	 */
	@RequestMapping(value = "getCheckedOrders.do")
	@ResponseBody
	public List<Orders> getCheckedOrders() {
		return ordersDaoProxy.findByStatus(2);
	}
	
}

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
import com.runying.vo.TableVO;

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
	public TableVO<Orders> getOrders(int currentPage, int countPerPage) {
		List<Orders> os = ordersDaoProxy.findAll(currentPage, countPerPage);
		TableVO<Orders> tvo = new TableVO<Orders>();
		tvo.setRows(os);
		tvo.setCurrentPage(currentPage);
		tvo.setCountPerPage(countPerPage);
		tvo.setPages((ordersDaoProxy.size()-1) / countPerPage + 1);
		return tvo;
	}
	
	/**
	 * 获取未经录入工序的订单
	 * 
	 * @return
	 */
	@RequestMapping(value = "getUncheckedOrders.do")
	@ResponseBody
	public TableVO<Orders> getUncheckedOrders(int currentPage, int countPerPage) {
		List<Orders> os = ordersDaoProxy.findByStatus(1, currentPage, countPerPage);
		TableVO<Orders> tvo = new TableVO<Orders>();
		tvo.setRows(os);
		tvo.setCurrentPage(currentPage);
		tvo.setCountPerPage(countPerPage);
		tvo.setPages((ordersDaoProxy.sizeWithStatus(1)-1) / countPerPage + 1);
		return tvo;
	}
	
	/**
	 * 获取已经录入工序的订单
	 * 
	 * @return
	 */
	@RequestMapping(value = "getCheckedOrders.do")
	@ResponseBody
	public TableVO<Orders> getCheckedOrders(int currentPage, int countPerPage) {
		List<Orders> os = ordersDaoProxy.findByStatus(2, currentPage, countPerPage);
		TableVO<Orders> tvo = new TableVO<Orders>();
		tvo.setRows(os);
		tvo.setCurrentPage(currentPage);
		tvo.setCountPerPage(countPerPage);
		tvo.setPages((ordersDaoProxy.sizeWithStatus(2)-1) / countPerPage + 1);
		return tvo;
	}
	
}

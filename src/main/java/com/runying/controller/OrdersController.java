package com.runying.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.runying.dao.OrdersDao;
import com.runying.dao.UserDao;
import com.runying.po.Orders;
import com.runying.service.OrdersService;
import com.runying.util.Constants;
import com.runying.util.Msg;
import com.runying.vo.OrdersDetailVO;
import com.runying.vo.ProcessOrdersTableVO;
import com.runying.vo.TableVO;

@Controller
@RequestMapping(value={"font-design/warehouse_main/"})
public class OrdersController {
	@Autowired
	private OrdersDao ordersDaoProxy;
	
	@Autowired
	private UserDao userDaoProxy;
	
	@Autowired
	private OrdersService ordersService;
	
	
	@RequestMapping("/ordersIn.do")
	@ResponseBody
	public Msg ordersIn(@RequestBody Orders o) {
		return ordersService.addOrders(o, Constants.user);
	}
	
	@RequestMapping(value = "getAllOrders.do")
	@ResponseBody
	public TableVO<OrdersDetailVO> getOrders(int currentPage, int countPerPage) {
		return ordersService.getUndeleteOrdersTVO(currentPage, countPerPage);
	}
	
	/**
	 * 获取未经录入工序的订单
	 * 
	 * @return
	 */
	@RequestMapping(value = "getUncheckedOrders.do")
	@ResponseBody
	public TableVO<ProcessOrdersTableVO> getUncheckedOrders(int currentPage, int countPerPage) {
		List<Object> status = new ArrayList<Object>();
		status.add(1);
		return ordersService.getOrdersVOByMultiStatus(status, currentPage, countPerPage);
	}
	
	/**
	 * 获取已经录入工序的订单
	 * 
	 * @return
	 */
	@RequestMapping(value = "getCheckedOrders.do")
	@ResponseBody
	public TableVO<ProcessOrdersTableVO> getCheckedOrders(int currentPage, int countPerPage) {
		List<Object> status = new ArrayList<Object>();
		status.add(2);
		status.add(3);
		status.add(4);
		status.add(5);
		return ordersService.getOrdersVOByMultiStatus(status, currentPage, countPerPage);
	}
	
	/**
	 * 获取已经录入库的订单
	 * 
	 * @return
	 */
	@RequestMapping(value = "getInWarehouseOrders.do")
	@ResponseBody
	public TableVO<ProcessOrdersTableVO> getInWarehouseOrders(int currentPage, int countPerPage) {
		List<Object> status = new ArrayList<Object>();
		status.add(2);
		status.add(3);
		status.add(4);
		return ordersService.getOrdersVOByMultiStatus(status, currentPage, countPerPage);
	}
}

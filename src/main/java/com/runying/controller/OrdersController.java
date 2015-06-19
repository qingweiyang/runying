package com.runying.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.runying.dao.OrdersDao;
import com.runying.dao.UserDao;
import com.runying.po.Orders;
import com.runying.po.User;
import com.runying.service.OrdersService;
import com.runying.util.Constants;
import com.runying.util.Msg;
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
		Msg msg = new Msg();
		o.setOperator(Constants.user);
		User resDB = userDaoProxy.findByUsername(Constants.user.getUsername());
		//检查操作员是否拥有  录入订单信息  的权限
		if(1 != (resDB.getPrivilege() & 1)) {
			msg.setStatus(0);
			msg.setDescription("权限不足");
			return msg;
		}
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
	public TableVO<ProcessOrdersTableVO> getUncheckedOrders(int currentPage, int countPerPage) {
		return ordersService.getOrdersVOByStatus(1, currentPage, countPerPage);
	}
	
	/**
	 * 获取已经录入工序的订单
	 * 
	 * @return
	 */
	@RequestMapping(value = "getCheckedOrders.do")
	@ResponseBody
	public TableVO<ProcessOrdersTableVO> getCheckedOrders(int currentPage, int countPerPage) {
		return ordersService.getOrdersVOByStatus(2, currentPage, countPerPage);
	}
	
	/**
	 * 获取已经录入库的订单
	 * 
	 * @return
	 */
	@RequestMapping(value = "getInWarehouseOrders.do")
	@ResponseBody
	public TableVO<ProcessOrdersTableVO> getInWarehouseOrders(int currentPage, int countPerPage) {
		return ordersService.getOrdersVOByStatus(3, currentPage, countPerPage);
	}
}

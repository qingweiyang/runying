package com.runying.dao;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.runying.po.Orders;
import com.runying.util.DaoUtil;
import com.runying.util.Msg;

public class OrdersDao extends DaoUtil{
	
	public List<Orders> findByStatus(int status, int pageNumber, int countPerPage) {
		return this.findByColumn("status", status, pageNumber, countPerPage);
	}
	
	public Long sizeWithStatus(int status) {
		return (long) this.findByColumn("status", status).size();
	}
	
	/**
	 * add an order, return false if the user has not the privilege. System adds current time as the time the recorded in system
	 *  
	 * @param o
	 * @return
	 */
	public Msg addOrders(Orders o) {
		Msg msg = new Msg();
	
		//获取系统（服务器）当前时间
		Calendar now = Calendar.getInstance();
		Date date = now.getTime();
		o.setSystemTime(date);
		o.setStatus(1);
		this.addObject(o);
		msg.setStatus(1);
		return msg;
	}

	/**
	 * 修改订单状态
	 * 
	 * @param ordersID
	 * @param status
	 */
	public void updateStatus(int ordersID, int status) {
		Orders o = this.findByID(ordersID);
		o.setStatus(status);
		this.updat(o);
	}
	
	@Override
	protected String className() {
		return "Orders";
	}
	
}

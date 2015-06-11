package com.runying.dao;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.runying.po.Orders;
import com.runying.util.DaoUtil;
import com.runying.util.Msg;

public class OrdersDao extends DaoUtil{
	private String className = "Orders";
	
	public List<Orders> findAll(int pageNumber, int countPerPage) {
		return this.findAll(className, pageNumber, countPerPage);
	}
	
	public Orders findByID(int id) {
		return this.findByID(Orders.class, id);
	}
	
	public List<Orders> findByStatus(int status, int pageNumber, int countPerPage) {
		return this.findByColumn(className, "status", status, pageNumber, countPerPage);
	}
	
	public Long sizeWithStatus(int status) {
		return (long) this.findByColumn(className, "status", status).size();
	}
	
	public Long size() {
		return this.size(className);
	}
	
	/**
	 * add an order, return false if the user has not the privilege. System adds current time as the time the recorded in system
	 *  
	 * @param o
	 * @return
	 */
	public Msg addOrders(Orders o) {
		Msg msg = new Msg();
		int privilege = o.getOperator().getPrivilege();
		//the use has not privilege
		if((privilege & 1) == 0) {
			msg.setStatus(0);
			msg.setDescription("权限不足");
			return msg;
		}
		//获取系统（服务器）当前时间
		Calendar now = Calendar.getInstance();
		Date date = now.getTime();
		o.setSystemTime(date);
		o.setStatus(1);
		this.addObject(o);
		msg.setStatus(1);
		return msg;
	}
	
}

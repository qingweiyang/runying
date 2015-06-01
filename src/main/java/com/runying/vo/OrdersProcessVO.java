package com.runying.vo;

import java.util.List;

import com.runying.po.Orders;
import com.runying.po.Process;

/**
 * 前端提交工序时的数据VO
 * 
 * @author I319213
 *
 */
public class OrdersProcessVO {
	
	private Orders orders;
	
	private List<Process> ps;

	public Orders getOrders() {
		return orders;
	}

	public void setOrders(Orders orders) {
		this.orders = orders;
	}

	public List<Process> getPs() {
		return ps;
	}

	public void setPs(List<Process> ps) {
		this.ps = ps;
	}
}

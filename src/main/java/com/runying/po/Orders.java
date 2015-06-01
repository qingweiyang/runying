package com.runying.po;

import java.util.Date;

/**
 * 订货单po
 * @author yqw
 *
 */
public class Orders {
	private int id;
	
	//订单号
	//private int orderNum;
	
	//单据编号
	private String number;
	
	//数量
	private int count;
	
	//备注
	private String remarks;
	
	//下达日期
	private Date releaseTime;
	
	//交货日期
	private Date deliveryTime;
	
	//订单录入系统日期
	private Date systemTime;
	
	private Product product;
	
	//订单录入操作员
	private User operator;
	
	//订单状态 0:已删除，1:未进行生产计划，2:已进行生产计划，3
	private int status;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

//	public int getOrderNum() {
//		return orderNum;
//	}
//
//	public void setOrderNum(int orderNum) {
//		this.orderNum = orderNum;
//	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Date getReleaseTime() {
		return releaseTime;
	}

	public void setReleaseTime(Date releaseTime) {
		this.releaseTime = releaseTime;
	}

	public Date getDeliveryTime() {
		return deliveryTime;
	}

	public void setDeliveryTime(Date deliveryTime) {
		this.deliveryTime = deliveryTime;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public User getOperator() {
		return operator;
	}

	public void setOperator(User operator) {
		this.operator = operator;
	}

	public Date getSystemTime() {
		return systemTime;
	}

	public void setSystemTime(Date systemTime) {
		this.systemTime = systemTime;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	
}

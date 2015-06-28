package com.runying.vo;

import java.util.Date;

/**
 * 一张出货单的详情信息vo
 * 
 * @author yqw
 *
 */
public class OrdersBillDetailVO {
	
	/**
	 * 发货产品名称
	 */
	private String productName;
	
	/**
	 * 发货产品规格
	 */
	private String size;
	
	/**
	 * 发货产品数量
	 */
	private int count;
	
	/**
	 * 发货时间
	 */
	private Date billTime;

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public Date getBillTime() {
		return billTime;
	}

	public void setBillTime(Date billTime) {
		this.billTime = billTime;
	}

}

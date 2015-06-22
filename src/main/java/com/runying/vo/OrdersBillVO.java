package com.runying.vo;

import java.util.Date;

/**
 * 出货单 列表vo
 * 
 * @author yqw
 *
 */
public class OrdersBillVO {
	/**
	 * 出货单id
	 */
	private int id;
	
	/**
	 * 操作员名称
	 */
	private String operator;
	
	/**
	 * 出货时间
	 */
	private Date systemTime;

	public int getid() {
		return id;
	}

	public void setid(int id) {
		this.id = id;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public Date getSystemTime() {
		return systemTime;
	}

	public void setSystemTime(Date systemTime) {
		this.systemTime = systemTime;
	}
	
}

package com.runying.vo;

import java.util.Date;

/**
 * 仓库管理>仓库查看 的VO
 * 
 * @author I319213
 *
 */
public class InHouseLogVO {
	private int id;
	
	private int operatorID;
	
	private String operatorName;
	
	private int productID;
	
	private String productName;
	
	private int number;
	
	private Date date;
	
	public InHouseLogVO(int id, int operatorID, String operatorName, int productID, 
			String productName, int number, Date date) {
		this.id = id;
		this.operatorID = operatorID;
		this.operatorName = operatorName;
		this.productID = productID;
		this.productName = productName;
		this.number = number;
		this.date = date;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getOperatorID() {
		return operatorID;
	}

	public void setOperatorID(int operatorID) {
		this.operatorID = operatorID;
	}

	public String getOperatorName() {
		return operatorName;
	}

	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}

	public int getProductID() {
		return productID;
	}

	public void setProductID(int productID) {
		this.productID = productID;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
}

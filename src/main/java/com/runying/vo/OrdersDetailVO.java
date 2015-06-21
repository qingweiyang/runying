package com.runying.vo;

import java.util.Date;

/**
 * 订单详情vo
 * 对应 订单查看>订单详情
 * @author yqw
 *
 */
public class OrdersDetailVO {
	/**
	 * 订单id
	 */
	private int id;
	
	/**
	 * 订单合同号
	 */
	private String contractNumber;
	
	/**
	 * 产品id
	 */
	private int productID;
	
	/**
	 * 产品名称
	 */
	private String materialName;
	
	/**
	 * 产品计划数量
	 */
	private int count;
	
	/**
	 * 订单发布日期
	 */
	private Date releaseTime;
	
	/**
	 * 订单交货日期
	 */
	private Date deliveryTime;
	
	/**
	 * 产品已完成量
	 */
	private int hasFinished;
	
	/**
	 * 订单录入系统日期
	 */
	private Date systemTime;
	
	/**
	 * 订单录入员
	 */
	private String operator;
	
	/**
	 * 订单状态
	 */
	private int status;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getContractNumber() {
		return contractNumber;
	}

	public void setContractNumber(String contractNumber) {
		this.contractNumber = contractNumber;
	}

	public String getMaterialName() {
		return materialName;
	}

	public void setMaterialName(String materialName) {
		this.materialName = materialName;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public Date getDeliveryTime() {
		return deliveryTime;
	}

	public void setDeliveryTime(Date deliveryTime) {
		this.deliveryTime = deliveryTime;
	}

	public int getHasFinished() {
		return hasFinished;
	}

	public void setHasFinished(int hasFinished) {
		this.hasFinished = hasFinished;
	}

	public Date getSystemTime() {
		return systemTime;
	}

	public void setSystemTime(Date systemTime) {
		this.systemTime = systemTime;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public int getProductID() {
		return productID;
	}

	public void setProductID(int productID) {
		this.productID = productID;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Date getReleaseTime() {
		return releaseTime;
	}

	public void setReleaseTime(Date releaseTime) {
		this.releaseTime = releaseTime;
	}

}

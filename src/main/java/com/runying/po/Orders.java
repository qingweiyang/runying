package com.runying.po;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * 订货单po
 * @author yqw
 *
 */
@Entity
@Table(name="orders")
public class Orders {
	@Id
	@GeneratedValue
	@Column
	private int id;
	
	//合同号
	@Column
	private String contractNumber;
		
	//单据编号
	@Column
	private String number;
	
	//图号
	@Column
	private String pictureNumber;
	
	//数量
	@Column
	private int count;
	
	//备注
	@Column
	private String remarks;
	
	//下达日期
	@Column
	private Date releaseTime;
	
	//交货日期
	@Column
	private Date deliveryTime;
	
	//订单录入系统日期
	@Column
	private Date systemTime;
	
	@ManyToOne
	@JoinColumn(name = "productID")
	private Product product;
	
	//订单录入操作员
	@ManyToOne
	@JoinColumn(name = "operatorID")
	private User operator;
	
	//对应的工序
	@OneToMany(mappedBy = "orders")
	private Set<Process> processes;
	
	//订单状态 [0:已删除]，[1:未进行生产计划]，[2:已进行生产计划]，[3:已入库], [4：发货中（已经发货部分，但未发完）], [5: 已发货（订单全部完成）]
	@Column
	private int status;
	
	//订单已完成数量
	@Column
	private int hasFinished;

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

	public Set<Process> getProcesses() {
		return processes;
	}

	public void setProcesses(Set<Process> processes) {
		this.processes = processes;
	}

	public String getContractNumber() {
		return contractNumber;
	}

	public void setContractNumber(String contractNumber) {
		this.contractNumber = contractNumber;
	}

	public String getPictureNumber() {
		return pictureNumber;
	}

	public void setPictureNumber(String pictureNumber) {
		this.pictureNumber = pictureNumber;
	}

	public int getHasFinished() {
		return hasFinished;
	}

	public void setHasFinished(int hasFinished) {
		this.hasFinished = hasFinished;
	}

}

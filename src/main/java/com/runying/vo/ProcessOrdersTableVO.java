package com.runying.vo;

import java.util.Date;
import java.util.Set;

import com.runying.po.Process;

/**
 * 工序录入>订单选择 时展示订单详情的table vo
 * 
 * @author I319213
 *
 */
public class ProcessOrdersTableVO {
	//订单号
	private int id;
	
	//下达日期
	private Date releaseTime;

	//单据编号
	private String number;
	
	//物料名称
	private String materialName;
	
	//产品规格型号
	private String size1;

	private String size2;
	
	//数量
	private int count;
	
	//交货日期
	private Date deliveryTime;
	
	//库存量
	private int warehouseCount;

	//对应的工序
	private Set<Process> processes;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getReleaseTime() {
		return releaseTime;
	}

	public void setReleaseTime(Date releaseTime) {
		this.releaseTime = releaseTime;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getMaterialName() {
		return materialName;
	}

	public void setMaterialName(String materialName) {
		this.materialName = materialName;
	}

	public String getSize1() {
		return size1;
	}

	public void setSize1(String size1) {
		this.size1 = size1;
	}

	public String getSize2() {
		return size2;
	}

	public void setSize2(String size2) {
		this.size2 = size2;
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

	public int getWarehouseCount() {
		return warehouseCount;
	}

	public void setWarehouseCount(int warehouseCount) {
		this.warehouseCount = warehouseCount;
	}

	public Set<Process> getProcesses() {
		return processes;
	}

	public void setProcesses(Set<Process> processes) {
		this.processes = processes;
	}

}

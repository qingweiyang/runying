package com.runying.po;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name="process")
public class Process {
	@Id
	@GeneratedValue
	@Column
	private int id;
	
	//工序号
	@Column
	private int processNum;
	
	//工序名称
	@Column
	private String name;
	
	//数量
	@Column
	private int num;
	
	@Column
	private Date systemTime;
	
	//接受人
	@ManyToOne
	@JoinColumn(name = "receiverID")
	private User receiver;
	
	@ManyToOne
	@JoinColumn(name = "ordersID")
	private Orders orders;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getProcessNum() {
		return processNum;
	}

	public void setProcessNum(int processNum) {
		this.processNum = processNum;
	}

	public Date getSystemTime() {
		return systemTime;
	}

	public void setSystemTime(Date systemTime) {
		this.systemTime = systemTime;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public User getReceiver() {
		return receiver;
	}

	public void setReceiver(User receiver) {
		this.receiver = receiver;
	}

	public Orders getOrders() {
		return orders;
	}

	@JsonBackReference
	public void setOrders(Orders orders) {
		this.orders = orders;
	}
}

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
 * 出货单与订单的连接表
 * 
 * @author yqw
 *
 */
@Entity
@Table(name="salesBill")
public class SalesBill {
	@Id
	@GeneratedValue
	@Column
	private int id;
	
	@OneToMany(mappedBy = "salesBill")
	private Set<SalesBill_Orders> salesBill_Orders;
	
	//操作员
	@ManyToOne
	@JoinColumn(name = "operatorID")
	private User operator;
	
	//录入系统日期
	@Column
	private Date systemTime;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public Set<SalesBill_Orders> getSalesBill_Orders() {
		return salesBill_Orders;
	}

	public void setSalesBill_Orders(Set<SalesBill_Orders> salesBill_Orders) {
		this.salesBill_Orders = salesBill_Orders;
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
	
}

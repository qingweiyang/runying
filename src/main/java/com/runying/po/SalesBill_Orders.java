package com.runying.po;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

/**
 * salesBill 与 orders的连接表
 * @author yqw
 *
 */
@Entity
@Table(name="salesBill_orders")
public class SalesBill_Orders {
	@Id
	@GeneratedValue
	@Column
	private int id;
	
	@ManyToOne
	@JoinColumn(name = "saleBillID")
	private SalesBill salesBill;

	@ManyToOne
	@JoinColumn(name = "ordersID")
	private Orders orders;

	/**
	 *  发货量
	 */
	@Column
	private int count;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public SalesBill getSalesBill() {
		return salesBill;
	}

	@JsonBackReference
	public void setSalesBill(SalesBill salesBill) {
		this.salesBill = salesBill;
	}

	public Orders getOrders() {
		return orders;
	}

	public void setOrders(Orders orders) {
		this.orders = orders;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
	
}

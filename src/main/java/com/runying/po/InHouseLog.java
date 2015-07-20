package com.runying.po;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * 入库日志
 * 
 * @author I319213
 *
 */
@Entity
@Table(name="inhouselog")
public class InHouseLog {
	@Id
	@GeneratedValue
	@Column
	private int id;
	
	/**
	 * 入库产品
	 */
	@OneToOne
	@JoinColumn(name = "productID")
	private Product product;
	
	/**
	 * 入库人员
	 */
	@OneToOne
	@JoinColumn(name = "userID")
	private User user;
	
	/**
	 * 入库数量
	 */
	@Column
	private int number;
	
	/**
	 * 入库日期
	 */
	@Column
	private Date inHouseDate;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public Date getInHouseDate() {
		return inHouseDate;
	}

	public void setInHouseDate(Date inHouseDate) {
		this.inHouseDate = inHouseDate;
	}
}

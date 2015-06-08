package com.runying.po;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 仓库po
 * 
 * @author I319213
 *
 */
@Entity
@Table(name="warehouse")
public class Warehouse {
	@Id
	@GeneratedValue
	@Column
	private int id;
	
	@ManyToOne
	@JoinColumn(name = "productID")
	private Product product;
	
	//库存量
	@Column
	private int number;

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

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}
	
}

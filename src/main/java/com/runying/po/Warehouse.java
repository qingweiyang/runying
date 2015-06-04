package com.runying.po;

/**
 * 仓库po
 * 
 * @author I319213
 *
 */
public class Warehouse {
	private int id;
	
	private Product product;
	
	//库存量
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

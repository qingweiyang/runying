package com.runying.dao;

import java.util.List;

import com.runying.po.Product;
import com.runying.util.DaoUtil;

public class ProductDao extends DaoUtil{
	private String className = "Product";
	
	public List<Product> findAll() {
		return this.findAll(className);
	}
	
}

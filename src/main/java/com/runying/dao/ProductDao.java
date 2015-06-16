package com.runying.dao;

import com.runying.util.DaoUtil;

public class ProductDao extends DaoUtil{

	@Override
	protected String className() {
		return "Product";
	}
	
	public Long sizeWithStatus(int status) {
		return (long) this.findByColumn("status", status).size();
	}
}

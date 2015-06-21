package com.runying.dao;

import java.util.List;

import com.runying.po.Product;
import com.runying.po.Warehouse;
import com.runying.util.DaoUtil;

public class WarehouseDao extends DaoUtil{
	
	public Warehouse findByProduct(Product p) {
		List<Warehouse> ps = this.findByColumn("product", p);
		if(ps == null || ps.size() == 0) {
			return null;
		} else {
			return ps.get(0);
		}
	}

	@Override
	protected String className() {
		return "Warehouse";
	}
}

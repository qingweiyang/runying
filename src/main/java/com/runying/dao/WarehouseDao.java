package com.runying.dao;

import java.util.List;

import com.runying.po.Product;
import com.runying.po.User;
import com.runying.po.Warehouse;
import com.runying.util.DaoUtil;
import com.runying.util.Msg;

public class WarehouseDao extends DaoUtil{
	
	private final String className = "Warehouse";
	
	/**
	 * 入库
	 * @return
	 */
	public Msg inWarehouse(User operator, Product product, int num) {
		Msg msg = new Msg();
		
		//检查操作员是否有权限
		if(1 != (operator.getPrivilege() >> 2 & 1)) {
			msg.setStatus(0);
			msg.setDescription("权限不足");
			return msg;
		}
		
		//入库数量是否合法
		if(num <= 0) {
			msg.setStatus(0);
			msg.setDescription("入库数量错误，必须大于0");
			return msg;
		}
		
		//入库操作
		//先检查仓库中是否已有该产品
		List<Warehouse> wh = this.findByColumn(className, "product", product);
		if(wh == null || wh.size() == 0) {
			//没有该产品
			Warehouse w = new Warehouse();
			w.setNumber(num);
			w.setProduct(product);
			this.addObject(w);
		} else {
			Warehouse w = wh.get(0);
			w.setNumber(w.getNumber()+num);
			this.updat(w);
		}
		
		msg.setStatus(1);
		return msg;
	}
}

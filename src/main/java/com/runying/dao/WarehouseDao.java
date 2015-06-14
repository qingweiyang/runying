package com.runying.dao;

import java.util.List;

import com.runying.po.Product;
import com.runying.po.User;
import com.runying.po.Warehouse;
import com.runying.util.DaoUtil;
import com.runying.util.Msg;

public class WarehouseDao extends DaoUtil{
	
	public Warehouse findByProduct(Product p) {
		List<Warehouse> ps = this.findByColumn("product", p);
		if(ps == null || ps.size() == 0) {
			return null;
		} else {
			return ps.get(0);
		}
	}
	
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
		List<Warehouse> wh = this.findByColumn("product", product);
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

	/**
	 * 出库
	 * @return
	 */
	public Msg outWarehouse(User operator, Product product, int num) {
		Msg msg = new Msg();
		
		//检查操作员是否有权限
		if(1 != (operator.getPrivilege() >> 2 & 1)) {
			msg.setStatus(0);
			msg.setDescription("权限不足");
			return msg;
		}
		
		//出库数量是否合法
		if(num <= 0) {
			msg.setStatus(0);
			msg.setDescription("入库数量错误，必须大于0");
			return msg;
		}
		
		//出库操作
		//先检查仓库中是否已有该产品
		List<Warehouse> wh = this.findByColumn("product", product);
		if(wh == null || wh.size() == 0) {
			//没有该产品
			msg.setStatus(0);
			msg.setDescription("仓库没有该商品");
			return msg;
		} else {
			Warehouse w = wh.get(0);
			//库存量 是否足够
			int wNum = w.getNumber();
			if(wNum < num) {
				msg.setStatus(0);
				msg.setDescription("商品 "+w.getProduct().getMaterialName()+" 库存量不足");
				return msg;
			} else {
				w.setNumber(w.getNumber()-num);
				this.updat(w);
			}
			
		}
		
		msg.setStatus(1);
		return msg;
	}

	@Override
	protected String className() {
		return "Warehouse";
	}
}

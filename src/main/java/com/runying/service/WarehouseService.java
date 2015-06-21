package com.runying.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.runying.dao.OrdersDao;
import com.runying.dao.ProductDao;
import com.runying.dao.UserDao;
import com.runying.dao.WarehouseDao;
import com.runying.po.Orders;
import com.runying.po.Product;
import com.runying.po.User;
import com.runying.po.Warehouse;
import com.runying.util.Constants;
import com.runying.util.Msg;
import com.runying.util.Validate;
import com.runying.vo.ProcessOrdersTableVO;
import com.runying.vo.TableVO;

@Service
public class WarehouseService {
	@Autowired
	private WarehouseDao warehouseDaoProxy;
	
	@Autowired
	private ProductDao productDaoProxy;
	
	@Autowired
	private UserDao userDaoProxy;
	
	@Autowired
	private OrdersDao ordersDaoProxy;
	
	public Msg outWarehouseBatch(List<ProcessOrdersTableVO> rows, User operator) {
		//检查操作员是否拥有 入库出库 权限
		User oDB = userDaoProxy.findByUsername(operator.getUsername());
		Msg msg = Validate.checkUserPrivilege(oDB, Constants.WAREHOUSE_IN_OUT); 
		if(msg.getStatus() == 0) {
			return msg;
		}
		
		//批量出库
		for(ProcessOrdersTableVO ptvo : rows) {
			msg = this.outWarehouse(operator, (Product) productDaoProxy.findByID(ptvo.getProductID()), ptvo.getCount());
			if(msg.getStatus() == 0) {
				return msg;
			} else {
				//出库完成，修改订单状态,
				//订单还未全部完成
				//先找到订单的数据库中保存的数量（从前端传来值非数据库值，是用户输入的当前入库数量）
				Orders ordersDB = ordersDaoProxy.findByID(ptvo.getId());
				int planCount = ordersDB.getCount();
				int hasFinished = ordersDB.getHasFinished() + ptvo.getCount();
				if(planCount > hasFinished) {
					//此时订单未全部完成
					ordersDB.setStatus(4);
				} else {
					//此时订单全部完成
					ordersDB.setStatus(5);
				}
				ordersDB.setHasFinished(hasFinished);
				ordersDaoProxy.updat(ordersDB);
			}
		}
		
		return msg;
	}
	
	/**
	 * 出库
	 * @return
	 */
	public Msg outWarehouse(User operator, Product product, int num) {
		Msg msg = new Msg();
		
		//检查操作员是否有权限
		User oDB = userDaoProxy.findByUsername(operator.getUsername());
		if(1 != (oDB.getPrivilege() >> 2 & 1)) {
			msg.setStatus(0);
			msg.setDescription("权限不足");
			return msg;
		}
		
		//出库数量是否合法
		if(num <= 0) {
			msg.setStatus(0);
			msg.setDescription("出库数量错误，必须大于0");
			return msg;
		}
		
		//出库操作
		//先检查仓库中是否已有该产品
		List<Warehouse> wh = warehouseDaoProxy.findByColumn("product", product);
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
				warehouseDaoProxy.updat(w);
			}
			
		}
		
		msg.setStatus(1);
		return msg;
	}
	
	/**
	 * 入库
	 * @return
	 */
	public Msg inWarehouse(User operator, Product product, int num) {
		Msg msg = new Msg();
		
		//检查操作员是否有权限
		User oDB = userDaoProxy.findByUsername(operator.getUsername());
		if(1 != (oDB.getPrivilege() >> 2 & 1)) {
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
		List<Warehouse> wh = warehouseDaoProxy.findByColumn("product", product);
		if(wh == null || wh.size() == 0) {
			//没有该产品
			Warehouse w = new Warehouse();
			w.setNumber(num);
			w.setProduct(product);
			warehouseDaoProxy.addObject(w);
		} else {
			Warehouse w = wh.get(0);
			w.setNumber(w.getNumber()+num);
			warehouseDaoProxy.updat(w);
		}
		
		msg.setStatus(1);
		return msg;
	}
	
	public Msg inWarehouseBatch(List<ProcessOrdersTableVO> rows, User operator) {
		//检查操作员是否拥有 入库出库 权限
		User oDB = userDaoProxy.findByUsername(operator.getUsername());
		Msg msg = Validate.checkUserPrivilege(oDB, Constants.WAREHOUSE_IN_OUT); 
		if(msg.getStatus() == 0) {
			return msg;
		}
		
		//批量入库
		for(ProcessOrdersTableVO ptvo : rows) {
			msg = this.inWarehouse(operator, (Product) productDaoProxy.findByID(ptvo.getProductID()), ptvo.getCount());
			if(msg.getStatus() == 0) {
				return msg;
			} else {
				//出库完成，修改订单状态, 已入库
				ordersDaoProxy.updateStatus(ptvo.getId(), 3);
			}
		}
		
		return msg;
	}
	
	/**
	 * 获取未被删除的产品
	 * 
	 * @param pageNumber
	 * @param countPerPage
	 * @return
	 */
	public TableVO<Product> getUndeletedProduct(int pageNumber, int countPerPage) {
		List<Product> us = productDaoProxy.findByColumn("status", 1, pageNumber, countPerPage);
		TableVO<Product> tvo = new TableVO<Product>();
		tvo.setRows(us);
		tvo.setCurrentPage(pageNumber);
		tvo.setCountPerPage(countPerPage);
		tvo.setPages((productDaoProxy.sizeWithStatus(1)-1) / countPerPage + 1);
		return tvo;
	}
	
	/**
	 * 获取所有未被删除的产品
	 * 
	 * @param pageNumber
	 * @param countPerPage
	 * @return
	 */
	public List<Product> getAllUndeletedProduct() {
		List<Product> us = productDaoProxy.findByColumn("status", 1, 1, productDaoProxy.size().intValue());
		return us;
	}
	
	/**
	 * 删除产品
	 * 
	 * @param u
	 * @param operator
	 * @return
	 */
	public Msg deleteProduct(Product p, User operator) {
		Msg msg = new Msg();
		
		User resDB = userDaoProxy.findByUsername(operator.getUsername());
		//检查操作员是否拥有 删除用户权限 的权限
		if(1 != (resDB.getPrivilege() >> 3 & 1)) {
			msg.setStatus(0);
			msg.setDescription("权限不足");
			return msg;
		}
		
		Product pDB = productDaoProxy.findByID(p.getId());
		//检查该产品是否存在
		if(pDB == null) {
			msg.setStatus(0);
			msg.setDescription("产品 " + p.getMaterialName() + " 不存在");
			return msg;
		}
		
		pDB.setStatus(0);
		userDaoProxy.updat(pDB);
		
		msg.setStatus(1);
		return msg;
	}
	
	/**
	 * 添加产品
	 * 
	 * @param u
	 * @param operator
	 * @return
	 */
	public Msg addProduct(Product p, User operator) {
		Msg msg = new Msg();
		
		User resDB = userDaoProxy.findByUsername(operator.getUsername());
		//检查操作员是否拥有 添加权限 的权限
		if(1 != (resDB.getPrivilege() >> 3 & 1)) {
			msg.setStatus(0);
			msg.setDescription("权限不足");
			return msg;
		}
		
		p.setStatus(1);
		productDaoProxy.addObject(p);
		msg.setStatus(1);
		return msg;
	}
	
	/**
	 * 修改产品
	 * 
	 * @param u
	 * @param operator
	 * @return
	 */
	public Msg editProduct(Product p, User operator) {
		Msg msg = new Msg();
		
		User resDB = userDaoProxy.findByUsername(operator.getUsername());
		//检查操作员是否拥有 编辑用户权限 的权限
		if(1 != (resDB.getPrivilege() >> 3 & 1)) {
			msg.setStatus(0);
			msg.setDescription("权限不足");
			return msg;
		}
		
		Product pDB = productDaoProxy.findByID(p.getId());
		//检查该产品是否存在
		if(pDB == null) {
			msg.setStatus(0);
			msg.setDescription("产品 " + p.getMaterialName() + " 不存在");
			return msg;
		}
		
		pDB.setMaterialName(p.getMaterialName());
		pDB.setSize1(p.getSize1());
		pDB.setSize2(p.getSize2());
		pDB.setMaterialCode(p.getMaterialCode());
		userDaoProxy.updat(pDB);
		
		msg.setStatus(1);
		return msg;
	}
}

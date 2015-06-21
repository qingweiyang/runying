package com.runying.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.runying.dao.ProductDao;
import com.runying.dao.UserDao;
import com.runying.po.Product;
import com.runying.po.User;
import com.runying.util.Msg;
import com.runying.vo.TableVO;

@Service
public class ProductService {
	@Autowired
	private ProductDao productDaoProxy;
	
	@Autowired
	private UserDao userDaoProxy;
	
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
		pDB.setMaterial(p.getMaterial());
		userDaoProxy.updat(pDB);
		
		msg.setStatus(1);
		return msg;
	}
}

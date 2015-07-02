package com.runying.service;

import java.util.HashMap;
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
	 * 根据产品ID搜索产品
	 * 
	 * @param productID
	 * @return
	 */
	public Product findByID(int productID) {
		return productDaoProxy.findByID(productID);
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
	public Msg deleteProduct(int pID, User operator) {
		Msg msg = new Msg();
		
		User resDB = userDaoProxy.findByUsername(operator.getUsername());
		//检查操作员是否拥有 删除用户权限 的权限
		if(1 != (resDB.getPrivilege() >> 3 & 1)) {
			msg.setStatus(0);
			msg.setDescription("权限不足");
			return msg;
		}
		
		Product pDB = productDaoProxy.findByID(pID);
		//检查该产品是否存在
		if(pDB == null) {
			msg.setStatus(0);
			msg.setDescription("产品ID " + pID + " 不存在");
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
		
		//检查是否该产品已存在
		if(this.isExist(p)) {
			msg.setStatus(0);
			msg.setDescription("该产品已存在");
			return msg;
		}
		
		p.setStatus(1);
		productDaoProxy.addObject(p);
		msg.setStatus(1);
		return msg;
	}
	
	/**
	 *  检验产品p是否已存在，注意：状态为0的产品（该产品已被删除）被认为不存在
	 *  
	 * @param p
	 * @return
	 */
	public boolean isExist(Product p) {
		HashMap<String, Object> cols = new HashMap<String, Object>();
		if(p.getMaterialName() == null) {
			cols.put("materialName", "");
		} else {
			cols.put("materialName", p.getMaterialName());
		}
		if(p.getMaterialCode() == null) {
			cols.put("materialCode", "");
		} else {
			cols.put("materialCode", p.getMaterialCode());
		}
		if(p.getSize1() == null) {
			cols.put("size1", "");
		} else {
			cols.put("size1", p.getSize1());
		}
		if(p.getSize2() == null) {
			cols.put("size2", "");
		} else {
			cols.put("size2", p.getSize2());
		}
		if(p.getMaterial() == null) {
			cols.put("material", "");
		} else {
			cols.put("material", p.getMaterial());
		}
//		System.out.println("map materialname = "+cols.get("materialName"));
		List<Product> pDB = productDaoProxy.findByColumns(cols);
		if(pDB == null || pDB.size() == 0) {
			return false;
		} else {
			for(Product sp : pDB) {
				if(sp.getStatus() == 0) {
					//该产品已被删除
					return false;
				}
			}
			return true;
		}
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

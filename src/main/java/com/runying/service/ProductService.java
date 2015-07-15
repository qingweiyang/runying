package com.runying.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.runying.dao.ProductDao;
import com.runying.dao.UserDao;
import com.runying.dao.WarehouseDao;
import com.runying.po.Product;
import com.runying.po.User;
import com.runying.po.Warehouse;
import com.runying.util.Msg;
import com.runying.util.Pinyin;
import com.runying.vo.TableVO;

@Service
public class ProductService {
	@Autowired
	private ProductDao productDaoProxy;
	
	@Autowired
	private UserDao userDaoProxy;
	
	@Autowired
	private WarehouseDao warehouseDaoProxy;
	
	/**
	 * 根据产品ID搜索产品
	 * 
	 * @param productID
	 * @return
	 */
	public Product findByID(int productID) {
		return productDaoProxy.findByID(productID);
	}
	
	public List<Product> findAll(int pageNumber, int countPerPage) {
		return productDaoProxy.findAll(pageNumber, countPerPage);
	}
	
//	/**
//	 * 获取未被删除的产品
//	 * 
//	 * @param pageNumber
//	 * @param countPerPage
//	 * @return
//	 */
//	public TableVO<Product> getUndeletedProduct(int pageNumber, int countPerPage) {
//		List<Product> us = productDaoProxy.findByColumn("status", 1, pageNumber, countPerPage);
//		TableVO<Product> tvo = new TableVO<Product>();
//		tvo.setRows(us);
//		tvo.setCurrentPage(pageNumber);
//		tvo.setCountPerPage(countPerPage);
//		tvo.setPages((productDaoProxy.sizeWithStatus(1)-1) / countPerPage + 1);
//		return tvo;
//	}
	
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
		//完成产品名称 映射 到 拼音首字母缩写
		p.setPinyin(Pinyin.toPinyinString(p.getMaterialName()));
		productDaoProxy.addObject(p);
		
		//更新仓库，并将该产品数量置为 0
		Warehouse w = new Warehouse();
		w.setNumber(0);
		w.setProduct(p);
		//检查仓库中是否已存在该产品，若存在，不做任何处理
		Warehouse wDB = warehouseDaoProxy.findByProduct(p);
		if(wDB == null) {
			warehouseDaoProxy.addObject(w);
		}
			
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
			//可能存在与 已被删除的产品 相同情况
			//系统允许新增的产品与 状态为[被删除]的产品 重复；（已删除的数据用户看不到，只是系统留作备份之用）
			boolean isTrue = true;
			for(Product sp : pDB) {
				if(sp.getStatus() == 0) {
					//该产品已被删除
					isTrue = false;
				}else if(sp.getStatus() != 0) {
					isTrue = true;
				}
			}
			return isTrue;
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
		pDB.setWeight(p.getWeight());
		userDaoProxy.updat(pDB);
		
		msg.setStatus(1);
		return msg;
	}
	
	/**
	 * 搜索所有状态为 未被删除 的产品
	 * 
	 * @param colsLike
	 * @param pageNumber
	 * @param countPerPage
	 * @return
	 */
	public TableVO<Product> findProducts(int pageNumber, int countPerPage) {
		Map<String, Object> colsEqual = new HashMap<String, Object>();
		colsEqual.put("status", 1);
		
		return productDaoProxy.findByConditions(null, "and", colsEqual, "or", "materialName", pageNumber, countPerPage);
	}
	
	/**
	 * 模糊搜索，产品状态未删除
	 * 
	 * @param colsLike
	 * @param pageNumber
	 * @param countPerPage
	 * @return
	 */
	public TableVO<Product> findProducts(Map<String, Object> colsLike, int pageNumber, int countPerPage) {
		Map<String, Object> colsEqual = new HashMap<String, Object>();
		colsEqual.put("status", 1);
		
		return productDaoProxy.findByConditions(colsLike, "or", colsEqual, "or", "pinyin", pageNumber, countPerPage);
	}
	
	/**
	 * 返回product的总数量
	 * 
	 * @return
	 */
	public int size() {
		return productDaoProxy.size().intValue();
	}
}

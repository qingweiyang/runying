package com.runying.dao.test;

import org.junit.Before;
import org.junit.Test;

import com.runying.dao.WarehouseDao;
import com.runying.po.Product;
import com.runying.po.User;
import com.runying.util.DaoUtil;
import com.runying.util.Msg;

public class WarehouseDaoTest {
	private WarehouseDao warehouseDao;
	
	@Before
	public void before() {
		warehouseDao = new WarehouseDao();
	}
	
	//@Test
	public void inWarehouseTest() {
		User operator = new DaoUtil().findByID(User.class, 1);
		Product product = new DaoUtil().findByID(Product.class, 1);
		Msg msg = warehouseDao.inWarehouse(operator, product, 2);
		System.out.println(msg.getDescription());
	}

	@Test
	public void outWarehouseTest() {
		User operator = new DaoUtil().findByID(User.class, 1);
		Product product = new DaoUtil().findByID(Product.class, 1);
		Msg msg = warehouseDao.outWarehouse(operator, product, 2);
		System.out.println(msg.getDescription());
	}
}

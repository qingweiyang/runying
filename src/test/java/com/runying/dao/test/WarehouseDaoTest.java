package com.runying.dao.test;

import org.junit.Before;
import org.junit.Test;

import com.runying.dao.WarehouseDao;
import com.runying.po.Product;
import com.runying.po.User;
import com.runying.util.DaoUtil;

public class WarehouseDaoTest {
	private WarehouseDao warehouseDao;
	
	@Before
	public void before() {
		warehouseDao = new WarehouseDao();
	}
	
	@Test
	public void inWarehouseTest() {
		User operator = new DaoUtil().findByID(User.class, 1);
		Product product = new DaoUtil().findByID(Product.class, 1);
		warehouseDao.inWarehouse(operator, product, 2);
	}

}

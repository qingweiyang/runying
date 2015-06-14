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
	
}

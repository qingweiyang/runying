package com.runying.util.test;


import org.junit.Before;
import org.junit.Test;

import com.runying.dao.OrdersDao;
import com.runying.po.User;
import com.runying.util.DaoUtil;

public class DaoUtilTest {
	private DaoUtil daoUtil;
	
	@Before
	public void before() {
		daoUtil = new OrdersDao();
	}
	
	//@Test
	public void addObjectTest() {
		User u = new User();
		u.setUsername("yqw");
		for(int i = 0 ; i < 3 ; i++) {
			daoUtil.addObject(u);
		}
	}
	
}

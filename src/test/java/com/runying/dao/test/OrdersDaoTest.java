package com.runying.dao.test;

import org.junit.Before;
import org.junit.Test;

import com.runying.dao.OrdersDao;
import com.runying.po.Orders;
import com.runying.po.Product;
import com.runying.po.User;
import com.runying.util.Msg;

public class OrdersDaoTest {
	private OrdersDao ordesDao;
	
	@Before
	public void before() {
		ordesDao = new OrdersDao();
	}
	
	@Test
	public void addOrdersTest() {
		User u = ordesDao.getObject(User.class, 2);
		Product p = ordesDao.getObject(Product.class, 1);
		Orders o = new Orders();
		o.setOperator(u);
		o.setProduct(p);
		Msg msg = ordesDao.addOrders(o);
		System.out.println(msg.getStatus()+"  "+msg.getDescription());
	}
}

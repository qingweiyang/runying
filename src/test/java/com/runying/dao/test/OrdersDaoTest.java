package com.runying.dao.test;

import java.util.List;

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
	
	//@Test
	public void findByIDTest() {
		Orders os = ordesDao.findByID(4);
		System.out.println(os.getProcesses().size());
	}
	
	@Test
	public void findByStatusTest() {
		List<Orders> os = ordesDao.findByStatus(1);
		System.out.println(os.size());
	}
	
	//@Test
	public void addOrdersTest() {
		User u = ordesDao.findByID(User.class, 1);
		Product p = ordesDao.findByID(Product.class, 1);
		Orders o = new Orders();
		o.setOperator(u);
		o.setProduct(p);
		Msg msg = ordesDao.addOrders(o);
		ordesDao.addOrders(o);
		System.out.println(msg.getStatus()+"  "+msg.getDescription());
	}
	
	//@Test
	public void findAllTest() {
		List<Orders> os = ordesDao.findAll();
		for(int i = 0 ; i < os.size() ; i++) {
			System.out.println(os.get(i).getId());
		}
	}
}

//package com.runying.dao.test;
//
//import java.util.List;
//
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
//import com.runying.dao.OrdersDao;
//import com.runying.po.Orders;
//import com.runying.po.Product;
//import com.runying.po.User;
//import com.runying.util.Msg;
//
////@RunWith(SpringJUnit4ClassRunner.class)
////@ContextConfiguration(locations = {"classpath:conf/spring-mvc.xml", "classpath:conf/spring-hibernate.xml", "classpath:conf/spring-beans.xml"})
//public class OrdersDaoTest {
//	@Autowired
//	private OrdersDao ordesDaoProxy;
//	
//	@Before
//	public void before() {
//	}
//	
//	//@Test
//	public void findByIDTest() {
//		Orders os = ordesDaoProxy.findByID(4);
//		System.out.println(os.getProcesses().size());
//	}
//	
//	//@Test
//	public void findByStatusTest() {
//	}
//	
//	//@Test
//	public void addOrdersTest() {
//		User u = ordesDaoProxy.findByID(User.class, 1);
//		Product p = ordesDaoProxy.findByID(Product.class, 1);
//		Orders o = new Orders();
//		o.setOperator(u);
//		o.setProduct(p);
//		Msg msg = ordesDaoProxy.addOrders(o);
//		ordesDaoProxy.addOrders(o);
//		System.out.println(msg.getStatus()+"  "+msg.getDescription());
//	}
//	
//	//@Test
//	public void findAllTest() {
//		List<Orders> os = ordesDaoProxy.findAll(1, 5);
//		for(int i = 0 ; i < os.size() ; i++) {
//			System.out.println(os.get(i).getId());
//		}
//	}
//}

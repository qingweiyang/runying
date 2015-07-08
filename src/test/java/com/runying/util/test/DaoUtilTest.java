//package com.runying.util.test;
//
//import java.util.HashMap;
//import java.util.Map;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
//import com.runying.dao.ProductDao;
//import com.runying.po.Product;
//import com.runying.vo.TableVO;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = {"classpath:conf/spring-mvc.xml", "classpath:conf/spring-hibernate.xml", "classpath:conf/spring-beans.xml"})
//public class DaoUtilTest {
//	@Autowired
//	private ProductDao productDaoProxy;
//	
//	@Test
//	public void findDimByColumnsWithConnectorTest() {
//		Map<String, Object> cols = null;
////		cols.put("materialName", "螺母");
//		
//		Map<String, Object> cols1 = new HashMap<String, Object>();
////		cols1.put("status", 1);
//		
//		TableVO<Product> us = productDaoProxy.findByConditions(cols, "and", cols1, "and", "materialName", 1, 100);
//		System.out.println(us.getRows().get(0).getMaterialName());
//	
//	}
//	
//}

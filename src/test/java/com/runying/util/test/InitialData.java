//package com.runying.util.test;
//
//import java.util.List;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
//import com.runying.dao.WarehouseDao;
//import com.runying.po.Product;
//import com.runying.po.Warehouse;
//import com.runying.service.ProductService;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = {"classpath:conf/spring-mvc.xml", "classpath:conf/spring-hibernate.xml", "classpath:conf/spring-beans.xml"})
//public class InitialData {
//	@Autowired
//	private WarehouseDao warehouseDaoProxy;
//	
//	@Autowired
//	private ProductService productService;
//
//	@Test
//	public void initialWarehouse() {
//		List<Product> ps = productService.getAllUndeletedProduct();
//		for(Product p : ps) {
//			Warehouse w = new Warehouse();
//			w.setProduct(p);
//			w.setNumber(0);
//			warehouseDaoProxy.addObject(w);
//		}
//	}
//	
//}

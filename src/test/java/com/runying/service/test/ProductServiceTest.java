package com.runying.service.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.runying.po.Product;
import com.runying.service.ProductService;

//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = {"classpath:conf/spring-mvc.xml", "classpath:conf/spring-hibernate.xml", "classpath:conf/spring-beans.xml"})
public class ProductServiceTest {
	@Autowired
	private ProductService productService;
	
	//@Test
	public void testIsExist() {
		Product p = new Product();
		p.setMaterialName("1232");
		
		System.out.println(productService.isExist(p));
	}
}

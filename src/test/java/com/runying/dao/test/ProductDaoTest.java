package com.runying.dao.test;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.runying.dao.ProductDao;
import com.runying.po.Product;

public class ProductDaoTest {
	
	private ProductDao productDao;
	
	@Before
	public void before(){      
		productDao = new ProductDao();
	}
	
	@Test
	public void acceptTask() {
		List<Product> ps = productDao.findAll();
		for(int i = 0 ; i < ps.size() ; i++) {
			System.out.println(ps.get(i).getMaterialName());
		}
	}

}

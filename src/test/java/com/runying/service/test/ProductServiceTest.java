//package com.runying.service.test;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
//import com.runying.po.Product;
//import com.runying.service.ProductService;
//import com.runying.vo.TableVO;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = {"classpath:conf/spring-mvc.xml", "classpath:conf/spring-hibernate.xml", "classpath:conf/spring-beans.xml"})
//public class ProductServiceTest {
//	@Autowired
//	private ProductService productService;
//	
//	//@Test
//	public void testIsExist() {
//		Product p = new Product();
//		p.setMaterialName("1");
//		
//		System.out.println(productService.isExist(p));
//	}
//	
//	@Test
//	public void testSearchProduct() {
//		Map<String, Object> conds = new HashMap<String, Object>();
//		String search = "顶丝";
//		//支持根据 产品名，规格型号1，规格型号2，物料长代码，材质，产品名拼音首字母缩写 模糊搜索
//		conds.put("materialName", search);
//		conds.put("size1", search);
//		conds.put("size2", search);
//		conds.put("materialCode", search);
//		conds.put("material", search);
//		conds.put("pinyin", search);
//		
//		List<Product> ps = productService.findProducts(conds, 1, 100).getRows();
//		for(Product p : ps) {
//			System.out.println(p.getWarehouse().getNumber());
//		}
//	}
//}

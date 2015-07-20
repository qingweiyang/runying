package com.runying.dao.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.runying.dao.InHouseLogDao;
import com.runying.vo.InHouseLogVO;
import com.runying.vo.TableVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:conf/spring-mvc.xml", "classpath:conf/spring-hibernate.xml", "classpath:conf/spring-beans.xml"})
public class InHouseLogDaoTest {
	@Autowired
	private InHouseLogDao inHouseLogDaoProxy;
	
	@Test
	public void testFind() {
		TableVO<InHouseLogVO> vos = inHouseLogDaoProxy.findInHouseLogVO("yqw", 1, 100);
		for(InHouseLogVO v : vos.getRows()) {
			System.out.println(v.getProductName());
		}
	}
}

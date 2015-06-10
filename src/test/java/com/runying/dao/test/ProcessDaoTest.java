package com.runying.dao.test;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.runying.service.ProcessService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:conf/spring-mvc.xml", "classpath:conf/spring-hibernate.xml", "classpath:conf/spring-beans.xml"})
public class ProcessDaoTest {
	@Autowired
	private ProcessService processService;
	
	@Before
	public void before() {
//		Process p = new Process();
//		p.setName("first gongxu");
//		p.setProcessNum(1);
//		processDao.addObject(p);
	}
	
	@Test
	public void addProcessTest() {
		
	}
}

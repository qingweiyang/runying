package com.runying.dao.test;

import org.junit.Before;
import org.junit.Test;

import com.runying.dao.ProcessDao;
import com.runying.po.Orders;
import com.runying.po.Process;
import com.runying.po.User;
import com.runying.util.Msg;


public class ProcessDaoTest {
	private ProcessDao processDao = new ProcessDao();
	
	@Before
	public void before() {
//		Process p = new Process();
//		p.setName("first gongxu");
//		p.setProcessNum(1);
//		processDao.addObject(p);
	}
	
	//@Test
	public void getPreProcessAccountTest() {
//		Process p = processDao.findByID(Process.class, 2);
//		System.out.println(processDao.getPreProcessAccount(p));
	}
	
	@Test
	public void addProcessTest() {
		Orders o = processDao.findByID(Orders.class, 1);
		User u = processDao.findByID(User.class, 1);
		
		Process p = new Process();
		p.setName("first gongxu");
		p.setNum(6);
		p.setOrders(o);
		
		Msg msg = processDao.addProcess(o, u, u, p);
		System.out.println(msg.getDescription());
		
	}
}

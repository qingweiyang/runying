package com.runying.dao.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.runying.dao.ProcessDao;
import com.runying.po.Orders;
import com.runying.po.Process;
import com.runying.po.User;


public class ProcessDaoTest {
	private ProcessDao processDao = new ProcessDao();
	
	@Before
	public void before() {
//		Process p = new Process();
//		p.setName("first gongxu");
//		p.setProcessNum(1);
//		processDao.addObject(p);
	}
	
	@Test
	public void getPreProcessAccountTest() {
		Process p = processDao.findByID(Process.class, 2);
		System.out.println(processDao.getPreProcessAccount(p));
	}
	
	//@Test
	public void addProcessTest() {
		Orders o = processDao.findByID(Orders.class, 2);
		User u = new User();
		u.setId(1);
		u.setUsername("adm");
		
		Process p = new Process();
		p.setId(1);
		p.setName("first gongxu");
		p.setNum(4);
		p.setOrders(o);
		p.setReceiver(u);
		List<Process> ps = new ArrayList<Process>();
		ps.add(p);
//		Msg msg = processDao.addProcess(o, u, u, p);
		processDao.addProcess(o, u, u, p);
//		processDao.addProcesses(o, u, ps);
//		System.out.println(msg.getDescription());
		
	}
}

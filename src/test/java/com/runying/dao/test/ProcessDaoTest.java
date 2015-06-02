package com.runying.dao.test;

import java.util.ArrayList;
import java.util.List;

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
	
	@Test
	public void addProcessTest() {
		Orders o = processDao.findByID(Orders.class, 1);
		User u = new User();
		u.setId(1);
		u.setUsername("yqw");
		
		User u2 = new User();
		u2.setId(2);
		u2.setUsername("adm");
		
		Process p1 = new Process();
		p1.setId(1);
		p1.setProcessNum(1);
		p1.setName("first gongxu");
		p1.setNum(4);
		p1.setOrders(o);
		p1.setReceiver(u);
		List<Process> ps = new ArrayList<Process>();
		ps.add(p1);
		Process p2 = new Process();
		p2.setId(2);
		p2.setProcessNum(2);
		p2.setName("first gongxu");
		p2.setNum(5);
		p2.setOrders(o);
		p2.setReceiver(u);
		ps.add(p2);
//		Msg msg = processDao.addProcess(o, u, u, p);
//		processDao.addProcess(o, u, u, p);
		Msg msg = processDao.addProcesses(o, u, ps);
		System.out.println(msg.getDescription());
		
	}
}

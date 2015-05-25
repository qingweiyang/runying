package com.runying.dao;

import org.hibernate.Session;

import com.runying.po.Orders;
import com.runying.po.Process;
import com.runying.po.User;
import com.runying.util.DaoUtil;
import com.runying.util.HibernateUtil;
import com.runying.util.Msg;

public class ProcessDao extends DaoUtil{
	
	public Msg addProcess(Orders o, User responsible, User receiver, Process p) {
		Msg msg = new Msg();
		//valid whether account of each process is less or equal than needed in orders
		
		return null;
	}
	
//	private int getProcessAccount(Orders o, Process p) {
//		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
//		session.beginTransaction();
//		
//		List mothers = session.createQuery(
//	    "select count(p.num) from Process as p where p.name = ?")
//	    .setString(0, name)
//	    .list();
//	}
}

package com.runying.dao;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.runying.po.Orders;
import com.runying.util.DaoUtil;
import com.runying.util.HibernateUtil;
import com.runying.util.Msg;

public class OrdersDao extends DaoUtil{
	private String className = "Orders";
	
	@SuppressWarnings("unchecked")
	public List<Orders> findAll() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		
		String hql = "from "+className;
		Query query = session.createQuery(hql);
		List<Orders> res = query.list();
		session.getTransaction().commit();
		
		return res;
	}
	
	public Orders findByID(int id) {
		return this.findByID(Orders.class, id);
	}
	
	/**
	 * add an order, return false if the user has not the privilege. System adds current time as the time the recorded in system
	 *  
	 * @param o
	 * @return
	 */
	public Msg addOrders(Orders o) {
		Msg msg = new Msg();
		int privilege = o.getOperator().getPrivilege();
		//the use has not privilege
		if((privilege & 1) == 0) {
			msg.setStatus(0);
			msg.setDescription("权限不足");
			return msg;
		}
		//获取系统（服务器）当前时间
		Calendar now = Calendar.getInstance();
		Date date = now.getTime();
		o.setSystemTime(date);
		o.setStatus(1);
		this.addObject(o);
		msg.setStatus(1);
		return msg;
	}
}

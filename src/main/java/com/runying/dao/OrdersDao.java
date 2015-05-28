package com.runying.dao;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.runying.po.Orders;
import com.runying.po.Product;
import com.runying.util.DaoUtil;
import com.runying.util.HibernateUtil;
import com.runying.util.Msg;

public class OrdersDao extends DaoUtil{
	
	@SuppressWarnings("unchecked")
	public List<Product> findAll() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		
		String hql = "from Orders";
		Query query = session.createQuery(hql);
		List<Product> res = query.list();
		session.getTransaction().commit();
		
		return res;
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
		this.addObject(o);
		msg.setStatus(1);
		return msg;
	}
}

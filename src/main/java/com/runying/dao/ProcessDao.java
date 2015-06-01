package com.runying.dao;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.runying.po.Orders;
import com.runying.po.Process;
import com.runying.po.User;
import com.runying.util.DaoUtil;
import com.runying.util.HibernateUtil;
import com.runying.util.Msg;

public class ProcessDao extends DaoUtil{
	private UserDao userDao = new UserDao();
	
	/**
	 * 增加多道工序
	 * 
	 * @param o
	 * @param ps
	 * @return
	 */
	public Msg addProcesses(Orders o, User responsible, List<Process> ps) {
		Msg msg = new Msg();
		for(Process p : ps) {
			//检查用户是否存在 
			User receiver = userDao.findByUsername(p.getReceiver().getUsername());
			if(null == receiver) {
				msg.setStatus(0);
				msg.setDescription("接收者不存在");
				return msg;
			}
			
			msg = addProcess(o, responsible, receiver, p);
			if(msg.getStatus() == 0) {
				return msg;
			}
		}
		return msg;
	}
	
	public Msg addProcess(Orders o, User responsible, User receiver, Process p) {
		Msg msg = new Msg();
		
		p.setOrders(o);
		p.setReceiver(receiver);
		p.setResponsible(responsible);
		//先自动生成 工序号
		p.setProcessNum(this.getProcessNum(p));
		//valid whether account of each process is less or equal than needed in orders
		int pre = this.getPreProcessAccount(p);
		if(p.getNum() > pre) {
			//当前工序数量大于前一道工序数量，错误
			msg.setStatus(0);
			msg.setDescription("工序数量必须小于或等于前一道工序数量");
			return msg;
		}
		
		//获取系统（服务器）当前时间
		Calendar now = Calendar.getInstance();
		Date date = now.getTime();
		p.setSystemTime(date);
		this.addObject(p);
		//修改orders状态为 已进行生产计划
		Orders oDB = this.findByID(Orders.class, o.getId());
		oDB.setStatus(2);
		new DaoUtil().updat(oDB);
		msg.setStatus(1);
		return msg;
	}
	
	
	public int getPreProcessAccount(Process p) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		
		int curNum = p.getProcessNum();
		if(p == null || curNum == 1) {
			//第一道工序数量无限制
			return Integer.MAX_VALUE;
		}
		
		String hql = "from Process as p where p.orders = ? and p.processNum = ?";
		Query query = session.createQuery(hql);
		query.setEntity(0, p.getOrders());
		query.setParameter(1, curNum-1);
		Process resP = (Process) query.uniqueResult();
		session.getTransaction().commit();
		
		return resP.getNum();
	}
	
	/**
	 * 获取当前工序的 工序号
	 * @param p
	 * @return
	 */
	private int getProcessNum(Process p) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		
		String hql = "from Process as p where p.orders = ?";
		Query query = session.createQuery(hql);
		query.setEntity(0, p.getOrders());
		
		if(query.list() == null) {
			return 1;
		}
		int num = query.list().size()+1;
		session.getTransaction().commit();
		
		return num;
	}
}

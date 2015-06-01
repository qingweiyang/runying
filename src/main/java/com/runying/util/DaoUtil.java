package com.runying.util;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.runying.po.User;

public class DaoUtil {
	
	public <T> int addObject(T o) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        int id = (Integer) session.save(o);
        session.getTransaction().commit();
        
        return id;
	}
	
	public <T> void updat(T o) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.update(o);
        session.getTransaction().commit();
	}
	
	@SuppressWarnings("unchecked")
	public <T> List<T> loginCheck(String username, String password) {
//		List mothers = session.createQuery(
//			    "select mother from Cat as cat join cat.mother as mother where cat.name = ?")
//			    .setString(0, name)
//			    .list();
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		
		String hql = "from com.runying.po.User as user where user.username = ? and user.password = ?";
		Query query = session.createQuery(hql);
		query.setParameter(0, username);
		query.setParameter(1, password);
		List<T> res = query.list();
		session.getTransaction().commit();
		
		return res;
	}
	
	/**
	 * get object by id
	 * @param T
	 * @param id
	 * @return
	 */
	public <T, V> V findByID(Class<T> c, int id) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        
		@SuppressWarnings("unchecked")
		V o = (V) session.get(c, id);
		
        session.getTransaction().commit();
        return o;
	}
	
	@SuppressWarnings("unchecked")
	protected <T> List<T> findByColumn(String className, String columnName, String columnValue) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		
		String hql = "from "+className+" as t where t."+columnName+" = ?";
		Query query = session.createQuery(hql);
		query.setParameter(0, columnValue);
		List<T> res = query.list();
		session.getTransaction().commit();
		
		return res;
	}
	
	public static void main(String[] args) {
		User u = new User();
		u.setUsername("test");
		u.setPassword("123");
		new DaoUtil().addObject(u);
		new DaoUtil().addObject(u);
		new DaoUtil().addObject(u);
		HibernateUtil.getSessionFactory().close();
    }
}

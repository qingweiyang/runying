package com.runying.util;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.runying.po.User;

public class DaoUtil {
	
	public static<T> int addObject(T o) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        int id = (Integer) session.save(o);
        session.getTransaction().commit();
        return id;
	}
	
	@SuppressWarnings("unchecked")
	public static<T> List<T> loginCheck(String username, String password) {
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
		return query.list();
	}
	
	/**
	 * get object by id
	 * @param T
	 * @param id
	 * @return
	 */
	public static <T, V> V getObject(Class<T> c, int id) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        
		@SuppressWarnings("unchecked")
		V o = (V) session.get(c, id);
		
        session.getTransaction().commit();
        return o;
	}
	
	public static void main(String[] args) {
		DaoUtil.getObject(User.class, 1);
		System.out.println(User.class.getName());
		HibernateUtil.getSessionFactory().close();
    }
}

package com.runying.util;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.runying.po.User;

@Repository
public class DaoUtil{
	
	@SuppressWarnings("unchecked")
	@Transactional
	protected <T> List<T> findAll(String className) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		
		String hql = "from "+className;
		Query query = session.createQuery(hql);
		List<T> res = query.list();
		session.getTransaction().commit();
		
		return res;
	}
	
	@Transactional
	public <T> int addObject(T o) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        int id = (Integer) session.save(o);
        session.getTransaction().commit();
        
        return id;
	}
	
	@Transactional
	public <T> void updat(T o) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.update(o);
        session.getTransaction().commit();
	}
	
	@SuppressWarnings("unchecked")
	@Transactional
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
//		session.getTransaction().commit();
		
		return res;
	}
	
	/**
	 * get object by id
	 * @param T
	 * @param id
	 * @return
	 */
	@Transactional
	public <T, V> V findByID(Class<T> c, int id) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        
		@SuppressWarnings("unchecked")
		V o = (V) session.get(c, id);
		
        session.getTransaction().commit();
        return o;
	}
	
	@SuppressWarnings("unchecked")
	@Transactional
	protected <T> List<T> findByColumn(String className, String columnName, Object columnValue) {
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

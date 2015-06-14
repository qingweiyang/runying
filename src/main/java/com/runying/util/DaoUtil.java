package com.runying.util;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public abstract class DaoUtil {
	protected abstract String className();
	
	@Resource(name="sessionFactory")
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	public Long size() {
		Session session = sessionFactory.getCurrentSession();
		
		String hql = "select count(*) from " + this.className();
		Query query = session.createQuery(hql);
		return (Long) query.uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	public <T> List<T> findAll(int pageNumber, int countPerPage) {
		Session session = sessionFactory.getCurrentSession();
		
		String hql = "from " + this.className();
		Query query = session.createQuery(hql);
		query.setFirstResult((pageNumber - 1)*countPerPage);
		query.setMaxResults(countPerPage);
		List<T> res = query.list();
		
		return res;
	}
	
	public <T> int addObject(T o) {
		Session session = sessionFactory.getCurrentSession();
		
        int id = (Integer) session.save(o);
        
        return id;
	}
	
	public <T> void updat(T o) {
		Session session = sessionFactory.getCurrentSession();
      
        session.update(o);
        
	}
	
	@SuppressWarnings("unchecked")
	public <T> List<T> loginCheck(String username, String password) {
		Session session = sessionFactory.getCurrentSession();
		
		String hql = "from com.runying.po.User as user where user.username = ? and user.password = ?";
		Query query = session.createQuery(hql);
		query.setParameter(0, username);
		query.setParameter(1, password);
		List<T> res = query.list();
		
		return res;
	}
	
	/**
	 * get object by id
	 * @param T
	 * @param id
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <T, V> V findByID(Class<T> c, int id) {
		Session session = sessionFactory.getCurrentSession();
		
		V o = (V) session.get(c, id);
		
        return o;
	}
	
	/**
	 * 
	 * @param className
	 * @param columnName
	 * @param columnValue
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <T> List<T> findByColumn(String columnName, Object columnValue) {
		Session session = sessionFactory.getCurrentSession();
		
		String hql = "from "+this.className()+" as t where t."+columnName+" = ?";
		Query query = session.createQuery(hql);
		query.setParameter(0, columnValue);
		List<T> res = query.list();
		
		return res;
	}
	
	/**
	 * 
	 * @param className
	 * @param columnName
	 * @param columnValue
	 * @param pageNumber
	 * @param countPerPage
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <T> List<T> findByColumn(String columnName, Object columnValue,
			int pageNumber, int countPerPage) {
		Session session = sessionFactory.getCurrentSession();
		
		String hql = "from "+ this.className() +" as t where t."+columnName+" = ?";
		Query query = session.createQuery(hql);
		query.setParameter(0, columnValue);
		query.setFirstResult((pageNumber - 1)*countPerPage);
		query.setMaxResults(countPerPage);
		List<T> res = query.list();
		
		return res;
	}

}

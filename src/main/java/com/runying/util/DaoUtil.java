package com.runying.util;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.runying.po.User;

public class DaoUtil {
	@Resource(name="sessionFactory")
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@SuppressWarnings("unchecked")
	protected <T> List<T> findAll(String className) {
		Session session = sessionFactory.getCurrentSession();
		
		String hql = "from "+className;
		Query query = session.createQuery(hql);
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
	
	@SuppressWarnings("unchecked")
	public <T> List<T> findByColumn(String className, String columnName, Object columnValue) {
		Session session = sessionFactory.getCurrentSession();
		
		String hql = "from "+className+" as t where t."+columnName+" = ?";
		Query query = session.createQuery(hql);
		query.setParameter(0, columnValue);
		List<T> res = query.list();
		
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

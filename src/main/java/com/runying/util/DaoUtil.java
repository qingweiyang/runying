package com.runying.util;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

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
	 * get object by id
	 * @param T
	 * @param id
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <T> T findByID(int id) {
		Session session = sessionFactory.getCurrentSession();
		
		String hql = "from "+this.className()+" as t where t.id = ?";
		Query query = session.createQuery(hql);
		query.setParameter(0, id);
		T res = (T) query.uniqueResult();
		
        return res;
	}
	
	/**
	 * 根据不同列筛选，用and连接
	 * 
	 * @param className
	 * @param columnName
	 * @param columnValue
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <T> List<T> findByColumns(Map<String, Object> cols) {
		Session session = sessionFactory.getCurrentSession();
		
		String hql = "from "+this.className()+" as t where";
		Iterator<Entry<String, Object>> itr = cols.entrySet().iterator();
		while(itr.hasNext()) {
			Entry<String, Object> entry = itr.next();
			hql += " t." + entry.getKey() + "=? and";
		}
		hql = hql.substring(0, hql.length() - 4);
		Query query = session.createQuery(hql);
		itr = cols.entrySet().iterator();
		int i  = 0 ;
		while(itr.hasNext()) {
			Entry<String, Object> entry = itr.next();
			query.setParameter(i++, entry.getValue());
		}
		List<T> res = query.list();
		
		return res;
	}
	
	/**
	 * 通过查询连接词 or|and 获取相关条件数据, 在一列上查
	 * @param vals
	 * @return
	 */
	public <T> List<T> findByColumnWithConnector(String colName, String connetor, List<Object> vals,
			int pageNumber, int countPerPage) {
		Session session = sessionFactory.getCurrentSession();
		
		String hql = "from "+this.className()+" as t where";
		for(int i = 0 ; i < vals.size() ; i++) {
			hql += " t." + colName + "=? " + connetor;
		}
		hql = hql.substring(0, hql.length() - (connetor.length() + 1));
		Query query = session.createQuery(hql);
		for(int i = 0 ; i < vals.size() ; i++) {
			query.setParameter(i, vals.get(i));
		}
		query.setFirstResult((pageNumber - 1)*countPerPage);
		query.setMaxResults(countPerPage);
		@SuppressWarnings("unchecked")
		List<T> res = query.list();
		
		return res;
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
	 * 获取相关查询条件下的数据总数量
	 * 
	 * @param colName
	 * @param connetor
	 * @param vals
	 * @return
	 */
	public int countByColumns(String colName, String connetor, List<Object> vals) {
		Session session = sessionFactory.getCurrentSession();
		
		String hql = "select count(*) from "+this.className()+" as t where";
		for(int i = 0 ; i < vals.size() ; i++) {
			hql += " t." + colName + "=? " + connetor;
		}
		hql = hql.substring(0, hql.length() - (connetor.length() + 1));
		Query query = session.createQuery(hql);
		for(int i = 0 ; i < vals.size() ; i++) {
			query.setParameter(i, vals.get(i));
		}
		
		int res = ((Long) query.uniqueResult()).intValue();
		
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

package com.runying.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.runying.po.Product;
import com.runying.util.DaoUtil;
import com.runying.util.HibernateUtil;

public class ProductDao extends DaoUtil{
	
	@SuppressWarnings("unchecked")
	public List<Product> findAll() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		
		String hql = "from Product";
		Query query = session.createQuery(hql);
		
		return query.list();
	}
}

package com.runying.util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtil {

    private static final SessionFactory sessionFactory = buildSessionFactory3();

    /**
     * 官网提供方案，有问题，报错  org.hibernate.HibernateException: Access to DialectResolutionInfo cannot be null when 'hibernate.dialect' not set
     * @return
     *
    private static SessionFactory buildSessionFactory() {
        try {
            // Create the SessionFactory from hibernate.cfg.xml
            return new Configuration().configure().buildSessionFactory(
			    new StandardServiceRegistryBuilder().build() );
        }
        catch (Throwable ex) {
            // Make sure you log the exception, as it might be swallowed
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    } 
    
    private static SessionFactory buildSessionFactory2() {
        try {
          // Create the SessionFactory from hibernate.cfg.xml
          Configuration configuration = new Configuration();
          configuration.configure("hibernate.cfg.xml");
//          System.out.println("Hibernate Configuration loaded");
          
          //apply configuration property settings to StandardServiceRegistryBuilder
          ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
//          System.out.println("Hibernate serviceRegistry created");
          
          SessionFactory sessionFactory = configuration
                    .buildSessionFactory(serviceRegistry);
          
          return sessionFactory;
        }
        catch (Throwable ex) {
          // Make sure you log the exception, as it might be swallowed
          System.err.println("Initial SessionFactory creation failed." + ex);
          throw new ExceptionInInitializerError(ex);
        }
      } */
    
    private static SessionFactory buildSessionFactory3() {
        try {
            // Create the SessionFactory from hibernate.cfg.xml
        	Configuration cfg = new Configuration().configure();
            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
            .applySettings(cfg.getProperties()).build();
            SessionFactory factory = cfg.buildSessionFactory(serviceRegistry);
            return factory;
        }
        catch (Throwable ex) {
            // Make sure you log the exception, as it might be swallowed
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

}
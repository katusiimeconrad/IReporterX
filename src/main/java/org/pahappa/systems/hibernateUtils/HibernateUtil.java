package org.pahappa.systems.hibernateUtils;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * @author HP
 */
public class HibernateUtil {

    public static SessionFactory buildSessionFactoryObj() {
        try {
            sessionFactoryObj = new Configuration().configure().buildSessionFactory();
        } catch (ExceptionInInitializerError exceptionObj) {
            exceptionObj.printStackTrace();
        }
        return sessionFactoryObj;
    }    private static SessionFactory sessionFactoryObj = buildSessionFactoryObj();

    // Create The SessionFactory Object From Standard (Hibernate.cfg.xml) Configuration File

    public static SessionFactory getSessionFactory() {
        return sessionFactoryObj;
    }


}
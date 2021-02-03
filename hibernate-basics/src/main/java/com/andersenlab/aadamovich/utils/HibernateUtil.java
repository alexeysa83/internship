package com.andersenlab.aadamovich.utils;

import org.hibernate.Session;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class HibernateUtil {

    private final static String DEFAULT_UNIT = "com.andersenlab.aadamovich";
    private static final EntityManagerFactory emFactory;

    static {
        emFactory =  Persistence.createEntityManagerFactory
                (DEFAULT_UNIT);
    }

    public static EntityManager getEntityManager () {
        return emFactory.createEntityManager();
    }

    public static Session getSession() {
        return getEntityManager().unwrap(Session.class);
    }
}

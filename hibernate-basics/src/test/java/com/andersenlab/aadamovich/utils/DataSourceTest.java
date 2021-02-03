package com.andersenlab.aadamovich.utils;

import org.hibernate.Session;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class DataSourceTest {

    @Test
    void getManager() {
        final EntityManager entityManager = HibernateUtil.getEntityManager();
              assertNotNull(entityManager);
    }

    @Test
    void getSesssion() throws SQLException {
        final Session session = HibernateUtil.getSession();
        assertNotNull(session);
            }
}

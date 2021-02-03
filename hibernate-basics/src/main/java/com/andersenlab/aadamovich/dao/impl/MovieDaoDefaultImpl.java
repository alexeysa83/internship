package com.andersenlab.aadamovich.dao.impl;

import com.andersenlab.aadamovich.dao.BaseMovieDao;
import com.andersenlab.aadamovich.entity.Movie;
import com.andersenlab.aadamovich.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.*;

public class MovieDaoDefaultImpl implements BaseMovieDao<Movie> {

    private static volatile BaseMovieDao<Movie> instance;

    public static BaseMovieDao<Movie> getInstance() {
        BaseMovieDao<Movie> localInstance = instance;
        if (localInstance == null) {
            synchronized (BaseMovieDao.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new MovieDaoDefaultImpl();
                }
            }
        }
        return localInstance;
    }

    @Override
    public Integer add(Movie m) {
        try (final Session session = HibernateUtil.getSession()) {
            final Transaction transaction = session.beginTransaction();
            session.save(m);
            transaction.commit();
            return m.getId();
        }
    }

    @Override
    public Movie findById(Integer id) {
        try (final Session session = HibernateUtil.getSession()) {
            return session.get(Movie.class, id);
        }
    }

    /*
    Do not use session.update(m); as want this method to return some value
     */
    @Override
    public boolean update(Movie m) {
        try (final Session session = HibernateUtil.getSession()) {
            final Transaction transaction = session.beginTransaction();
            final int rowsUpdated = session.createQuery
                    ("update Movie m set m.name=:name, m.genre=:genre, m.cool=:isCool where m.id=:id")
                    .setParameter("name", m.getName())
                    .setParameter("genre", m.getGenre())
                    .setParameter("isCool", m.isCool())
                    .setParameter("id", m.getId())
                    .executeUpdate();
            transaction.commit();
            return rowsUpdated > 0;
        }
    }

    @Override
    public boolean delete(Integer id) {
        try (final Session session = HibernateUtil.getSession()) {
            final Transaction transaction = session.beginTransaction();
            final int rowsDeleted = session.createQuery
                    ("delete Movie m where m.id=:id")
                    .setParameter("id", id)
                    .executeUpdate();
            transaction.commit();
            return rowsDeleted > 0;
        }
    }
}

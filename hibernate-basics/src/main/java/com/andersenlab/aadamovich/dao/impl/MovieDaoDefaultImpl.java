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

//    @Override
//    public Integer add(Movie movie) {
//        int primaryKey = -1;
//        try (final Connection connection = mysql.getConnection();
//             final PreparedStatement state = connection.prepareStatement
//                     ("INSERT INTO movie (name, genre, is_cool) VALUES (? , ? , ?)",
//                             Statement.RETURN_GENERATED_KEYS)) {
//            state.setString(1, movie.getName());
//            state.setString(2, movie.getGenre());
//            state.setBoolean(3, movie.isCool());
//            final int rowsUpdated = state.executeUpdate();
//            if (rowsUpdated > 0) {
//                try (final ResultSet resultSet = state.getGeneratedKeys()) {
//                    resultSet.next();
//                    primaryKey = resultSet.getInt(1);
//                }
//            }
//            return primaryKey;
//        } catch (SQLException e) {
//            throw new RuntimeException("Fail to save movie to DB", e);
//        }
//    }


    @Override
    public Integer add(Movie m) {
        try (final Session session = HibernateUtil.getSession()) {
            final Transaction transaction = session.beginTransaction();
            session.save(m);
            transaction.commit();
            return m.getId();
        }
    }

//    @Override
//    public Movie findById(Integer id) {
//        try (final Connection connection = mysql.getConnection();
//             final PreparedStatement state = connection.prepareStatement
//                     ("SELECT * FROM movie WHERE id = ?")) {
//            state.setInt(1, id);
//            try (final ResultSet resultSet = state.executeQuery()) {
//                final boolean exist = resultSet.next();
//                if (!exist) {
//                    return null;
//                }
//                final String name = resultSet.getString("name");
//                final String genre = resultSet.getString("genre");
//                final boolean isCool = resultSet.getBoolean("is_cool");
//                final Movie movie = new Movie(name, genre, isCool);
//                movie.setId(id);
//                return movie;
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException("Fail to get movie from DB", e);
//        }
//    }


    @Override
    public Movie findById(Integer id) {
        try (final Session session = HibernateUtil.getSession()) {
            return session.get(Movie.class, id);
        }
    }

//    @Override
//    public boolean update(Movie movie) {
//        try (final Connection connection = mysql.getConnection();
//             final PreparedStatement state = connection.prepareStatement
//                     ("UPDATE movie SET name = ?, genre = ?, is_cool = ? WHERE id = ?")) {
//            state.setString(1, movie.getName());
//            state.setString(2, movie.getGenre());
//            state.setBoolean(3, movie.isCool());
//            state.setInt(4, movie.getId());
//            return state.executeUpdate() > 0;
//        } catch (SQLException e) {
//            throw new RuntimeException("Fail to update movie in DB", e);
//        }
//    }

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

    //    @Override
//    public boolean delete(Integer id) {
//        try (final Connection connection = mysql.getConnection();
//             final PreparedStatement state = connection.prepareStatement
//                     ("DELETE FROM movie WHERE id = ?")) {
//            state.setInt(1, id);
//            return state.executeUpdate() > 0;
//        } catch (SQLException e) {
//            throw new RuntimeException("Fail to delete movie from DB", e);
//        }
//    }
}

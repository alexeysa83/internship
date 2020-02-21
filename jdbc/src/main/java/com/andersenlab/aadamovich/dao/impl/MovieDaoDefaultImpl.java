package com.andersenlab.aadamovich.dao.impl;

import com.andersenlab.aadamovich.dao.BaseMovieDao;
import com.andersenlab.aadamovich.model.Movie;
import com.andersenlab.aadamovich.utils.DataSource;

import java.sql.*;

public class MovieDaoDefaultImpl implements BaseMovieDao <Movie> {

    private DataSource mysql = DataSource.getInstance();

    private static volatile BaseMovieDao <Movie> instance;

    public static BaseMovieDao <Movie> getInstance() {
        BaseMovieDao <Movie> localInstance = instance;
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
    public Integer add(Movie movie) {
        int primaryKey = -1;
        try (final Connection connection = mysql.getConnection();
             final PreparedStatement state = connection.prepareStatement
                     ("INSERT INTO movie (name, genre, is_cool) VALUES (? , ? , ?)",
                             Statement.RETURN_GENERATED_KEYS)) {
            state.setString(1, movie.getName());
            state.setString(2, movie.getGenre());
            state.setBoolean(3, movie.isCool());
            final int rowsUpdated = state.executeUpdate();
            if (rowsUpdated > 0) {
                try (final ResultSet resultSet = state.getGeneratedKeys()) {
                    resultSet.next();
                    primaryKey = resultSet.getInt(1);
                }
            }
            return primaryKey;
        } catch (SQLException e) {
            throw new RuntimeException("Fail to save movie to DB", e);
        }
    }

    @Override
    public Movie findById(Integer id) {
        try (final Connection connection = mysql.getConnection();
             final PreparedStatement state = connection.prepareStatement
                     ("SELECT * FROM movie WHERE id = ?")) {
            state.setInt(1, id);
            try (final ResultSet resultSet = state.executeQuery()) {
                final boolean exist = resultSet.next();
                if (!exist) {
                    return null;
                }
                final String name = resultSet.getString("name");
                final String genre = resultSet.getString("genre");
                final boolean isCool = resultSet.getBoolean("is_cool");
                final Movie movie = new Movie(name, genre, isCool);
                movie.setId(id);
                return movie;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Fail to get movie from DB", e);
        }
    }

    @Override
    public boolean update(Movie movie) {
        try (final Connection connection = mysql.getConnection();
             final PreparedStatement state = connection.prepareStatement
                     ("UPDATE movie SET name = ?, genre = ?, is_cool = ? WHERE id = ?")) {
            state.setString(1, movie.getName());
            state.setString(2, movie.getGenre());
            state.setBoolean(3, movie.isCool());
            state.setInt(4, movie.getId());
            return state.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Fail to update movie in DB", e);
        }
    }

    @Override
    public boolean delete(Integer id) {
        try (final Connection connection = mysql.getConnection();
             final PreparedStatement state = connection.prepareStatement
                     ("DELETE FROM movie WHERE id = ?")) {
            state.setInt(1, id);
            return state.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Fail to delete movie from DB", e);
        }
    }
}

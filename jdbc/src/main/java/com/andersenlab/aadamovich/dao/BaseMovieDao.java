package com.andersenlab.aadamovich.dao;

import com.andersenlab.aadamovich.model.Movie;

public interface BaseMovieDao {

    // Returns primary key for newly generated instance
    Integer add (Movie m);

    Movie findById (Integer id);

    boolean update (Movie m);

    boolean delete (Integer id);
}

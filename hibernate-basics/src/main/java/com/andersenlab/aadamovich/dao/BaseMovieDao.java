package com.andersenlab.aadamovich.dao;

public interface BaseMovieDao <T>{

    // Returns primary key for newly generated instance
    Integer add (T m);

    T findById (Integer id);

    boolean update (T m);

    boolean delete (Integer id);
}

package com.andersenlab.aadamovich.dao;

public interface BaseDao <T> {

    T add (T t);

    T getById (Integer id);

    boolean update(T t);

    boolean delete (Integer id);
}
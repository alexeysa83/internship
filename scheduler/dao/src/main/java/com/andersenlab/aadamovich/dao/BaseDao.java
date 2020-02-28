package com.andersenlab.aadamovich.dao;

public interface BaseDao <T> {

    T save(T t);

    T findById(Integer id);

    boolean update(T t);

    boolean delete (Integer id);
}
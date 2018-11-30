package com.zr.dao;

import java.util.List;

/**
 * 定义类型是T 代表任意类型
 *  （1） 任意类型使用大写字母表示，不一定是T
 *
 * @param <T>
 */
public interface BaseDao<T>  {

    //增
    void add(T t);
    //删
    void delete(T t);
    //改
    void update(T t);
    //查
        //根据Id查询
    T findById(int id);
        //查询所有
    List<T> findAll();



}

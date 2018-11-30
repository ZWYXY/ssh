package com.zr.dao;

import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

public class BaseDaoImpl<T> extends HibernateDaoSupport implements BaseDao<T> {

    private Class clazzType;
    //构造方法
    public BaseDaoImpl(){
        //1 获取当前运行对象的class
        //比如运行customerDao实现类，得到customerDao实现类class
        Class clazz = this.getClass();

        //2 获取运行类的父类的 参数化类型 就是 BaseDaoImpl<CustomerEntity>
        Type type = clazz.getGenericSuperclass();

        //3 将type类型转换成子接口ParameterizedType
        ParameterizedType ptype = (ParameterizedType) type;

        //4 获取实际类型参数 就是 <Customer>中的Customer
        Type[] types = ptype.getActualTypeArguments();

        //5 把 Type 变成class
        Class clazzParameter = (Class)types[0];
        this.clazzType = clazzParameter;
    }

    @Override
    public void add(T t) {
        this.getHibernateTemplate().save(t);
    }

    @Override
    public void delete(T t) {
        this.getHibernateTemplate().delete(t);
    }

    @Override
    public void update(T t) {
        this.getHibernateTemplate().update(t);
    }

    @Override
    public T findById(int id) {
        return (T)this.getHibernateTemplate().get(clazzType, id);
    }

    @Override
    public List<T> findAll() {
        System.out.println("Hello Git");//Who
        return (List<T>) this.getHibernateTemplate().find("from " + clazzType.getSimpleName());
    }
}

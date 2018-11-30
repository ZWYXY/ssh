package com.zr.dao;

import com.zr.entity.DataDictionary;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Repository(value = "DDDao")
public class DDDaoImpl extends BaseDaoImpl<DataDictionary> implements DDDao{

    @Resource
    public void setMySessionFactory(SessionFactory sessionFactory){
        super.setSessionFactory(sessionFactory);
    }

}

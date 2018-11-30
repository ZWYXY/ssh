package com.zr.dao;


import com.zr.entity.VisitEntity;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Repository
public class VisitImpl extends HibernateDaoSupport implements VisitDao{


    //注入sessionFactory
    @Resource
    public void setMySessionFactory(SessionFactory sessionFactory){
       super.setSessionFactory(sessionFactory);
    }

    @Override
    public void save(VisitEntity visitEntity) {
        this.getHibernateTemplate().save(visitEntity);
    }

    @Override
    public List<VisitEntity> findAll() {
        return (List<VisitEntity>)this.getHibernateTemplate().find("from VisitEntity");
    }
}

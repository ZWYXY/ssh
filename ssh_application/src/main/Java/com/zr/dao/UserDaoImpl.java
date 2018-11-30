package com.zr.dao;


import com.zr.entity.UserEntity;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import java.util.List;

public class UserDaoImpl extends HibernateDaoSupport implements UserDao {

//    private HibernateTemplate hibernateTemplate;

//    public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
//        this.hibernateTemplate = hibernateTemplate;
//    }

    //登录的功能
    @Override
    @SuppressWarnings("all")
    public UserEntity loginUser(UserEntity userEntity) {
        //得到hibernate Template对象
        HibernateTemplate hibernateTemplate = this.getHibernateTemplate();
        //登录查询的操作
        //根据用户名和密码查询
        List<UserEntity> list =  (List<UserEntity>) this.getHibernateTemplate().find
                                 ("from UserEntity  where username = ?0 and password = ?1",
                                 userEntity.getUsername(), userEntity.getPassword() );
        //返回User对象
        if(list != null && list.size() != 0){
            return list.get(0);
        }
        return null;
    }

    @Override   //返回所有查询结果
    public List<UserEntity> findAll() {
        return (List<UserEntity>)this.getHibernateTemplate().find("from UserEntity");
    }

}

























package com.zr.service;

import com.zr.dao.UserDao;
import com.zr.entity.UserEntity;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public class UserService {

    private UserDao userDao;
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    //登录方法
    public UserEntity login(UserEntity userEntity){
        //调用Dao 里面的方法
        return  userDao.loginUser(userEntity);
    }

    //查询所有
    public List<UserEntity> findAll(){
        return userDao.findAll();
    }




}

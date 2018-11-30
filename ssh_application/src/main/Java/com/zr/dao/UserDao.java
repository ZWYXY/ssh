package com.zr.dao;

import com.zr.entity.UserEntity;

import java.util.List;

public interface UserDao {

    UserEntity loginUser(UserEntity userEntity);

    List<UserEntity> findAll();


}

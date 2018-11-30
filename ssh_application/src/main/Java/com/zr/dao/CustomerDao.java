package com.zr.dao;

import com.zr.entity.CustomerEntity;

import java.util.List;

public interface CustomerDao extends BaseDao<CustomerEntity>{


    int findCount();

    List<CustomerEntity> findPage(int begin, int pageSize);

    List<CustomerEntity> findCondition(CustomerEntity customerEntity);

    List<CustomerEntity> findMoreCondition(CustomerEntity customerEntity);

    List countBycustSources();

    List<CustomerEntity> countBycustLevel();


/* 增删改 查（查询所有，按Id查询）都在BaseDao中定义
    List<CustomerEntity> findAll();

    CustomerEntity findOne(int cid);

    void add (CustomerEntity customerEntity);

    void delete(CustomerEntity customerEntity);

    void update(CustomerEntity customerEntity);
*/
}

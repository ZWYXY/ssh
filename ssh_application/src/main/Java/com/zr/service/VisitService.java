package com.zr.service;


import com.zr.dao.VisitDao;

import com.zr.entity.VisitEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class VisitService {

    //注入VisitDao属性
    private VisitDao visitDao;
    @Autowired
    public void setVisitDao(VisitDao visitDao){
        this.visitDao = visitDao;
    }

    //1 保存 客户拜访 信息
    public void save(VisitEntity visitEntity){
        visitDao.save(visitEntity);
    }
    //2 查询所有 客户拜访信息
    public List<VisitEntity> findAll(){
        return visitDao.findAll();
    }
}

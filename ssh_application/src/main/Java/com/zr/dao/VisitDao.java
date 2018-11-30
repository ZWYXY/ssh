package com.zr.dao;

import com.zr.entity.VisitEntity;

import java.util.List;

public interface VisitDao {

    void save(VisitEntity visitEntity);

    List<VisitEntity> findAll();

}

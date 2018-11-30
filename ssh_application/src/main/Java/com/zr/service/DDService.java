package com.zr.service;

import com.zr.dao.DDDao;
import com.zr.entity.DataDictionary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class DDService {
    //注入DDDao属性
    private DDDao ddDao;
    @Autowired
    public void setDdDao(DDDao ddDao){
        this.ddDao = ddDao;
    }

    public List<DataDictionary> findAll() {
        return ddDao.findAll();
    }
}

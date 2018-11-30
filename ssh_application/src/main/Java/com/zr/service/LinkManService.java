package com.zr.service;


import com.zr.dao.LinkManDao;
import com.zr.entity.LinkMan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Scope("prototype")
@Service(value = "linkManService")
@Transactional //开启事务注解
public class LinkManService {

    private LinkManDao linkManDao;
    @Autowired
    public void setLinkManDao(LinkManDao linkManDao){
        this.linkManDao = linkManDao;
    }

    public void add(LinkMan linkMan){
        linkManDao.add(linkMan);
    }

    public List<LinkMan> findAll() {
        return linkManDao.findAll();
    }

    public LinkMan findById(int lkmId) {
        return linkManDao.findById(lkmId);
    }

    public void update(LinkMan linkMan) {
        linkManDao.update(linkMan);
    }

    public void delete(LinkMan linkMan) {
        linkManDao.delete(linkMan);
    }

    public List<LinkMan> findMoreCondition(LinkMan linkMan) {
        return  linkManDao.findMoreCondition(linkMan);
    }

    //begin 开始位置， rows 每页行数
    public List<LinkMan> findPagingJson(int begin, int rows) {
        return linkManDao.findPage(begin, rows);
    }

    public int findCount() {
         return linkManDao.findCount();
    }
}

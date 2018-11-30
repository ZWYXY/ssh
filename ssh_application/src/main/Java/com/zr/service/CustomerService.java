package com.zr.service;

import com.zr.dao.CustomerDao;
import com.zr.entity.CustomerEntity;
import com.zr.entity.PageBean;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public class CustomerService {
    private CustomerDao customerDao;
    public void setCustomerDao(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    public void add(CustomerEntity customerEntity) {
        customerDao.add(customerEntity);
    }

    public List<CustomerEntity> findAll(){
        return customerDao.findAll();
    }

    public CustomerEntity findById(int cid){
        return customerDao.findById(cid);
    }

    public void delete(CustomerEntity customerEntity){
        customerDao.delete(customerEntity);
    }

    public void update(CustomerEntity customerEntity){
        customerDao.update(customerEntity);
    }

    //封装分页的数据到pageBean中去
    public PageBean listPage(Integer currentPage){
        //创建pageBean对象
        PageBean pageBean= new PageBean();

        //当前页
        pageBean.setCurrentPage(currentPage);

        //总记录数
        int totalCount = customerDao.findCount();
        pageBean.setTotalCount(totalCount);

        //每页显示记录数
        int pageSize = 9;

        //总页数
            //用总记录数，除以 每页显示记录数
        int totalPage = 0;
        if(totalCount % pageSize == 0){ //整除
            totalPage = totalCount / pageSize;
        } else { //不整除
            totalPage = totalCount / pageSize + 1;
        }
        pageBean.setTotalPage(totalPage);

        //开始位置
        int begin = (currentPage - 1) * pageSize;

        //每页记录list集合
        List<CustomerEntity> list = customerDao.findPage(begin , pageSize);
        pageBean.setList(list);

        return pageBean;
    }

    public List<CustomerEntity> findCondition(CustomerEntity customerEntity){
        return customerDao.findCondition(customerEntity);
    }

    public List<CustomerEntity> findMoreCondition(CustomerEntity customerEntity) {

        return customerDao.findMoreCondition(customerEntity);
    }

    public List countBycustSources() {
        return customerDao.countBycustSources();
    }

    public List<CustomerEntity> countBycustLevel() {

        return customerDao.countBycustLevel();
    }

    public List<CustomerEntity> findPageJson(int begin, int rows) {
        return customerDao.findPage(begin, rows);
    }

    public int findCount() {
        return customerDao.findCount();
    }
}

package com.zr.entity;


import com.alibaba.fastjson.annotation.JSONField;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;

public class CustomerEntity {
    private  Integer cid;
    private  String custName;
    //private  String custLevel;
    //在客户中表示DataDictionary对象
    @JSONField(serialize = false) //这个dataDictionary 不进行序列化
    private DataDictionary dataDictionary;
    public DataDictionary getDataDictionary() {
        return dataDictionary;
    }
    public void setDataDictionary(DataDictionary dataDictionary) {
        this.dataDictionary = dataDictionary;
    }

    private  String custSource;
    private  String custPhone;
    private  String custLinkman;


    //在客户中表示所有的联系人
    @JSONField(serialize = false)
    private Set<LinkMan> setLinkMan = new HashSet<>();
    public Set<LinkMan> getSetLinkMan() { //这个地方有个问题如果一对多的关系没有在.hbm.xml中配置，这边会报错'Basic' attribute type should not be a container
        return setLinkMan;
    }
    public void setSetLinkMan(Set<LinkMan> setLinkMan) {
        this.setLinkMan = setLinkMan;
    }
    //在客户表示拜访记录
    @JSONField(serialize = false) //让set集合不进行序列化从而 让set集合不进行转换
    private Set<VisitEntity> visitEntitySet = new HashSet<>();
    public Set<VisitEntity> getVisitEntitySet() {
        return visitEntitySet;
    }
    public void setVisitEntitySet(Set<VisitEntity> visitEntitySet) {
        this.visitEntitySet = visitEntitySet;
    }

    public Integer getCid() {
        return cid;
    }
    public void setCid(Integer cid) {
        this.cid = cid;
    }
    public String getCustName() {
        return custName;
    }
    public void setCustName(String custName) {
        this.custName = custName;
    }
//    public String getCustLevel() {
//        return custLevel;
//    }
//    public void setCustLevel(String custLevel) {
//        this.custLevel = custLevel;
//    }
    public String getCustSource() {
        return custSource;
    }
    public void setCustSource(String custSource) {
        this.custSource = custSource;
    }
    public String getCustPhone() {
        return custPhone;
    }
    public void setCustPhone(String custPhone) {
        this.custPhone = custPhone;
    }
    public String getCustLinkman() {
        return custLinkman;
    }
    public void setCustLinkman(String custLinkman) {
        this.custLinkman = custLinkman;
    }
}

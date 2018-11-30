package com.zr.entity;



public class LinkMan {

    private Integer lkmId;
    private String lkmName;
    private String lkmGender;
    private String lkmPhone;

    /*在联系人中表示 这个联系人属于哪个客户
    *  第一步，在LinkMan中定义 CustomerEntity
    *  第二步，生成CustomerEntity 的setter 和 getter 方法
    * */


    private  CustomerEntity customerEntity;
    public void setCustomerEntity(CustomerEntity customerEntity){this.customerEntity = customerEntity;}
    public CustomerEntity getCustomerEntity(){return customerEntity; }






    public Integer getLkmId() {
        return lkmId;
    }

    public void setLkmId(Integer lkmId) {
        this.lkmId = lkmId;
    }

    public String getLkmName() {
        return lkmName;
    }

    public void setLkmName(String lkmName) {
        this.lkmName = lkmName;
    }

    public String getLkmGender() {
        return lkmGender;
    }

    public void setLkmGender(String lkmGender) {
        this.lkmGender = lkmGender;
    }

    public String getLkmPhone() {
        return lkmPhone;
    }

    public void setLkmPhone(String lkmPhone) {
        this.lkmPhone = lkmPhone;
    }
}

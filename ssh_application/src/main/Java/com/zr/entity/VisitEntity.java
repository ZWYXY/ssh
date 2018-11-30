package com.zr.entity;

public class VisitEntity {

    private Integer vid;
    private String address;
    private String times;

    //表示CustomerEntity
    private CustomerEntity customerEntity;
    public CustomerEntity getCustomerEntity() { return customerEntity; }
    public void setCustomerEntity(CustomerEntity customerEntity) { this.customerEntity = customerEntity; }
    //表示UserEntity
    private UserEntity userEntity;
    public void setUserEntity(UserEntity userEntity){ this.userEntity = userEntity;}
    public UserEntity getUserEntity(){ return userEntity; }


    public Integer getVid() {
        return vid;
    }

    public void setVid(Integer vid) {
        this.vid = vid;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTimes() {
        return times;
    }

    public void setTimes(String times) {
        this.times = times;
    }
}

package com.example.demo.pojo;

import org.springframework.stereotype.Component;

@Component
public class Lawyer {
    private short lid;
    private String lname;
    private short age;
    private String location;
    private short status;//0为未注册，1为经管理员审核注册
    private String others;



    public short getLid() {
        return lid;
    }

    public void setLid(short lid) {
        this.lid = lid;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public short getAge() {
        return age;
    }

    public void setAge(short age) {
        this.age = age;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public short getStatus() {
        return status;
    }

    public void setStatus(short status) {
        this.status = status;
    }

    public String getOthers() {
        return others;
    }

    public void setOthers(String others) {
        this.others = others;
    }
}

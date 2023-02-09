package com.example.demo.pojo;

import org.springframework.stereotype.Component;

@Component
public class User {
    private short  uid;
    private String uname;
    private String location;
    private short age;
    private String others;

    public short getUid() {
        return uid;
    }

    public void setUid(short uid) {
        this.uid = uid;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public short getAge() {
        return age;
    }

    public void setAge(short age) {
        this.age = age;
    }

    public String getOthers() {
        return others;
    }

    public void setOthers(String others) {
        this.others = others;
    }

    public User(short uid, String uname, String location, short age, String others) {
        this.uid = uid;
        this.uname = uname;
        this.location = location;
        this.age = age;
        this.others = others;
    }
}

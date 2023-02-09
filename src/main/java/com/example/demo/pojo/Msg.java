package com.example.demo.pojo;

import org.springframework.stereotype.Component;

import java.util.Date;

//用户或律师提出
@Component
public class Msg {
    private short mid;
    private short uid;
    private short lid;
    private String others;
    private Date date;
    private short status;//0为未处理，1为已处理

    public short getMid() {
        return mid;
    }

    public void setMid(short mid) {
        this.mid = mid;
    }

    public short getUid() {
        return uid;
    }

    public void setUid(short uid) {
        this.uid = uid;
    }

    public short getLid() {
        return lid;
    }

    public void setLid(short lid) {
        this.lid = lid;
    }

    public String getOthers() {
        return others;
    }

    public void setOthers(String others) {
        this.others = others;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public short getStatus() {
        return status;
    }

    public void setStatus(short status) {
        this.status = status;
    }
}

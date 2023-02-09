package com.example.demo.pojo;

import org.springframework.stereotype.Component;

@Component
public class Contract {
    private short cid;
    private short uid;
    private short lid;
    private short status;//0为未签署，1为已签署并预支付，2为已完成
    private double price;
    private String others;

    public short getCid() {
        return cid;
    }

    public void setCid(short cid) {
        this.cid = cid;
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

    public short getStatus() {
        return status;
    }

    public void setStatus(short status) {
        this.status = status;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getOthers() {
        return others;
    }

    public void setOthers(String others) {
        this.others = others;
    }


}

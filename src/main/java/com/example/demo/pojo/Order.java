package com.example.demo.pojo;

import org.springframework.stereotype.Component;

import java.sql.Date;
@Component
public class Order {
    private short oid;
    private short uid;
    private short lid;
    private Date date;
    private short status;//0为提出未接受，1为已接受，2为受理中，3为已完结
    private short cid;
    private String others;

    public short getOid() {
        return oid;
    }

    public void setOid(short oid) {
        this.oid = oid;
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

    public short getCid() {
        return cid;
    }

    public void setCid(short cid) {
        this.cid = cid;
    }

    public String getOthers() {
        return others;
    }

    public void setOthers(String others) {
        this.others = others;
    }

    public Order(short oid, short uid, short lid, Date date, short status, short cid, String others) {
        this.oid = oid;
        this.uid = uid;
        this.lid = lid;
        this.date = date;
        this.status = status;
        this.cid = cid;
        this.others = others;
    }


}

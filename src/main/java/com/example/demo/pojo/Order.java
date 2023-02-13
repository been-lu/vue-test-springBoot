package com.example.demo.pojo;

import lombok.Data;

import java.sql.Date;

@Data
public class Order {
    private Integer oid;
    private Integer  uid;
    private Integer lid;
    private Date date;
    private short status;//0为提出未接受，1为已接受，2为受理中，3为已完结
    private Integer cid;
    private String others;





}

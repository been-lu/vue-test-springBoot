package com.example.demo.pojo;

import lombok.Data;


@Data
public class Contract {
    private Integer cid;
    private Integer uid;
    private Integer lid;
    private short status;//0为未签署，1为已签署并预支付，2为已完成
    private double price;
    private String others;




}

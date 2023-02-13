package com.example.demo.pojo;

import lombok.Data;

@Data
public class Lawyer {
    private Integer lid;
    private String lname;
    private short age;
    private String location;
    private short status;//0为未注册，1为经管理员审核注册
    private String others;


}

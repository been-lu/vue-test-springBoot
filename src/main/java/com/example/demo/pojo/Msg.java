package com.example.demo.pojo;

import lombok.Data;

import java.util.Date;

//用户或律师提出
@Data
public class Msg {
    private Integer mid;
    private Integer uid;
    private Integer lid;
    private String others;
    private Date date;
    private short status;//0为未处理，1为已处理

}

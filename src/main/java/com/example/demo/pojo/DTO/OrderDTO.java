package com.example.demo.pojo.DTO;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class OrderDTO {
    private Long oid;
    private Long  uid;
    private Long lid;
    private Timestamp date;
    private Integer status;//0为提出未接受，1为已接受，2为受理中，3为已完结
    private Integer cid;
    private String others;
}

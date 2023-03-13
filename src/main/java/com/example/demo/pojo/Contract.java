package com.example.demo.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;


@Data
@TableName("contract")
public class Contract {
    @TableId(value = "cid")
    private Long cid;
    private Long oid;
    private Integer status;//0为未签署，1为已签署并预支付，2为已完成
    private double price;
    private String others;


}

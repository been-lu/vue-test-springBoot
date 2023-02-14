package com.example.demo.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;


@Data
@TableName("contract")
public class Contract {
    @TableId(value = "cid",type = IdType.AUTO)
    private Integer cid;
    private Integer uid;
    private Integer lid;
    private Integer status;//0为未签署，1为已签署并预支付，2为已完成
    private double price;
    private String others;




}

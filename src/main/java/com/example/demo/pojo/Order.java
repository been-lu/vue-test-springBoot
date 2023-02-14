package com.example.demo.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.sql.Date;

@Data
@TableName("order")
public class Order {
    @TableId(value = "oid",type = IdType.AUTO)
    private Integer oid;
    private Integer  uid;
    private Integer lid;
    private Date date;
    private Integer status;//0为提出未接受，1为已接受，2为受理中，3为已完结
    private Integer cid;
    private String others;





}

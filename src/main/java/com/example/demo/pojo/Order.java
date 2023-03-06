package com.example.demo.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.sql.Timestamp;

@Data
@TableName("affair")
public class Order {
    @TableId(type = IdType.ASSIGN_ID)
    private Long oid;
    private Long uid;
    private Long lid;
    private Timestamp date;
    private Integer status;//0为提出未接受，1为已接受，2为受理中，3为已完结
    private Long cid;
    private String others;





}

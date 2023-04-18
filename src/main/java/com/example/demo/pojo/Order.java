package com.example.demo.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@TableName("affair")
@AllArgsConstructor
@NoArgsConstructor
/**
 *  预约
 *  订单
 */
public class Order {
    @TableId(type = IdType.ASSIGN_ID)
    private Long oid;
    private Long uid;
    private Long lid;
    private Timestamp date;

    /**
     *0为提出未接受，1为已接受，2为受理中，3为已完结
     */
    private String status;

    private Long cid;
    private String others;
    private double price;
    private String description;





}

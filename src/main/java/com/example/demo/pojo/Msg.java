package com.example.demo.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

//用户或律师提出
@Data
@TableName("msg")
public class Msg {
    @TableId(value = "mid")
    private Long mid;
    private Long uid;
    private Long lid;
    private String others;
    private Date date;
    private Integer status;//0为未处理，1为已处理

}

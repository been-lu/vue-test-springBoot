package com.example.demo.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("lawyer")
public class Lawyer {
    @TableId(value = "lid",type = IdType.AUTO)
    private Integer lid;
    private String lname;
    private Integer age;
    private String location;
    private Integer status;//0为未注册，1为经管理员审核注册
    private String others;


}

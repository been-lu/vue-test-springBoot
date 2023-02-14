package com.example.demo.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;


@Data
@TableName("user")
public class User {
    @TableId(value = "uid",type = IdType.AUTO)
    //mybatis-plus使用雪花算法生成id
    private Integer uid;
    private String uname;
    private String location;
    private Integer age;
    private String others;
    private String pwd;
    private String email;



}

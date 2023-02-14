package com.example.demo.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;


@Data
@TableName("user")
public class User {
    @TableId
    private Integer uid;
    private String uname;
    private String location;
    private short age;
    private String others;
    private String pwd;



}

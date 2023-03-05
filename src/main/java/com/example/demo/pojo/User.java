package com.example.demo.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;


@Data
@TableName("user")
public class User {
    @TableId(type = IdType.ASSIGN_ID)
    private Long uid;
    private String uname;
    private String location;
    private Integer age;
    private String others;
    private String pwd;
    private String email;

}

package com.example.demo.pojo;

import lombok.Data;

@Data
public class UserType {
    /**
     * user 普通用户
     * lawyer 律师用户
     * admin 管理员
     */
    private String userType;

    private Long id;


}

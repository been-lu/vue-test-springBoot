package com.example.demo.pojo.DTO;

import lombok.Data;

@Data
public class LawyerDTO {
    private Long lid;
    private String lname;
    private String location;
    private Integer status; //0为未注册，1为经管理员审核注册
    private String others;
    private String email;
    private String pwd;
    private String token;
}

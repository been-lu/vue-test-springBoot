package com.example.demo.pojo.DTO;

import lombok.Data;

@Data
public class UserDTO {
    private String email;
    private String pwd;
    private Long uid;
    private String uname;
    private String token;
}

package com.example.demo.pojo.DTO;

import com.sun.corba.se.impl.naming.namingutil.CorbanameURL;
import lombok.Data;

@Data
public class AdminDTO {
    private Long aid;
    private String aname;
    private String pwd;
    private String others;

}

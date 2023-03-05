package com.example.demo.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("admin")
public class Administrator{
    @TableId(type = IdType.ASSIGN_ID)
    private Long aid;
    private String aname;
    private String pwd;
    private String others;
}

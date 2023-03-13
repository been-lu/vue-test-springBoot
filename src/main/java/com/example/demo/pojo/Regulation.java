package com.example.demo.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("regulation")
public class Regulation {
    //法律条款
    @TableId(type = IdType.ASSIGN_ID)
    private Long did;
    private String description;
}

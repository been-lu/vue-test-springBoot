package com.example.demo.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

//用户或律师提出
@Data
@TableName("msg")
@NoArgsConstructor
@AllArgsConstructor
public class Msg {
    @TableId(value = "mid")
    private Long mid;
    private Long uid;
    private Long lid;
    private String others;
    private Date date;

    /**
     * 已受理，未受理。
     */
    private String status;

    private String description;


}

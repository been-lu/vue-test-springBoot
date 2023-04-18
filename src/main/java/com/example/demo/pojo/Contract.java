package com.example.demo.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;


@Data
@TableName("contract")
@AllArgsConstructor
@NoArgsConstructor
/**
 * 合同表
 */
public class Contract {
    @TableId(value = "cid")
    private Long cid;
    private Long oid;
    private Integer status;//0为未签署，1为已签署并预支付，2为已完成
    private double price;
    private String others;


    public Contract(Contract contract) {
        if (Objects.nonNull(contract)) {
            setCid(contract.getCid());
            setOid(contract.getOid());
            setOthers(contract.getOthers());
            setPrice(contract.getPrice());
            setStatus(contract.getStatus());
        }

    }

}

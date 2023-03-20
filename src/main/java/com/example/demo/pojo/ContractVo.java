package com.example.demo.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ContractVo extends Contract{

    private Long uid;
    private String uname;
    private Long lid;
    private String lname;

    public ContractVo(Contract contract){
        super(contract);
    }
}

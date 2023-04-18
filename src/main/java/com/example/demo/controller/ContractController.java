package com.example.demo.controller;

import com.example.demo.common.Result;
import com.example.demo.pojo.UserType;
import com.example.demo.service.ContractService;
import com.example.demo.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/contract")
public class ContractController {

    @Autowired
    ContractService contractService;

    @GetMapping("/page")
    public Result findPage(@RequestParam Integer pageNum, @RequestParam Integer pageSize,
                           @RequestParam(defaultValue = "") String status) {
        UserType userType = TokenUtils.getCurrentUser();
        //准备在下一层实现
        return Result.success(contractService.pageByUserType(pageNum, pageSize, userType));


    }

}

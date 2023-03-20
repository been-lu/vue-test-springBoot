package com.example.demo.service;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.mapper.ContractMapper;
import com.example.demo.pojo.Contract;
import com.example.demo.pojo.ContractVo;
import com.example.demo.pojo.Order;
import com.example.demo.pojo.UserType;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Log4j2
public class ContractService extends ServiceImpl<ContractMapper, Contract> {

    @Autowired
    ContractMapper contractMapper;

    @Autowired
    OrderService orderService;


    public IPage<ContractVo> pageByUserType(Integer pageNum, Integer pageSize, UserType userType) {
        IPage<Contract> page = new Page<>(pageNum, pageSize);
        IPage<Contract> contractIPage = this.page(page);
        Page<ContractVo> contractVoIPage = new Page<>();
        BeanUtil.copyProperties(contractIPage, contractVoIPage, true);


        QueryWrapper<Order> orderQueryWrapper = new QueryWrapper<>();

        if (userType.getUserType().equals("lawyer")) {
            orderQueryWrapper.eq("lid", userType.getId());
        }
        if (userType.getUserType().equals("user")) {
            orderQueryWrapper.eq("uid", userType.getId());
        }
        List<Order> orderList = orderService.list(orderQueryWrapper);
        Map<Long, Order> map = new HashMap<>();
        for (Order order : orderList) {
            map.put(order.getOid(), order);
        }
        for (ContractVo contractVo : contractVoIPage.getRecords()) {
            Order order = map.get(contractVo.getOid());
            contractVo.setUid(order.getUid());
            contractVo.setLid(order.getLid());
        }


        return contractVoIPage;

    }


}

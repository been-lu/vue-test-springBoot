package com.example.demo.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.common.Constants;
import com.example.demo.common.Result;
import com.example.demo.pojo.DTO.OrderDTO;
import com.example.demo.pojo.Order;
import com.example.demo.pojo.UserType;
import com.example.demo.service.OrderService;
import com.example.demo.utils.TokenUtils;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    OrderService orderService;

    //分页查询
    @GetMapping("/page")
    public Result findPage(@RequestParam Integer pageNum, @RequestParam Integer pageSize,
                           @RequestParam(defaultValue = "") String uid,
                           @RequestParam(defaultValue = "") String lid,
                           @RequestParam(defaultValue = "") String status) {
        //权限校验
        UserType userType= TokenUtils.getCurrentUser();
        IPage<Order> page = new Page<>(pageNum, pageSize);
        QueryWrapper<Order> queryWrapper = new QueryWrapper<>();
        if(userType.getUserType().equals("admin")){
            ;
        }
        if(userType.getUserType().equals("user")){
            queryWrapper.eq("uid", userType.getId());
        }
        if(userType.getUserType().equals("lawyer")){
            queryWrapper.eq("lid", userType.getId());
        }
        queryWrapper.orderByDesc("oid");
        if (!"".equals(uid)) {
            queryWrapper.like("uid", uid);
        }
        if (!"".equals(lid)) {
            queryWrapper.like("lid", lid);
        }
        if (!"".equals(status)) {
            queryWrapper.like("status", Integer.parseInt(status));
        }

        return Result.success(orderService.page(page, queryWrapper));
    }

    @PostMapping("/saveOrUpdate")
    public Result saveOrUpdate(@NotNull @RequestBody Order order){
        //权限校验
        UserType userType= TokenUtils.getCurrentUser();
        if (order.getUid()==null){
            order.setUid(userType.getId());
        }
        if(order.getOid()==null){
            orderService.save(order);
        }
        else{
            orderService.updateById(order);
        }
        return Result.success();

    }


}

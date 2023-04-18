package com.example.demo.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.common.Constants;
import com.example.demo.common.Result;
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


    /**
     * 预约记录的查询
     *
     * @param pageNum
     * @param pageSize
     * @param uid
     * @param lid
     * @param status
     * @return
     */
    @GetMapping("/page")
    public Result findPage(@RequestParam Integer pageNum, @RequestParam Integer pageSize,
                           @RequestParam(defaultValue = "") String uid,
                           @RequestParam(defaultValue = "") String lid,
                           @RequestParam(defaultValue = "") String status) {
        //权限校验
        UserType userType = TokenUtils.getCurrentUser();
        IPage<Order> page = new Page<>(pageNum, pageSize);
        QueryWrapper<Order> queryWrapper = new QueryWrapper<>();
        //这里后续记得改
//        if(userType.getUserType().equals("admin")){
//            ;
//        }
        if (userType.getUserType().equals("user")) {
            queryWrapper.eq("uid", userType.getId());
        }
        if (userType.getUserType().equals("lawyer")) {
            queryWrapper.eq("lid", userType.getId());
        }
        //
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

    /**
     * @param order
     * @return
     */
    @PostMapping("/saveOrUpdate")
    public Result saveOrUpdate(@NotNull @RequestBody Order order) {
        //权限校验
        UserType userType = TokenUtils.getCurrentUser();


        if (userType.getUserType().equals("user")) {
            if (order.getOid() == null) {
                if (order.getUid() == null) {
                    order.setUid(userType.getId());
                }
                order.setStatus("未接受");
                orderService.save(order);
            }
            Order one = orderService.getById(order.getOid());
            if (!one.getStatus().equals(order.getStatus())) {
                return Result.error(Constants.CODE_400, "不合法的修改");
            }
            return Result.success();
        }

        if (userType.getUserType().equals("lawyer")) {
            if (order.getLid() == null) {
                order.setLid(userType.getId());
                orderService.save(order);
            } else {
                orderService.updateById(order);
            }
            return Result.success();
        }

        if (userType.getUserType().equals("admin")) {
            orderService.saveOrUpdate(order);
            return Result.success();
        }

        return Result.success();

    }


}

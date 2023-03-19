package com.example.demo.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.common.Constants;
import com.example.demo.common.Result;
import com.example.demo.pojo.Msg;
import com.example.demo.pojo.UserType;
import com.example.demo.service.MsgService;
import com.example.demo.utils.TokenUtils;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/msg")
public class MsgController {
    @Autowired
    MsgService msgService;

    @GetMapping("/page")
    public Result findPage(@RequestParam Integer pageNum, @RequestParam Integer pageSize){
        UserType userType= TokenUtils.getCurrentUser();
        IPage<Msg> page=new Page<>(pageNum,pageSize);
        QueryWrapper<Msg> queryWrapper=new QueryWrapper<>();
        queryWrapper.orderByDesc("mid");
        if(userType.getUserType().equals("user")){
            queryWrapper.eq("uid", userType.getId());
        }
        if (userType.getUserType().equals("lawyer")){
            queryWrapper.eq("lid", userType.getId());
        }
        return Result.success(msgService.page(page, queryWrapper));
    }


    @PostMapping("/save")
    public Result save(@NotNull @RequestBody Msg msg){
        UserType userType= TokenUtils.getCurrentUser();
        if(userType.getUserType().equals("user")){
            msg.setUid(userType.getId());
        }
        if (userType.getUserType().equals("lawyer")){
            msg.setLid(userType.getId());
        }
        msgService.save(msg);
        return Result.success();
    }

}

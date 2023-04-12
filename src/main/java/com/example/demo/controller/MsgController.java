package com.example.demo.controller;

import cn.hutool.core.util.StrUtil;
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


    /**
     * 改接口对所有用户开放，但根据用户类别不同，返回数据不同
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("/page")
    public Result findPage(@RequestParam Integer pageNum, @RequestParam Integer pageSize,
                           @RequestParam Boolean findAll) {
        UserType userType = TokenUtils.getCurrentUser();
        IPage<Msg> page = new Page<>(pageNum, pageSize);
        QueryWrapper<Msg> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("mid");
        if (userType.getUserType().equals("user")) {
            queryWrapper.eq("uid", userType.getId());
        }
        if (userType.getUserType().equals("lawyer")) {
            queryWrapper.eq("lid", userType.getId());
        }
        if (!findAll) {
            queryWrapper.eq("status", "未受理");
        }
        return Result.success(msgService.page(page, queryWrapper));
    }

    /**
     * 该功能只针对律师以及用户开放
     *
     * @param msg
     * @return
     */
    @PostMapping("/save")
    public Result save(@NotNull @RequestBody Msg msg) {
        UserType userType = TokenUtils.getCurrentUser();
        if (userType.getUserType().equals("user")) {
            msg.setUid(userType.getId());
        }
        if (userType.getUserType().equals("lawyer")) {
            msg.setLid(userType.getId());
        }
        msg.setStatus("未受理");
        msgService.save(msg);
        return Result.success();
    }

    /**
     * 该功能只对管理员开放，在管理员处理完请求后管理员执行
     *
     * @param msg
     * @return
     */
    @PostMapping("/update")
    public Result update(@NotNull @RequestBody Msg msg) {
        UserType userType = TokenUtils.getCurrentUser();
        if (!userType.getUserType().equals("admin")) {
            return Result.error(Constants.CODE_401, "权限异常");
        }
        if (msg.getMid() == null) {
            return Result.error(Constants.CODE_600, "业务异常");
        }

        msg.setStatus("已受理");


        msgService.updateById(msg);
        return Result.success();
    }

    @PostMapping("/check")
    public Result check(Long mid) {
        UserType userType = TokenUtils.getCurrentUser();
        if (!userType.getUserType().equals("admin")) {
            return Result.error(Constants.CODE_401, "权限异常");
        }
        if (mid == null) {
            return Result.error(Constants.CODE_401, "参数错误");
        }
        //undone!!!
        return Result.success();

    }
}

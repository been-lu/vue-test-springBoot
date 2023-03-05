package com.example.demo.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.log.Log;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.common.Constants;
import com.example.demo.common.Result;
import com.example.demo.exception.ServiceException;
import com.example.demo.mapper.UserMapper;
import com.example.demo.pojo.DTO.UserDTO;
import com.example.demo.pojo.Lawyer;
import com.example.demo.pojo.User;
import com.example.demo.pojo.UserType;
import com.example.demo.service.UserService;
import com.example.demo.utils.TokenUtils;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserMapper userMapper;

    @Autowired
    UserService userService;

    @GetMapping("/findAll")
    public List<User> index() {
        return userMapper.selectList(null);
    }

    //前端记得要一定要有邮箱
    @PostMapping("/saveOrUpdate")
    public Result saveOrUpdate(@NotNull @RequestBody User user) {
        //add or update
        if (user.getUid() != null) {
            userService.updateById(user);
        } else {
            QueryWrapper<User> queryWrapper=new QueryWrapper<>();
            queryWrapper.eq("email", user.getEmail());
            if(!userService.list(queryWrapper).isEmpty()){
                userService.update(user, queryWrapper);
            }
            else{
                if(user.getPwd()==null){
                    user.setPwd("123456");
                }
                userService.save(user);
            }
        }
        return Result.success();
    }

    @PostMapping("/login")
    public Result login(@RequestBody UserDTO userDTO) {
        String email = userDTO.getEmail();
        String pwd = userDTO.getPwd();
        if (StrUtil.isBlank(email) || StrUtil.isBlank(pwd))
            return Result.error(Constants.CODE_400,"参数错误");
        return Result.success(userService.login(userDTO));
    }


    @PostMapping("/register")
    public Result register(@RequestBody UserDTO userDTO) {
        User user=new User();
        BeanUtil.copyProperties(userDTO, user, true);
        user.setUname("null");
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("email", user.getEmail());
        if (!userService.list(queryWrapper).isEmpty()) {
            return Result.error(Constants.CODE_600, "信箱已注册");
        } else {
            userService.save(user);

            return Result.success();
        }

    }
    //分页查询
    @GetMapping("/page")
    public Result findPage(@RequestParam Integer pageNum,
                                @RequestParam Integer pageSize,
                                @RequestParam(defaultValue = "") String uname,
                                @RequestParam(defaultValue = "") String email,
                                @RequestParam(defaultValue = "") String location) {
        IPage<User> page = new Page<>(pageNum, pageSize);
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("uid");
        if (!"".equals(uname)) {
            queryWrapper.like("uname", uname);
        }
        if (!"".equals(email)) {
            queryWrapper.like("email", email);
        }
        if (!"".equals(location)) {
            queryWrapper.like("location", location);
        }
        UserType userType= TokenUtils.getCurrentUser();
        System.out.println("当前用户信息======\n"+userType+"======\n");

        return Result.success(userService.page(page, queryWrapper));
    }


    @PostMapping("/addUser")
    private Boolean addUser(@RequestBody User user) {
        user.setPwd("123456");
        return userService.signin(user);
    }
}

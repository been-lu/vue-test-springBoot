package com.example.demo.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.mapper.UserMapper;
import com.example.demo.pojo.DTO.UserDTO;
import com.example.demo.pojo.User;
import com.example.demo.service.UserService;
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
    public Boolean saveOrUpdate(@NotNull @RequestBody User user) {
        //add or update
        if (user.getUid() == null) {
//            Boolean res=userService.signin(user);
//            if(!res){//登陆失败，同一邮箱不允许多个账户
//                QueryWrapper<User> queryWrapper=new QueryWrapper<>();
//                queryWrapper.eq("email", user.getEmail());
//                userMapper.update(user,queryWrapper);
//                return true;
//            }
//            else{
//                return false;
//            }
            user.setPwd("123456");
            return userService.save(user);
        } else {
            return userService.updateById(user);
        }

    }

    @PostMapping("/login")
    public boolean login(@RequestBody UserDTO userDTO) {
        String email = userDTO.getEmail();
        String pwd = userDTO.getPwd();
        if (StrUtil.isBlank(email) || StrUtil.isBlank(pwd))
            return false;
        return userService.login(userDTO);
    }

    //分页查询
    @GetMapping("/page")
    public IPage<User> findPage(@RequestParam Integer pageNum,
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
        return userService.page(page, queryWrapper);
    }


    @PostMapping("/addUser")
    private Boolean addUser(@RequestBody User user) {
        user.setPwd("123456");
        return userService.signin(user);
    }
}

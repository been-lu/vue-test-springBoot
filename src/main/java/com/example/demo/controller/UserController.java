package com.example.demo.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.mapper.UserMapper;
import com.example.demo.pojo.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserMapper userMapper;

    @Autowired
    UserService userService;

    @GetMapping("/findAll")
    public List<User> index(){
        return userMapper.selectList(null);
    }

    //前端记得要一定要有邮箱
    @PostMapping("/saveOrUpdate")
    public Integer saveOrUpdate(@RequestBody User user){
        //add or update
        if (user.getUid()==null) {
            Map<Boolean ,String> map=userService.signin(user);
            if(map.containsKey(false)){//登陆失败，同一邮箱不允许多个账户
                QueryWrapper<User> queryWrapper=new QueryWrapper<>();
                queryWrapper.eq("email", user.getEmail());
                return userMapper.update(user,queryWrapper);
            }
            else{
                return 0;
            }
        }
        else{
             userMapper.updateById(user);
             return 1;
        }

    }

    @RequestMapping("/login")
    public Integer login(@RequestBody User user){
        if (userService.login(user)){
            return 1;
        }
        else
            return 0;
    }

    //分页查询
    @GetMapping("/page")
    public IPage<User> findPage(@RequestParam Integer pageNum,
                          @RequestParam Integer pageSize,
                          @RequestParam(defaultValue = "") String uname,
                          @RequestParam(defaultValue = "") String email,
                          @RequestParam(defaultValue = "") String location){

        IPage<User> page =new Page<>(pageNum,pageSize);


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
        return userService.page(page,queryWrapper);
    }
}

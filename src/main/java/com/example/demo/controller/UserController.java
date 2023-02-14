package com.example.demo.controller;

import com.example.demo.mapper.UserMapper;
import com.example.demo.pojo.User;
import com.example.demo.service.UserService;
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
    public List<User> index(){
        return userMapper.selectList(null);
    }

    @PostMapping
    public Integer save(@RequestBody User user){
        //add or update
        return userService.save(user);
    }
}

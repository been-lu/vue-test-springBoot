package com.example.demo.controller;

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
    public Integer save(@RequestBody User user){
        //add or update
        return userService.save(user);
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
    public Map<String,Object> findPage(@RequestParam Integer pageNum,
                                       @RequestParam Integer pageSize,
                                       @RequestParam String uname){
        Map<String,Object> res=new HashMap<>();
        res.put("total", userService.findCount(uname));
        res.put("data",  userService.findByPage(pageNum, pageSize,uname));

        return res;
    }
}

package com.example.demo.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.mapper.UserMapper;
import com.example.demo.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserService extends ServiceImpl<UserMapper,User> {
    @Autowired
    UserMapper userMapper;

    /*
    *列举一下要实现的功能
    * 用户注册
    * 用户信息修改
    * 用户登录
    *   用户登录应使用邮箱与密码
    *
    * */

//    public boolean save(User user){
//        if(user.getUid()==null){
//            return userMapper.insert(user);
//        }
//        else{
//            return userMapper.updateById(user);
//        }
//    }

    //默认前端传来用户名与密码
    public boolean login(User user){
        Map<String,Object> map=new HashMap<>();
        map.put("email", user.getEmail());
        map.put("pwd", user.getPwd());
        if(userMapper.selectByMap(map).isEmpty()){
            return false;
        }else{
            return true;
        }
    }

    //用户注册
    public Boolean signin(User user){
        Map<String,Object> map=new HashMap<>();
        map.put("email", user.getEmail());
        if(userMapper.selectByMap(map).isEmpty()){
            userMapper.insert(user);
            return true;
        }
        else{
            return false;
        }

    }

    public void update(User user){
        //不允许修改邮箱
        user.setEmail(null);
        userMapper.updateById(user);
    }

    //分页查询
    public List<User> findByPage(Integer pageNum,Integer pageSize,String uname){
        Page<User> page=new Page<>(pageNum,pageSize);
        QueryWrapper<User>queryWrapper=new QueryWrapper<>();
        if(!"".equals(uname)){
            queryWrapper.like("uname", uname);
        }
        Page<User> res=userMapper.selectPage(page, queryWrapper);
        return res.getRecords();


    }

    public Long findCount(String uname){
        QueryWrapper<User>queryWrapper=new QueryWrapper<>();
        if(!"".equals(uname)){
            queryWrapper.like("uname", uname);
        }
        return userMapper.selectCount(queryWrapper).longValue();
    }
}

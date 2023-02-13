package com.example.demo.service;

import com.example.demo.mapper.UserMapper;
import com.example.demo.pojo.User;
import org.apache.ibatis.javassist.compiler.ast.ASTree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserMapper userMapper;

    public int save(User user){
        if(user.getUid()==null){
            return userMapper.insert(user);
        }
        else{
            return userMapper.update(user);
        }

    }
}

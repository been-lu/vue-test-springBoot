package com.example.demo;

import com.example.demo.mapper.UserMapper;
import com.example.demo.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
public class MybatisPlusTest {
    @Autowired
    UserMapper userMapper;

    @Test
    public void testSelectList(){
        List<User> list= userMapper.selectList(null);
        list.forEach(System.out::println);
    }

    @Test
    public void testInsertUser(){
        // INSERT INTO user ( uid, uname, age, pwd ) VALUES ( ?, ?, ?, ? )
        User user=new User();
        user.setUname("wangwu");
        user.setAge(23);
        user.setPwd("qwerty");

        user.setEmail("123@qq.com");
        System.out.println(user.getUid());
        System.out.println( "result: "+ userMapper.insert(user));
        System.out.println("uid: "+ user.getUid());

    }

    @Test
    public void testDel(){
        System.out.print("result: ");
//        System.out.println(userMapper.deleteById(2098647044));
        Map<String,Object> map=new HashMap<>();
        map.put("email", "122@qq.com");
        System.out.println(userMapper.deleteByMap(map));

    }
}

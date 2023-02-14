package com.example.demo;

import com.example.demo.mapper.UserMapper;
import com.example.demo.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class MybatisPlusTest {
    @Autowired
    UserMapper userMapper;

    @Test
    public void testSelectList(){
        List<User> list= userMapper.selectList(null);
        list.forEach(System.out::println);
    }
}

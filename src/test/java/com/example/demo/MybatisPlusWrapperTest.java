package com.example.demo;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.mapper.UserMapper;
import com.example.demo.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tk.mybatis.spring.annotation.MapperScan;

import java.util.List;

@SpringBootTest
public class MybatisPlusWrapperTest {
    @Autowired
    private UserMapper userMapper;

    @Test
    public void test(){
        QueryWrapper<User> queryWrapper=new QueryWrapper<>();
        queryWrapper.like("uname", "a").
                between("age", 20, 26);
        List<User> list=userMapper.selectList(queryWrapper);
        list.forEach(System.out::println);
    }
}

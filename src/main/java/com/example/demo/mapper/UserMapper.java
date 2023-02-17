package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.pojo.User;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;


@Repository

public interface UserMapper extends BaseMapper<User> {

}

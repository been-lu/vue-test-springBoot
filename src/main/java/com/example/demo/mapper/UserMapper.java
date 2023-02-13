package com.example.demo.mapper;

import com.example.demo.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserMapper {
    @Select("select * from user")
    List<User> findAll();

    @Insert("insert into user (uname,pwd,age,location,others)\n" +
            "values (#{uname},#{pwd},#{age},#{location},#{others})")
    int insert(User user);


    int update(User user);

}

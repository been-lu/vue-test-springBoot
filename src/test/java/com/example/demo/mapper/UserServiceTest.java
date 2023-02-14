package com.example.demo.mapper;

import com.example.demo.pojo.User;
import com.example.demo.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    UserService userService;

    @Test
    public void testLogin(){
        User user=new User();
        user.setEmail("五xyza@qq.com");
        user.setPwd("qwerty");
        boolean res=false;
        try{
            res=userService.login(user);
            System.out.println("res: "+ res);

        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {

        }
    }
    
    @Test
    public void testSignin(){
        User user=new User();
        user.setUname("孙悟空");
        user.setAge(23);
        user.setPwd("qwerty");
        user.setEmail("五xyza@qq.com");
        Map<Boolean,String> res;
        res=userService.signin(user);
        if(res.containsKey(true)){
            System.out.println("success!");
        }
        else{
            System.out.println(res.get(false));
        }
        
    }
    @Test
    public void testUpdate(){
        //记得邮箱不允许修改
        User user=new User();
        user.setUid(1625468622041653250L);
        user.setLocation("tianjin");
        userService.update(user);
    }
}

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
        user.setEmail("1222@qq.com");
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
        user.setUname("李林");
        user.setAge(23);
        user.setPwd("qwerty");
        user.setEmail("1234@qq.com");
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
        User user=new User();
        user.setUid(2098647050);
        user.setEmail("12354@qq.com");
        userService.update(user);
    }
}

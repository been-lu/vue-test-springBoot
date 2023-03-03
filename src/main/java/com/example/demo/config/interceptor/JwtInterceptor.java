package com.example.demo.config.interceptor;

import cn.hutool.core.util.StrUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.example.demo.common.Constants;
import com.example.demo.exception.ServiceException;
import com.example.demo.pojo.Administrator;
import com.example.demo.pojo.Lawyer;
import com.example.demo.pojo.User;
import com.example.demo.service.AdministratorService;
import com.example.demo.service.LawyerService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class JwtInterceptor implements HandlerInterceptor {
    @Autowired
    UserService userService;

    @Autowired
    LawyerService lawyerService;

    @Autowired
    AdministratorService administratorService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String token = request.getHeader("token");
        // 如果不是映射到方法直接通过
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        if (StrUtil.isBlank(token)) {
            throw new ServiceException(Constants.CODE_401, "无token，权限异常");
        }
        //获取token中的用户id
        String id;
        try {
            id = JWT.decode(token).getAudience().get(0);
        } catch (JWTDecodeException j) {
            throw new ServiceException(Constants.CODE_401, "token验证失败，请重新登录");
        }
        //根据id查询数据库
        User user = userService.getById(id);
        Lawyer lawyer = lawyerService.getById(id);
        Administrator administrator = administratorService.getById(id);
        JWTVerifier jwtVerifier;
        if (user == null) {
            if (lawyer == null) {
                if (administrator == null) {
                    throw new ServiceException(Constants.CODE_401, "用户验证失败，请重新登录");
                } else {
                    jwtVerifier = JWT.require(Algorithm.HMAC256(administrator.getPwd())).build();
                }
            } else {
                jwtVerifier = JWT.require(Algorithm.HMAC256(lawyer.getPwd())).build();
            }

        } else {
            jwtVerifier = JWT.require(Algorithm.HMAC256(user.getPwd())).build();
        }
        //
        // 用户密码加签验证 token

        try {
            jwtVerifier.verify(token); // 验证token
        } catch (JWTVerificationException e) {
            throw new ServiceException(Constants.CODE_401, "token验证失败，请重新登录");
        }

        return true;
    }
}

package com.example.demo.utils;

import cn.hutool.core.date.DateUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import java.util.Date;

public class TokenUtils {

    /**
     *
     * @param id
     * @param pwd
     * @return
     */
    public static String genToken(String id,String pwd){
        return JWT.create().withAudience(id) // 将 user id 保存到 token 里面,作为载荷
                .withExpiresAt(DateUtil.offsetHour(new Date(), 1)) // 1小时后token过期
                .sign(Algorithm.HMAC256(pwd)); // 以 password 作为 token 的密钥
    }
}

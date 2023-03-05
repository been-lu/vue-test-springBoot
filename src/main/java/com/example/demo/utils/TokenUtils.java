package com.example.demo.utils;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.demo.pojo.Administrator;
import com.example.demo.pojo.Lawyer;
import com.example.demo.pojo.UserType;
import com.example.demo.pojo.User;
import com.example.demo.service.AdministratorService;
import com.example.demo.service.LawyerService;
import com.example.demo.service.UserService;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Component
public class TokenUtils {

    private static UserService staticUserService;
    private static LawyerService staticLawyerService;
    private static AdministratorService staticAdministratorService;

    @Resource
    private UserService userService;
    @Resource
    private LawyerService lawyerService;
    @Resource
    private AdministratorService administratorService;

    @PostConstruct
    public void setServie() {
        staticUserService = userService;
        staticLawyerService = lawyerService;
        staticAdministratorService = administratorService;
    }

    /**
     * @param id
     * @param pwd
     * @return
     */
    public static String genToken(String id, String pwd) {
        return JWT.create().withAudience(id) // 将 user id 保存到 token 里面,作为载荷
                .withExpiresAt(DateUtil.offsetHour(new Date(), 1)) // 1小时后token过期
                .sign(Algorithm.HMAC256(pwd)); // 以 password 作为 token 的密钥
    }

    /**
     * 获取当前用户信息
     *
     * @return
     */
    public static UserType getCurrentUser() {
        try {
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            String token = request.getHeader("token");
            if (StrUtil.isNotBlank(token)) {
                UserType userType = new UserType();
                String id = JWT.decode(token).getAudience().get(0);
                userType.setId(Long.valueOf(id));
                User user = staticUserService.getById(Long.valueOf(id));
                Lawyer lawyer = staticLawyerService.getById(Long.valueOf(id));
                Administrator administrator = staticAdministratorService.getById(Long.valueOf(id));
                if (user != null) {
                    userType.setUserType("user");
                }
                if (lawyer != null) {
                    userType.setUserType("lawyer");
                }
                if (administrator != null) {
                    userType.setUserType("admin");
                }

                return userType;
            }
        } catch (Exception e) {
            return null;
        }
        return null;
    }
}

package com.example.demo.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.common.Constants;
import com.example.demo.common.Result;
import com.example.demo.pojo.Administrator;
import com.example.demo.pojo.DTO.AdminDTO;
import com.example.demo.service.AdministratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdministratorService administratorService;

    @PostMapping("/saveOrUpdate")
    public Boolean saveOrUpdate(@RequestBody Administrator administrator) {
        if (administrator.getAid() != null) {
            return administratorService.updateById(administrator);
        } else {
            QueryWrapper<Administrator> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("aname", administrator.getAname());
            if (!administratorService.list(queryWrapper).isEmpty()) {
                return administratorService.update(administrator,queryWrapper);
            } else {
                return administratorService.save(administrator);
            }
        }
    }

    @PostMapping("/login")
    public Result login (@RequestBody AdminDTO adminDTO){
        String aname = adminDTO.getAname();
        String pwd = adminDTO.getPwd();
        if (StrUtil.isBlank(aname) || StrUtil.isBlank(pwd))
            return Result.error(Constants.CODE_400,"参数错误");
        return Result.success(administratorService.login(adminDTO));
    }

}

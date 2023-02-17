package com.example.demo.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.pojo.Administrator;
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


}

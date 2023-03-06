package com.example.demo.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.common.Constants;
import com.example.demo.common.Result;
import com.example.demo.pojo.Administrator;
import com.example.demo.pojo.DTO.AdminDTO;
import com.example.demo.pojo.UserType;
import com.example.demo.service.AdministratorService;
import com.example.demo.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdministratorService administratorService;

    @PostMapping("/saveOrUpdate")
    public Result saveOrUpdate(@RequestBody Administrator administrator) {
        if (administrator.getAid() != null) {
            administratorService.updateById(administrator);
        } else {
            QueryWrapper<Administrator> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("aname", administrator.getAname());
            if (!administratorService.list(queryWrapper).isEmpty()) {
                administratorService.update(administrator, queryWrapper);
            } else {
                if (administrator.getPwd() == null)
                    administrator.setPwd("123456");
                administratorService.save(administrator);
            }
        }
        return Result.success();
    }

    @PostMapping("/login")
    public Result login(@RequestBody AdminDTO adminDTO) {
        String aname = adminDTO.getAname();
        String pwd = adminDTO.getPwd();
        if (StrUtil.isBlank(aname) || StrUtil.isBlank(pwd))
            return Result.error(Constants.CODE_400, "参数错误");
        return Result.success(administratorService.login(adminDTO));
    }

    //分页查询
    @GetMapping("/page")
    public Result findPage(@RequestParam Integer pageNum, @RequestParam Integer pageSize) {
        //权限校验
        UserType userType= TokenUtils.getCurrentUser();
        if(!userType.getUserType().equals("admin")){
            return Result.error(Constants.CODE_401, "权限异常");
        }


        IPage<Administrator> page = new Page<>(pageNum, pageSize);
        QueryWrapper<Administrator> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("aid");
        return Result.success(administratorService.page(page, queryWrapper));
    }

}

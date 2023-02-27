package com.example.demo.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.common.Constants;
import com.example.demo.common.Result;
import com.example.demo.exception.ServiceException;
import com.example.demo.pojo.DTO.LawyerDTO;
import com.example.demo.pojo.Lawyer;
import com.example.demo.service.LawyerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/lawyer")
public class LawyerController {
    @Autowired
    private LawyerService lawyerService;

    @PostMapping("/login")
    public Result login(@RequestBody LawyerDTO lawyerDTO) {
        String email = lawyerDTO.getEmail();
        String pwd = lawyerDTO.getPwd();
        if (StrUtil.isBlank(email) || StrUtil.isBlank(pwd))
            return Result.error(Constants.CODE_400, "参数错误");
        return Result.success(lawyerService.login(lawyerDTO));
    }

    @PostMapping("/saveOrUpdate")
    public Boolean saveOrUpdate(@RequestBody Lawyer lawyer) {
        if (lawyer.getLid() != null) {
            return lawyerService.updateById(lawyer);
        } else {
            QueryWrapper<Lawyer> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("email", lawyer.getEmail());
            if (!lawyerService.list(queryWrapper).isEmpty()) {
                return lawyerService.update(lawyer, queryWrapper);
            } else {
                if (lawyer.getPwd() == null)
                    lawyer.setPwd("123456");
                return lawyerService.save(lawyer);
            }
        }
    }

    @PostMapping("/register")
    public Result register(@RequestBody LawyerDTO lawyerDTO) {
        Lawyer lawyer=new Lawyer();
        BeanUtil.copyProperties(lawyerDTO, lawyer, true);
        lawyer.setLname("null");
        QueryWrapper<Lawyer> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("email", lawyer.getEmail());
        if (!lawyerService.list(queryWrapper).isEmpty()) {
            return Result.error(Constants.CODE_600, "信箱已注册");
        } else {
            lawyerService.save(lawyer);
            Lawyer one;
            try {
                one = lawyerService.getOne(queryWrapper);
            } catch (Exception e) {
                throw new ServiceException(Constants.CODE_500,"系统错误");
            }

            return Result.success(one);
        }

    }

    //分页查询
    @GetMapping("/page")
    public Result findPage(@RequestParam Integer pageNum,
                           @RequestParam Integer pageSize,
                           @RequestParam(defaultValue = "") String lname,
                           @RequestParam(defaultValue = "") String email,
                           @RequestParam(defaultValue = "") String location) {
        IPage<Lawyer> page = new Page<>(pageNum, pageSize);
        QueryWrapper<Lawyer> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("lid");
        if (!"".equals(lname)) {
            queryWrapper.like("lname", lname);
        }
        if (!"".equals(email)) {
            queryWrapper.like("email", email);
        }
        if (!"".equals(location)) {
            queryWrapper.like("location", location);
        }
        return Result.success(lawyerService.page(page, queryWrapper));
    }
}

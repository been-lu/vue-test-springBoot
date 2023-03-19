package com.example.demo.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.common.Constants;
import com.example.demo.common.Result;
import com.example.demo.pojo.Regulation;
import com.example.demo.pojo.UserType;
import com.example.demo.service.RegulationService;
import com.example.demo.utils.TokenUtils;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/regulation")
public class RegulationController {

    @Autowired
    RegulationService regulationService;

    @GetMapping("/page")
    public Result findPage(@RequestParam Integer pageNum, @RequestParam Integer pageSize){
        IPage<Regulation> page = new Page<>(pageNum, pageSize);
        QueryWrapper<Regulation> queryWrapper=new QueryWrapper<>();
        queryWrapper.orderByDesc("did");
        return Result.success(regulationService.page(page));
    }

    @PostMapping("/saveOrUpdate")
    public Result saveOrUpdate(@NotNull @RequestBody Regulation regulation){
        UserType userType= TokenUtils.getCurrentUser();
        if(userType.getUserType().equals("user")){
            return Result.error(Constants.CODE_401, "权限异常");
        }
        if(regulation.getDid()==null){
            regulationService.save(regulation);
        }
        else{
            regulationService.updateById(regulation);
        }
        return Result.success();
    }
}

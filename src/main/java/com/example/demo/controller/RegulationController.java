package com.example.demo.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.common.Result;
import com.example.demo.mapper.RegulationMapper;
import com.example.demo.pojo.Regulation;
import com.example.demo.service.RegulationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
}

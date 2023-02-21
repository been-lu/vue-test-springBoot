package com.example.demo.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.mapper.AdministratorMapper;
import com.example.demo.pojo.Administrator;
import com.example.demo.pojo.DTO.AdminDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdministratorService  extends ServiceImpl<AdministratorMapper, Administrator> {
    @Autowired
    AdministratorMapper administratorMapper;

    public boolean login(AdminDTO adminDTO){
        QueryWrapper<Administrator> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("aname", adminDTO.getAname())
                .eq("pwd", adminDTO.getPwd());
        try {
            Administrator one= getOne(queryWrapper);
            return one !=null;
        }catch (Exception e){
            return false;
        }



    }
}


package com.example.demo.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.mapper.LawyerMapper;
import com.example.demo.pojo.DTO.LawyerDTO;
import com.example.demo.pojo.Lawyer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LawyerService extends ServiceImpl<LawyerMapper, Lawyer> {
    @Autowired
    LawyerMapper lawyerMapper;

    public boolean login(LawyerDTO lawyerDTO) {
        QueryWrapper<Lawyer> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("email", lawyerDTO.getEmail())
                .eq("pwd", lawyerDTO.getPwd());
        try {
            Lawyer one= getOne(queryWrapper);
            return one !=null;
        }catch (Exception e){
            return false;
        }

    }
}

package com.example.demo.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.mapper.LawyerMapper;
import com.example.demo.pojo.Lawyer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LawyerService extends ServiceImpl<LawyerMapper, Lawyer> {
    @Autowired
    LawyerMapper lawyerMapper;

}

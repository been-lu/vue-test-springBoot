package com.example.demo.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.mapper.AdministratorMapper;
import com.example.demo.pojo.Administrator;
import org.springframework.stereotype.Service;

@Service
public class AdministratorService  extends ServiceImpl<AdministratorMapper, Administrator> {
}

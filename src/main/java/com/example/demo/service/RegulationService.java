package com.example.demo.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.mapper.RegulationMapper;
import com.example.demo.pojo.Regulation;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class RegulationService extends ServiceImpl<RegulationMapper, Regulation> {
    @Autowired
    RegulationMapper regulationMapper;



}

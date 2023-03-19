package com.example.demo.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.mapper.MsgMapper;
import com.example.demo.pojo.Msg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MsgService extends ServiceImpl<MsgMapper, Msg> {

    @Autowired
    MsgMapper msgMapper;
}

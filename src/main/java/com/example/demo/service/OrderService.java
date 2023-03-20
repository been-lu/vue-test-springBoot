package com.example.demo.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.mapper.OrderMapper;
import com.example.demo.pojo.Order;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class OrderService extends ServiceImpl<OrderMapper, Order> {


}

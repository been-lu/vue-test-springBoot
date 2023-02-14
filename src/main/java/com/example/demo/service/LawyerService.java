package com.example.demo.service;

import com.example.demo.mapper.LawyerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LawyerService {
    @Autowired
    LawyerMapper lawyerMapper;


}

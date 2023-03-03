package com.example.demo.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.log.Log;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.common.Constants;
import com.example.demo.exception.ServiceException;
import com.example.demo.mapper.LawyerMapper;
import com.example.demo.pojo.DTO.LawyerDTO;
import com.example.demo.pojo.Lawyer;
import com.example.demo.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LawyerService extends ServiceImpl<LawyerMapper, Lawyer> {
    @Autowired
    LawyerMapper lawyerMapper;

    private static final Log LOG = Log.get();

    public LawyerDTO login(LawyerDTO lawyerDTO) {
        QueryWrapper<Lawyer> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("email", lawyerDTO.getEmail())
                .eq("pwd", lawyerDTO.getPwd());
        Lawyer one;
        try {
            one= getOne(queryWrapper);

        }catch (Exception e){
            LOG.error(e);
            throw new ServiceException(Constants.CODE_500,"系统错误");
        }
        if (one != null) {
            BeanUtil.copyProperties(one,lawyerDTO,true);
            lawyerDTO.setToken(TokenUtils.genToken(lawyerDTO.getLid().toString(), lawyerDTO.getPwd()));
            return lawyerDTO;
        }
        else{
            throw new ServiceException(Constants.CODE_600,"用户名或用户错误");
        }
    }
}

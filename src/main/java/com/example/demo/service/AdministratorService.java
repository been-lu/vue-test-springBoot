package com.example.demo.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.log.Log;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.common.Constants;
import com.example.demo.exception.ServiceException;
import com.example.demo.mapper.AdministratorMapper;
import com.example.demo.pojo.Administrator;
import com.example.demo.pojo.DTO.AdminDTO;
import com.example.demo.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdministratorService  extends ServiceImpl<AdministratorMapper, Administrator> {
    @Autowired
    AdministratorMapper administratorMapper;

    private static final Log LOG = Log.get();

    public AdminDTO login(AdminDTO adminDTO){
        QueryWrapper<Administrator> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("aname", adminDTO.getAname())
                .eq("pwd", adminDTO.getPwd());
        Administrator one;
        try {
            one= getOne(queryWrapper);
        }catch (Exception e){
            LOG.error(e);
            throw new ServiceException(Constants.CODE_500,"系统错误");
        }
        if (one != null) {
            BeanUtil.copyProperties(one,adminDTO,true);
            adminDTO.setToken(TokenUtils.genToken(adminDTO.getAid().toString(), adminDTO.getPwd()));
            return adminDTO;
        }
        else{
            throw new ServiceException(Constants.CODE_600,"用户名或用户错误");
        }


    }
}


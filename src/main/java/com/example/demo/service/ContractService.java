package com.example.demo.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.mapper.ContractMapper;
import com.example.demo.pojo.Contract;
import com.example.demo.pojo.ContractVo;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class ContractService extends ServiceImpl<ContractMapper, Contract> {

    @Autowired
    ContractMapper contractMapper;

    public Page<ContractVo> pageByUser(Integer pageNum, Integer pageSize){
        IPage<Contract> page = new Page<>(pageNum, pageSize);
        IPage<Contract> contractIPage = this.page(page);

        //undone
    }
}

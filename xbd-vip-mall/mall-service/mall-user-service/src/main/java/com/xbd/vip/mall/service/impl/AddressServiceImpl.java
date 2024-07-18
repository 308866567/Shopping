package com.xbd.vip.mall.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xbd.vip.mall.mapper.AddressMapper;
import com.xbd.vip.mall.service.AddressService;
import com.xbd.vip.mall.user.model.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressServiceImpl extends ServiceImpl<AddressMapper, Address> implements AddressService {
    @Autowired
    private AddressMapper addressMapper;

    @Override
    public List<Address> list(String userName) {
        QueryWrapper<Address> queryWrapper =new QueryWrapper<Address>();
        queryWrapper.eq("username",userName);
        return addressMapper.selectList(queryWrapper);
    }
}

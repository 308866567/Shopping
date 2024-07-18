package com.xbd.vip.mall.controller;

import com.xbd.mall.util.RespResult;
import com.xbd.vip.mall.service.AddressService;
import com.xbd.vip.mall.user.model.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/address")
@CrossOrigin
public class AddressController {
    @Autowired
    private AddressService addressService;

    //localhost:8088/address/list
    @GetMapping(value = "/list")
    public RespResult<List<Address>> list() {
        String userName = "test";//TODO 用户鉴权
        List<Address> addresses = addressService.list(userName);
        return RespResult.ok(addresses);
    }
}

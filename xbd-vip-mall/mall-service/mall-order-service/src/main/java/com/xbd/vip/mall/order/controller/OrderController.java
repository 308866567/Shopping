package com.xbd.vip.mall.order.controller;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.xbd.mall.util.RespCode;
import com.xbd.mall.util.RespResult;
import com.xbd.vip.mall.order.model.Order;
import com.xbd.vip.mall.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping(value = "/order")
@CrossOrigin
public class OrderController {

    @Autowired
    private OrderService orderService;

    /**
     * 添加订单
     * @param order
     * @return
     */
    @PostMapping
    public RespResult add(@RequestBody Order order){
        //初始化订单信息
        String userName="testUser";
        order.setUsername(userName);
        order.setCreateTime(new Date());
        order.setUpdateTime(order.getCreateTime());
        order.setId(IdWorker.getIdStr());
        order.setOrderStatus(0);
        order.setPayStatus(0);
        order.setIsDelete(0);

        //添加订单
        Boolean bo = orderService.add(order);
        return bo? RespResult.ok():RespResult.error(RespCode.SYSTEM_ERROR);
    }
}

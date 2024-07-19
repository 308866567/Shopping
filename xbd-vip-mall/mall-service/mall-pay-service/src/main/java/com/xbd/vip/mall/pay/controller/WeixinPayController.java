package com.xbd.vip.mall.pay.controller;


import com.alibaba.fastjson.JSON;
import com.xbd.mall.util.RespResult;
import com.xbd.vip.mall.pay.model.PayLog;
import com.xbd.vip.mall.pay.service.PayLogService;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping(value = "/wx")
@CrossOrigin
public class WeixinPayController {

    @Autowired
    private PayLogService payLogService;

    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    /***
     * 记录支付结果
     * 执行事务消息发送
     */
    @GetMapping(value = "/result")
    public RespResult payLog(){
        //记录日志
        PayLog payLog = new PayLog("1",1,"test","No001",new Date());
        //构建消息
        Message message = MessageBuilder.withPayload(JSON.toJSONString(payLog)).build();
        //发消息
        //参数:消息组,发到那里去,队列名,消息,附加参数
        rocketMQTemplate.sendMessageInTransaction("rocket","log",message,null);
        return RespResult.ok();
    }
}





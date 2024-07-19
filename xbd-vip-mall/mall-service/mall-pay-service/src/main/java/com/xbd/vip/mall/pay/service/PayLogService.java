package com.xbd.vip.mall.pay.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xbd.vip.mall.pay.model.PayLog;

public interface PayLogService extends IService<PayLog> {
    void add(PayLog payLog);
}

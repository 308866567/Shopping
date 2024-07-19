package com.xbd.vip.mall.pay.mq;

import com.alibaba.fastjson.JSON;
import com.xbd.vip.mall.pay.model.PayLog;
import com.xbd.vip.mall.pay.service.PayLogService;
import org.apache.rocketmq.spring.annotation.RocketMQTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

/**
 * 事务消息监听类
 */
@Component
@RocketMQTransactionListener(txProducerGroup = "rocket")//传入事务组
public class TransactionListener implements RocketMQLocalTransactionListener {

    @Autowired
    private  PayLogService payLogService;
    /**
     * 当向broker发送half消息成功后,调用该方法
     * 用于执行本地事务操作
     * @param msg
     * @param o
     * @return
     */
    @Override
    public RocketMQLocalTransactionState executeLocalTransaction(Message msg, Object o) {
        try {
            //本地事务
            String result = msg.getPayload().toString();
            PayLog payLog = JSON.parseObject(result, PayLog.class);
            payLogService.add(payLog);
        }catch (Exception e){
            e.printStackTrace();
            return RocketMQLocalTransactionState.ROLLBACK;
        }
        return RocketMQLocalTransactionState.COMMIT;
    }

    /**
     * 超时回查
     * @param message
     * @return
     */
    @Override
    public RocketMQLocalTransactionState checkLocalTransaction(Message message) {
        return RocketMQLocalTransactionState.COMMIT;
    }
}

package com.xbd.vip.mall.order.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xbd.mall.util.RespResult;
import com.xbd.vip.mall.cart.feign.CartFeign;
import com.xbd.vip.mall.cart.model.Cart;
import com.xbd.vip.mall.goods.feign.SkuFeign;
import com.xbd.vip.mall.order.mapper.OrderMapper;
import com.xbd.vip.mall.order.mapper.OrderSkuMapper;
import com.xbd.vip.mall.order.model.Order;
import com.xbd.vip.mall.order.model.OrderSku;
import com.xbd.vip.mall.order.service.OrderService;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper,Order> implements OrderService {


    @Autowired
    private OrderSkuMapper orderSkuMapper;

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private SkuFeign skuFeign;

    @Autowired
    private CartFeign cartFeign;
    /**
     *
     * @param order
     * @return
     */
    @Override
    @GlobalTransactional
    public Boolean add(Order order) {
        //查询购物车数据
        RespResult<List<Cart>> cartResp = cartFeign.list(order.getCartIds());
        List<Cart> carts = cartResp.getData();
        if(carts==null||carts.size()==0){
            return false;
        }

        //递减库存
        skuFeign.dcount(carts);
        //添加订单明细
        int totalNum=0;
        int moneys=0;
        for (Cart cart : carts) {
            //将cart转成OrderSku
            OrderSku orderSku = JSON.parseObject(JSON.toJSONString(cart), OrderSku.class);
            orderSku.setId(IdWorker.getIdStr());
            orderSku.setOrderId(order.getId());//TODO 添加赋值
            orderSku.setMoney(orderSku.getPrice()*orderSku.getNum());
            //添加
            orderSkuMapper.insert(orderSku);

            totalNum+=orderSku.getNum();
            moneys+=orderSku.getMoney();
        }
        //添加订单
        order.setTotalNum(totalNum);
        order.setMoneys(moneys);
        orderMapper.insert(order);

//        int q=10/0;
//        System.out.println("库存递减---");
        //删除购物车记录
        cartFeign.delete(order.getCartIds());
        return true;
    }
}

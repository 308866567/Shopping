package com.xbd.vip.canal.job.dynamic;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import com.xbd.vip.canal.spring.SpringContext;
import com.xbd.vip.mall.page.feign.SeckillPageFeign;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 普通静态作业执行流程
 * 1.指定执行周期
 * 2.分片指定
 * 3.指定Zookeeper中的命名空间
 */
public class DynamicJob implements SimpleJob {
    /**
     *
     * @param shardingContext
     */
    @Override
    public void execute(ShardingContext shardingContext) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("动态执行"
                +shardingContext.getJobParameter()
                +",当前时间:"
                +simpleDateFormat.format(new Date()));

        //静态页删除
        delete(shardingContext.getJobParameter());
    }


    /**
     * 执行静态页删除
     * @param acid
     */
    public void delete(String acid){
    //从容器中获取指定的实例,使用SpringContext
        SeckillPageFeign seckillPageFeign = SpringContext.getBean(SeckillPageFeign.class);
        seckillPageFeign.deleByAct(acid);
    }
}

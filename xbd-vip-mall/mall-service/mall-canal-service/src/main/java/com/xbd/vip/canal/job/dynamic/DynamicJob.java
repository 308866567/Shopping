package com.xbd.vip.canal.job.dynamic;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;

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
    }
}

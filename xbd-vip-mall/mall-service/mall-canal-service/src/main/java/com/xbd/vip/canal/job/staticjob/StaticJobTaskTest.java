package com.xbd.vip.canal.job.staticjob;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import com.dangdang.elasticjob.lite.annotation.ElasticSimpleJob;
import org.springframework.stereotype.Component;

/**
 * 普通静态作业执行流程
 * 1.指定执行周期
 * 2.分片指定
 * 3.指定Zookeeper中的命名空间
 */
//@Component
//@ElasticSimpleJob(
//        jobName = "${elaticjob.zookeeper.namespace}",
//        shardingTotalCount = 1,
//        cron = "0/1 * * * * ?"//一秒一次
//)
public class StaticJobTaskTest implements SimpleJob {
    /**
     *
     * @param shardingContext
     */
    @Override
    public void execute(ShardingContext shardingContext) {
        System.out.println("静态执行");
    }
}

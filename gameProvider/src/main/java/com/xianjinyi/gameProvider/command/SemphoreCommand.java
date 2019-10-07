package com.hzed.simulate.command;

import com.hzed.simulate.vo.AutoSignRequest;
import com.netflix.hystrix.*;

import java.util.Map;

/**
 * 资源隔离策略改为信号量
 * 默认也是10个线程
 * execute使用都是一样的
 *
 * 信号量： HystrixCommandProperties.ExecutionIsolationStrategy.SEMAPHORE
 * 默认线程池： HystrixCommandProperties.ExecutionIsolationStrategy.THREAD
 *
 *
 */
public class SemphoreCommand extends HystrixCommand<AutoSignRequest> {

    private Map map;

    public SemphoreCommand(Map paramMap) {
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("SemphoreCommand"))
                // 自定义command名称 不设置默认为类名
                .andCommandKey(HystrixCommandKey.Factory.asKey("SemphoreCommand"))
                // 自定义线程池名称
                .andThreadPoolKey(HystrixThreadPoolKey.Factory.asKey("asas"))
                // 自定义线程池大小 队列大小
                .andThreadPoolPropertiesDefaults(HystrixThreadPoolProperties.Setter()
                        .withCoreSize(20)
                        // 两参数，谁小谁生效 不设置，默认线程池满了就拒绝
                        .withMaxQueueSize(20)
                        .withQueueSizeRejectionThreshold(5)
                        )

                .andCommandPropertiesDefaults(HystrixCommandProperties.Setter()

                        .withExecutionIsolationStrategy(HystrixCommandProperties.ExecutionIsolationStrategy.THREAD)
                        // 信号量大小
                        .withExecutionIsolationSemaphoreMaxConcurrentRequests(20)
                        // 降级机制最大并发量
                        .withFallbackIsolationSemaphoreMaxConcurrentRequests(20)
                        // 接口访问调用超时时间
                        .withExecutionTimeoutInMilliseconds(5000)
                        // 是否开启超时机制，默认开启
                        .withExecutionTimeoutEnabled(true)
                )

        );
    }

    @Override
    protected AutoSignRequest run() throws Exception {
        return null;
    }
}

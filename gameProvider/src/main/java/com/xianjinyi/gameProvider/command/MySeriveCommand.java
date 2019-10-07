package com.hzed.simulate.command;

import com.hzed.simulate.vo.ApiResponse;
import com.hzed.simulate.vo.AutoSignRequest;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;

/**
 *
 * 线程池 默认10个线程
 * 基于HystrixCommand做线程池做资源隔离技术  一般用于单条数据获取
 * HystrixObservableCommand  多条数据
 *
 * <AutoSignRequest> 表示command 最终返回的数据结果的格式
 *
 */
public class MySeriveCommand extends HystrixCommand<AutoSignRequest> {

    private Long pid;

    public MySeriveCommand(Long pid) {

        /**
         * 相同Group name的 绑定到一个线程池里面去
         */
        super(HystrixCommandGroupKey.Factory.asKey("MySeriveCommandGroup"));
        this.pid = pid;
    }

    @Override
    protected AutoSignRequest run() throws Exception {
        // 具体http调用的逻辑
        // 参数 通过构造函数传入本command类 ，即pid


        return new AutoSignRequest();
    }
}

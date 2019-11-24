package com.xianjinyi.gameProvider.disruptor;

import java.util.concurrent.ThreadFactory;

/**
 * @author: xianjinyi
 * @date 2019/11/23
 */
public class EventThreadFactory implements ThreadFactory {
    @Override
    public Thread newThread(Runnable r) {
        return new Thread(r);
    }
}


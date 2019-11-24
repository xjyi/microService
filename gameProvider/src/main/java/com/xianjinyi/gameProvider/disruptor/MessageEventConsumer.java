package com.xianjinyi.gameProvider.disruptor;


import com.lmax.disruptor.EventHandler;

/**
 * @author: xianjinyi
 * @date 2019/11/23
 */
public class MessageEventConsumer implements EventHandler<MessageEvent> {
    @Override
    public void onEvent(MessageEvent event, long sequence, boolean endOfBatch) throws Exception {
        System.out.println("消费者消费消息：" + event.getMessage());
    }
}

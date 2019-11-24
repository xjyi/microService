package com.xianjinyi.gameProvider.disruptor;

import com.lmax.disruptor.EventFactory;

/**
 * @author: xianjinyi
 * @date 2019/11/23
 */
public class MessageEventFactory implements EventFactory<MessageEvent> {
    @Override
    public MessageEvent newInstance() {
        return new MessageEvent();
    }
}

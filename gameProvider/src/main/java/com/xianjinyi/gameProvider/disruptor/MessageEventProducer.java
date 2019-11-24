package com.xianjinyi.gameProvider.disruptor;

import com.lmax.disruptor.RingBuffer;

/**
 * @author: xianjinyi
 * @date 2019/11/23
 */
public class MessageEventProducer {
    private RingBuffer<MessageEvent> ringBuffer;

    public MessageEventProducer(RingBuffer<MessageEvent> ringBuffer) {
        this.ringBuffer = ringBuffer;
    }
    /**
     * 将接收到的消息输出到ringBuffer
     */
    public void onData(String messageStr) {
        //请求下一个事件序号；
        long sequence = ringBuffer.next();
        try {
            //获取该序号对应的事件对象；
            MessageEvent event = ringBuffer.get(sequence);
            event.setMessage(messageStr);
            System.out.println("生产者发布消息:" + messageStr);
        }
        finally {
            //发布事件；
            ringBuffer.publish(sequence);
        }
    }
}


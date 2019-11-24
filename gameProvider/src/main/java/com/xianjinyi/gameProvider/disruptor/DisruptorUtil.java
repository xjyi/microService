package com.xianjinyi.gameProvider.disruptor;

/**
 * @author: xianjinyi
 * @date 2019/11/23
 */

import com.lmax.disruptor.EventFactory;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.YieldingWaitStrategy;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;

/**
 * disrupt工具类
 *
 * @author yang.kuowei
 * @version 1.0
 * @date 2019-05-14 19:34
 */
public  class DisruptorUtil {

    private static DisruptorUtil disruptorUtil;

    private Disruptor<MessageEvent> disruptor;

    // RingBuffer 大小，必须是 2 的 N 次方；
    private static final int RING_BUFFER_SIZE = 1024 * 1024;

    private RingBuffer<MessageEvent> ringBuffer;

    private MessageEventProducer messagemEventProducer;

    private final MessageEventConsumer messageEventConsumer;

    private DisruptorUtil() {
        EventThreadFactory eventThreadFactory = new EventThreadFactory();
        EventFactory<MessageEvent> eventFactory = new MessageEventFactory();
        disruptor = new Disruptor<MessageEvent>(eventFactory, RING_BUFFER_SIZE, eventThreadFactory, ProducerType.SINGLE,
                new YieldingWaitStrategy());
        messageEventConsumer = new MessageEventConsumer();
        disruptor.handleEventsWith(messageEventConsumer);
    }

    /**
     * 获取 LogDisruptorUtil 实例
     *
     * @return LogDisruptorUtil
     */
    public static DisruptorUtil getInstance() {
        if (disruptorUtil == null) {
            synchronized (DisruptorUtil.class) {
                if (disruptorUtil == null) {
                    disruptorUtil = new DisruptorUtil();
                    return disruptorUtil;
                }
            }
        }
        return disruptorUtil;
    }
    /**
     * 启动disruptor
     */
    public void start() {
        disruptor.start();
        ringBuffer = disruptor.getRingBuffer();
        messagemEventProducer = new MessageEventProducer(ringBuffer);
        //应用关闭前关闭disrupt
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            @Override
            public void run() {
                disruptor.shutdown();
            }
        }));
    }

    /**
     * 生产者发布事件
     */
    public void produce(String message) {
        messagemEventProducer.onData(message);
    }

    public static void main(String[] args) {
        char a = 'a';
        System.out.println();
    }

}

package com.xianjinyi.gameConsumer.stream;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

/**
 * @author: xianjinyi
 * @date 2020/03/27
 */
public interface StreamClient {

    /**
     * 接收端
     * @return
     */
    @Input("MyMessage")
    SubscribableChannel myMessageInput();

    @Input("User")
    SubscribableChannel userInput();
}

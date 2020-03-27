package com.xianjinyi.gameProvider.stream;

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
     * 发送端
     * @return
     */
    @Output("MyMessage")
    MessageChannel myMessageOutput();

    @Output("User")
    MessageChannel userOutput();
}

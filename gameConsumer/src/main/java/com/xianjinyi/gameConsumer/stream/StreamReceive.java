package com.xianjinyi.gameConsumer.stream;

import com.xianjinyi.gameConsumer.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

/**
 * @author: xianjinyi
 * @date 2020/03/27
 */

@Component
// 绑定
@EnableBinding(StreamClient.class)
@Slf4j
public class StreamReceive {


    @StreamListener("MyMessage")
    public void receive(String obj){
        log.info("MyMessage接收：{}",obj);
    }

    @StreamListener("User")
    @SendTo("MyMessage") // 回复消息
    public void receive(User obj){
        log.info("User接收：{}",obj);
    }
}

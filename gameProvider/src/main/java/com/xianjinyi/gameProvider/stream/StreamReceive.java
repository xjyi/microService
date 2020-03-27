package com.xianjinyi.gameProvider.stream;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
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





}

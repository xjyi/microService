package com.xianjinyi.gameProvider.controller;

import com.netflix.hystrix.HystrixCommandProperties;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.xianjinyi.gameProvider.dao.UserRepository;
import com.xianjinyi.gameProvider.entity.User;
import com.xianjinyi.gameProvider.stream.StreamClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Optional;

/**
 * @Author: xianjinyi
 * @date 2019/03/12
 */

@RestController
@RequestMapping("/game")
@Slf4j
@DefaultProperties(defaultFallback = "sendMq")// 设置当前类默认降级方法
public class GameController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private StreamClient streamClient;



    /**
     * MQ发送端
     * @return
     */
    @GetMapping("/sendMq")
    public String sendMq(){
        Message<String> buildMess = MessageBuilder.withPayload("time:" + new Date()).build();
        streamClient.myMessageOutput().send(buildMess);
        return "SUCCESS";
    }

    /**
     * MQ发送端
     * @return
     * 常用短路器配置（也是HystrixCommandProperties中）  circuitBreaker.enabled
     * circuitBreaker.requestVolumeThreshold   单位时间最少达到多少流量
     * circuitBreaker.sleepWindowInMilliseconds 半开的时间，即多长时间尝试
     * circuitBreaker.errorThresholdPercentage  打开短路器的失败百分比阈值
     *
     * 也可以在yml中配置，见yml
     *
     *
     */
    @GetMapping("/sendData")
    @HystrixCommand(fallbackMethod = "sendMq",
            // 超时时间配置，默认1s HystrixCommandProperties
            commandProperties = @HystrixProperty(name= "execution.isolation.thread.timeoutInMilliseconds",value = "2000")

    )
    public String sendData(){
        User user = new User();
        user.setName("神罗天征");
        user.setId(11111L);
        Message<User> buildMess = MessageBuilder.withPayload(user).build();
        streamClient.userOutput().send(buildMess);
        return "SUCCESS";
    }


    @GetMapping("/testSync")
    public User testSync(){

        toSynchornize();
        return null;
    }

    @GetMapping("/{id}")
    public User listGame(@PathVariable Long id){
        //toLoop();
//        toSynchornize();
//        return null;
        return userRepository.findById(id).get();
    }

    private void toSynchornize(){
        synchronized (this){
            System.out.println("sync1");
            try {

                this.wait();
            }catch (Exception e){
                e.printStackTrace();
            }

        }
    }

    private void toLoop() {
        int i=0;
        while(i<Integer.MAX_VALUE){
            System.out.println("CPU过高测试"+i);
            i++;
        }
    }

    /**
     *  多参数 get
     */
    @GetMapping("/multiGet")
    public User multiGet(Long id ,String flag ){
        log.info("请求入参：{}=={}" ,id,flag);
        return userRepository.findById(id).get();
    }


    @PostMapping("/post")
    public User post(Long id ,String flag ){
        log.info("请求入参：{}=={}" ,id,flag);
        return userRepository.findById(id).get();
    }

    @PostMapping("/postObject")
    public User post(@RequestBody  User user ){
        log.info("请求入参：{}=={}" ,user);
        return userRepository.findById(user.getId()).get();
    }



}

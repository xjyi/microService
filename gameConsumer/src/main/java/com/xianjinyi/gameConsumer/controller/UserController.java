package com.xianjinyi.gameConsumer.controller;

import com.xianjinyi.gameConsumer.entity.User;
import com.xianjinyi.gameConsumer.microService.MicroServiceUser;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @Author: xianjinyi
 * @date 2019/03/12
 */
@RequestMapping("/user")
@RestController
@Slf4j
public class UserController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private LoadBalancerClient loanBalanceClient;

    @Autowired
    private MicroServiceUser microServiceUser;


    @GetMapping("/feigh/{id}")
    public User feigh(@PathVariable Long id){
        User entity = microServiceUser.findById(id);
        log.info("返回{}",entity.toString());
        return entity;
    }


    @GetMapping("/multiGet")
    public User multiGet(){

        User entity = microServiceUser.multiGet(1L,"test");
        log.info("返回{}",entity.toString());
        return entity;
    }





    @GetMapping("/getUserInfo/{id}")
    public User getUserInfo(@PathVariable Long id){
        ResponseEntity<User> entity = restTemplate.getForEntity("http://microservice-provider-user/xianjinyi/game/{id}", User.class,id);
        log.info("返回{}",entity.toString());
        return entity.getBody();
    }

    /**
     * 测试负载均衡
     */
    @GetMapping("/loanBalance")
    public String  loanBalance(){
        // 经过负载均衡后，获取连接的微服务节点信息
        ServiceInstance instance = loanBalanceClient.choose("microservice-provider-user");
        String host = instance.getHost();
        int port = instance.getPort();
        String serviceId = instance.getServiceId();
        log.info("访问地址：{},端口：{} ：serviceId{}",host,port,serviceId);


        ServiceInstance instance2 = loanBalanceClient.choose("microservice-provider-user2");
        String host2 = instance2.getHost();
        int port2 = instance2.getPort();
        String serviceId2= instance2.getServiceId();
        log.info("访问地址：{},端口：{} ：serviceId{}",host2,port2,serviceId2);



        return  "SUCCESS";
    }

}

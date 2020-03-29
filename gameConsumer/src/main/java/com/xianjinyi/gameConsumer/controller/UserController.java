package com.xianjinyi.gameConsumer.controller;

import com.xianjinyi.gameConsumer.entity.User;
import com.xianjinyi.gameConsumer.microService.MicroServiceUser;
import com.xianjinyi.gameConsumer.stream.StreamClient;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: xianjinyi
 * @date 2019/03/12
 */
@RequestMapping("/user")
@RestController
@Slf4j
@RefreshScope
public class UserController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private LoadBalancerClient loanBalanceClient;

    @Autowired
    private MicroServiceUser microServiceUser;





    @Value("${evn}")
    private String env;

    @GetMapping("/getEnv")
    // allowCredentials = "true" 允许cookie跨域
    @CrossOrigin(allowCredentials = "true")
    public String getEnv(){
        return env;
    }





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

    @GetMapping("/getByMap")
    public User getByMap(){

        HashMap hashMap = new HashMap();
        hashMap.put("id",1L);
        hashMap.put("flag","MapTest");
        User entity = microServiceUser.multiGet2(hashMap);
        log.info("getByMap返回{}",entity.toString());
        return entity;
    }


    @GetMapping("/post")
    public User post(){

        HashMap hashMap = new HashMap();
        hashMap.put("id",2L);
        hashMap.put("flag","MapTest");
        User entity = microServiceUser.post(hashMap);
        log.info("post返回{}",entity.toString());
        return entity;
    }

    @GetMapping("/postOject")
    public User postOject(){

        User user = new User();
        user.setId(3L);
        User entity = microServiceUser.postObject(user);
        log.info("post返回{}","ok");
        return entity;
    }


    public static void main(String[] args) {
        ArrayList<User> users = new ArrayList<>();
        users.add(new User(1L,"肠粉"));
        users.add(new User(2L,"蛋糕"));
        users.add(new User(3L,"雪条"));
        List<String> list = users.stream()
                .map(User::getUsername)
                .collect(Collectors.toList());

        System.out.println(list);

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

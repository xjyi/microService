package com.xianjinyi.gameConsumer.controller;

import com.xianjinyi.gameConsumer.entity.User;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("/getUserInfo/{id}")
    public User getUserInfo(@PathVariable Long id){
        ResponseEntity<User> entity = restTemplate.getForEntity("http://microservice-provider-user/xianjinyi/game/{id}", User.class,id);
        log.info("返回{}",entity.toString());
        return entity.getBody();
    }
}

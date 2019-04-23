package com.xianjinyi.gameConsumer.microService;


import com.xianjinyi.gameConsumer.entity.User;
import feign.Logger;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@FeignClient(name = "microservice-provider-user"/*,configuration = UserFeignConfig.class*/)
public interface MicroServiceUser {

    @GetMapping("/xianjinyi/game/{id}")
    User findById(@PathVariable("id") Long id);

    @GetMapping("/xianjinyi/game/multiGet")
    User multiGet(@RequestParam("id") Long id ,@RequestParam("flag") String flag);

    @GetMapping("/xianjinyi/game/multiGet")
    User multiGet2(@RequestParam Map hashMap);

    @PostMapping("/xianjinyi/game/post")
    User post(@RequestParam Map hashMap);

    @PostMapping("/xianjinyi/game/postObject")
    User postObject(@RequestBody User user);
}


//class UserFeignConfig {
//    @Bean
//    public Logger.Level logger() {
//        return Logger.Level.FULL;
//    }
//}
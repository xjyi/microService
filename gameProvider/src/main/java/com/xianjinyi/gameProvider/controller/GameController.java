package com.xianjinyi.gameProvider.controller;

import com.xianjinyi.gameProvider.dao.UserRepository;
import com.xianjinyi.gameProvider.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * @Author: xianjinyi
 * @date 2019/03/12
 */

@RestController
@RequestMapping("/game")
@Slf4j
public class GameController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/{id}")
    public User listGame(@PathVariable Long id){
        return userRepository.findById(id).get();
    }

    /**
     *  多参数 get
     */
    @GetMapping("/multiGet")
    public User multiGet(Long id ,String flag ){
        log.info("请求入参：｛｝==｛｝" ,id,flag);
        return userRepository.findById(id).get();
    }
}

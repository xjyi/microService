package com.xianjinyi.gameConsumer.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @Author: xianjinyi
 * @date 2019/03/12
 */

@Data
//@NoArgsConstructor
@AllArgsConstructor
public class User {

    private Long id;
    private String username;
    private String name;
    private Integer age;
    private BigDecimal balance;

    public static void main(String[] args) {
        Integer a= 1201;
        Integer b= 1201;
        System.out.println(a == b);

    }

    public User (){

    }
    public User (Long id,String username){
        this.id = id;
        this.username = username;
    }
}

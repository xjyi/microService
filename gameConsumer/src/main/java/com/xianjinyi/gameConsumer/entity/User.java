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
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private Long id;
    private String username;
    private String name;
    private Integer age;
    private BigDecimal balance;
}

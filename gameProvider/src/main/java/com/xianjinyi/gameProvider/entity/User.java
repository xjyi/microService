package com.xianjinyi.gameProvider.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * @Author: xianjinyi
 * @date 2019/03/12
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {


    @Id
    //@GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column
    private String username;
    @Column
    private String name;
    @Column
    private Integer age;
    @Column
    private BigDecimal balance;

    public static void main(String[] args) {
        int pageSize = 8 * 1024 *1024;
        int size = ~(pageSize - 1);
        System.out.println("size=" + size);
    }
}

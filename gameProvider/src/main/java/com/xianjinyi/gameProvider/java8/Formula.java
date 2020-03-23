package com.xianjinyi.gameProvider.java8;

/**
 * @author: xianjinyi
 * @date 2020/02/25
 */
interface Formula{

    double calculate(int a);

    default double sqrt(int a) {
        return Math.sqrt(a);
    }

}

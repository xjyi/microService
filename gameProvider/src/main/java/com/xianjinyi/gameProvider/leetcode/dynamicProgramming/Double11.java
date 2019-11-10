package com.xianjinyi.gameProvider.leetcode.dynamicProgramming;

/**
 * 花最少的钱，满足满减最低价格
 */
public class Double11 {


    // items商品价格，n商品个数, w表示满减条件，比如200
    public static void double11advance(int[] items, int n, int w) {
        //超过3倍就没有薅羊毛的价值了
        //  限制条件与期望条件是同一个，使用布尔数组即可
        boolean[][] states = new boolean[n][3*w+1];
        states[0][0] = true;  // 第一行的数据要特殊处理
        states[0][items[0]] = true;


        for (int i = 1; i < n; ++i) { // 动态规划
            for (int j = 0; j <= 3*w; ++j) {// 不购买第i个商品
                if (states[i - 1][j] == true) {
                    states[i][j] = states[i - 1][j];
                }
            }
            for (int j = 0; j <= 3 * w - items[i]; ++j) {//购买第i个商品
                if (states[i - 1][j] == true) {
                    states[i][j + items[i]] = true;
                }
            }
        }

        int j;
        for (j = w; j < 3 * w + 1; ++j) {
            if (states[n - 1][j] == true) break; // 输出结果大于等于w的最小值
        }
        // 此处存疑，应该用一个变量来接j
        if (j == -1) return; // 没有可行解

        for (int i = n - 1; i >= 1; --i) { // i表示二维数组中的行，j表示列
            // 当前总价格-当前物品价格，对应的值为ture，表示该物品在当前层中是有计算的（至少购买当前商品是其中之一的可行做法）
            if (j - items[i] >= 0 && states[i - 1][j - items[i]] == true) {
                System.out.print(items[i] + " "); // 购买这个商品
                j = j - items[i];
            } // else 没有购买这个商品，j不变。
        }
        if (j != 0) {
            System.out.print(items[0]);
        }
    }


}

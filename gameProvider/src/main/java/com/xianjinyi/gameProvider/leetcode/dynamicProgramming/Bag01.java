package com.xianjinyi.gameProvider.leetcode.dynamicProgramming;

public class Bag01 {


    // weight:物品重量，n:物品个数，w:背包可承载重量
    public int knapsack(int[] weight, int n, int weightLimit) {
        // 默认值false
        // 背包最多装weightLimit重，且都为整型，则每个可能的重量都是一个数组下标，值为该重量是否匹配上计算结果
        // 相当于有n个一维数组，每个一维数组大小为 weightLimit+1
        boolean[][] states = new boolean[n][weightLimit+1];

        // 第一行的数据要特殊处理，可以利用哨兵优化

        // 第一行装或者不装
        states[0][0] = true;
        states[0][weight[0]] = true;

        // 动态规划状态转移
        // 每一个物品
        for (int i = 1; i < n; ++i) {

            // 不把第i个物品放入背包，
            // 当前物品不装，则当前物品对应的一维数组，与上一个物品的一样
            for (int j = 0; j <= weightLimit; ++j) {
                if (states[i-1][j] == true) {
                    states[i][j] = states[i-1][j];
                }
            }

            //把第i个物品放入背包
            // 当前物品装，则不超过总重量前提下，之前每个有数据的都加当前重量（已经包括了前面全部不装的情况）
            for (int j = 0; j <= weightLimit-weight[i]; ++j) {
                if (states[i-1][j]==true) {
                    states[i][j+weight[i]] = true;
                }
            }
        }

        // 输出结果
        for (int i = weightLimit; i >= 0; --i) {
            if (states[n-1][i] == true) {
                return i;
            }
        }
        return 0;
    }


    public static int knapsack2(int[] items, int n, int w) {
        boolean[] states = new boolean[w+1]; // 默认值false
        states[0] = true;  // 第一行的数据要特殊处理，可以利用哨兵优化
        states[items[0]] = true;

        for (int i = 1; i < n; ++i) { // 动态规划
            for (int j = w-items[i]; j >= 0; --j) {//把第i个物品放入背包
                if (states[j]==true) states[j+items[i]] = true;
            }
        }

        for (int i = w; i >= 0; --i) { // 输出结果
            if (states[i] == true) return i;
        }
        return 0;
    }








    public int myKnapsack(int[] weight, int n, int weightLimit){

        // 0~weightLimit
        boolean [][] bags = new boolean[n][weightLimit+1];

        // 第一行
        bags[0][0] = true;
        bags[0][weight[0]] = true;

        for (int i = 1; i < n; i++) {
            // weightLimit+1 是大小，weightLimit是最大下标，
            for(int j=0;j<=weightLimit ;j++){
                if (bags[i-1][j]){
                    bags[i][j] =true;
                }
            }

            for (int j = 0; j <= weightLimit - weight[i]; j++) {
                if (bags[i - 1][j]) {
                    bags[i][j + weight[i]] = true;
                }
            }
        }

        // 取数据
        for(int j = weightLimit+1;j>0;j--){
            if(bags[n-1][j]){
                return j;
            }
        }

        return 0;
    }













}

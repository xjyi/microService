package com.xianjinyi.gameProvider.leetcode.dynamicProgramming;

public class Bag01Value {

    private int maxV = Integer.MIN_VALUE; // 结果放到maxV中
    private int[] items = {2,2,4,6,3};  // 物品的重量
    private int[] value = {3,4,8,9,6}; // 物品的价值
    private int n = 5; // 物品个数
    private int w = 9; // 背包承受的最大重量



    public static int myKnapsack3(int[] weight, int[] value, int n, int w) {
        int[] states = new int[w+1];

        for(int i=0;i<w+1;i++){
            states[i] = -1;
        }


        states[0]=0;
        states[weight[0]]=value[0];

        // 要区分是上一次的数据，还是这一次的数据
        // 只看重量的话，从右往左，遇到的肯定是上次的
        // 价值其实也是一样，值在遍历的时候，除了比较大小，剩余的就是确定当前的组合是否存在，没有其他作用
        for (int i = 1; i < n; i++) {
            for (int j = w - weight[i]; j >= 0; j--) {
                if (states[j] >= 0) {
                    int v = states[j] + value[i];
                    if (states[j+weight[i]] < v){
                        states[j+weight[i]] = v;
                    }
                }
            }
        }

        int maxVal =0;
        for (int i = 0; i <= w; i++) {
            if (states[i] >maxVal ){
                maxVal =  states[i];
            }
        }

        return maxVal;
    }


    public static void main(String[] args) {
        Bag01Value bag01Value = new Bag01Value();
//        int value = knapsack3(bag01Value.items, bag01Value.value, bag01Value.n, bag01Value.w);
        int value = myKnapsack3(bag01Value.items, bag01Value.value, bag01Value.n, bag01Value.w);
        System.out.println(value);
    }







    public static int knapsack3(int[] weight, int[] value, int n, int w) {
        int[][] states = new int[n][w+1];

        // 初始化states
        // 初始化是必须的，在只考虑重量时，该值表示当前重量是否存在
         // 此处要判断价值都否存在，只能在已有的情况下继续添加，初始化为0，0可能是已存在的情况，只是价值为0 ，所以需要-1
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < w+1; ++j) {
                states[i][j] = -1;
            }
        }
        states[0][0] = 0;
        states[0][weight[0]] = value[0];

        for (int i = 1; i < n; ++i) { //动态规划，状态转移

            // 不选择第i个物品
            for (int j = 0; j <= w; ++j) {
                if (states[i-1][j] >= 0) {
                    states[i][j] = states[i-1][j];
                }
            }
            // 选择第i个物品
            for (int j = 0; j <= w-weight[i]; ++j) {
                if (states[i-1][j] >= 0) {
                    int v = states[i-1][j] + value[i];
                    if (v > states[i][j+weight[i]]) {
                        states[i][j+weight[i]] = v;
                    }
                }
            }
        }
        // 找出最大值
        // j代表的是重量，之前的例子就是求重量，所以直接拿最右的，价值则不一定，所以要遍历
        int maxvalue = -1;
        for (int j = 0; j <= w; ++j) {
            if (states[n-1][j] > maxvalue) {
                maxvalue = states[n-1][j];
            }
        }
        return maxvalue;
    }



    /**
     * 01 背包 价值问题
     * 回溯方式
     * @param i
     * @param cw
     * @param cv
     */
    public void f(int i, int cw, int cv) { // 调用f(0, 0, 0)
        // cw==w表示装满了,i==n表示物品都考察完了
        if (cw == w || i == n) {
            if (cv > maxV) maxV = cv;
            return;
        }


        f(i + 1, cw, cv); // 选择不装第i个物品


        if (cw + items[i] <= w) {
            f(i + 1, cw + items[i], cv + value[i]); // 选择装第i个物品
        }
    }
}

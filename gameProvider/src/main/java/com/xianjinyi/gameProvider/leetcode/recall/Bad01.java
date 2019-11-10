package com.xianjinyi.gameProvider.leetcode.recall;

public class Bad01 {

    //存储背包中物品总重量的最大值
    public int maxW = Integer.MIN_VALUE;


    // cw表示当前已经装进去的物品的重量和；i表示考察到哪个物品了；
    // w背包重量；items表示每个物品的重量；n表示物品个数
    // 假设背包可承受重量100，物品个数10，物品重量存储在数组a中，那可以这样调用函数：
    // f(0, 0, a, 10, 100)


    public void find(int i, int wight, int[] items, int n, int weightLimit) {
        // 进入当前方法 wight <= weightLimit
        // < 的时候，可能还可以继续装
        if (wight == weightLimit || i == n) { // weight==weightLimit表示装满了;i==n表示已经考察完所有的物品
            if (wight > maxW) {
                // 记录最大的，覆盖小的
                maxW = wight;
            }
            return;
        }

        // 0:当前不装进背包
        find(i+1, wight, items, n, weightLimit);

        // 1：不超过限制时，装进背包
        if (wight + items[i] <= weightLimit) {// 已经超过可以背包承受的重量的时候，就不要再装了
            find(i+1,wight + items[i], items, n, weightLimit);
        }
    }


















    public void find2(int i, int wight, int[] items, int n, int weightLimit) {
        // 剪枝
        if(wight == weightLimit || i == n){
            if(wight >maxW){
                maxW = wight;
            }
            // 不能漏
            return;
        }

        find2(i+1,  wight, items,  n,  weightLimit);
        if (wight + items[i] <= weightLimit){
            find2(i+1,  wight + items[i], items,  n,  weightLimit);
        }

    }









}

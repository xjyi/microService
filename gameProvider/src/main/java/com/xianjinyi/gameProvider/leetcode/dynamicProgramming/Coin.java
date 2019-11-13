package com.xianjinyi.gameProvider.leetcode.dynamicProgramming;

public class Coin {
    public static int[] item ={1,3,5};
    public static int itemLen =3;


    public static void main(String[] args) {
        getLeastCoin(9);
    }
    /**
     * 硬币有多种，此处应该是用状态转移方程
     * 相同硬币数的可以合并
     *
     * 硬币个数 i
     * 总金额 j
     * status[i][j] = min()
     * @param val
     * @return
     */
    public static int getLeastCoin(int val){
        // i 是个数 j是金额 j到达期望值时，i最小
        boolean [][]status = new boolean[val+1][val+1];

        for (int k = 0; k < itemLen; k++) {
            status[0][item[k]] =true;
        }

        for (int i=1;i<=val;i++){
            for (int j = 0; j <= val; j++) {
                if (status[i - 1][j]) {
                    status[i][j] = true;
                }
            }

            for (int j = 0; j <= val; j++) {
                if (status[i - 1][j]) {
                    for (int k = 0; k < itemLen; k++) {
                        if (j + item[k] <= val) {
                            status[i][j + item[k]] =true;

                            // 等于的时候可剪枝
                            if(j + item[k] == val){
                                // 打印硬币组合
                                printf(status,i,j + item[k]);
                                return i+1;
                            }
                        }else{
                            // 加入某个硬币后，超过预期值，但是不代表已经找到
                            // 防止越界，直接不处理即可
                        }
                    }
                }
            }
        }


        return 0;
    }

    private static void printf(boolean[][] status, int i, int j) {
        while(i>0){
            for (int k = 0; k < itemLen; k++) {
                if (j-item[k] >=0 && status[i-1][j-item[k]]){
                    i = i-1;
                    j= j-item[k];
                    System.out.println(item[k]);

                    if (i==0){
                        System.out.println(j);
                    }
                    break;
                }
            }
        }
    }


    // 评论答案 1
    public int minCoins(int money) {
        if (money == 1 || money == 3 || money == 5) return 1;
        boolean [][] state = new boolean[money][money + 1];
        if (money >= 1) state[0][1] = true;
        if (money >= 3) state[0][3] = true;
        if (money >= 5) state[0][5] = true;
        for (int i = 1; i < money; i++) {
            for (int j = 1; j <= money; j++) {
                if (state[i - 1][j]) {
                    if (j + 1 <= money) state[i][j + 1] = true;
                    if (j + 3 <= money) state[i][j + 3] = true;
                    if (j + 5 <= money) state[i][j + 5] = true;
                    if (state[i][money]) return i + 1;
                }
            }
        }
        return money;
    }
    // 评论答案 2
    public int countMoneyMin(int[] moneyItems, int resultMemory) {

        if (null == moneyItems || moneyItems.length < 1) {
            return -1;
        }
        if (resultMemory < 1) {
            return -1;
        }

        // 计算遍历的层数，此按最小金额来支付即为最大层数
        int levelNum = resultMemory / moneyItems[0];
        int leng = moneyItems.length;

        int[][] status = new int[levelNum][resultMemory + 1];

        // 初始化状态数组
        for (int i = 0; i < levelNum; i++) {
            for (int j = 0; j < resultMemory + 1; j++) {
                status[i][j] = -1;
            }
        }

        // 将第一层的数数据填充
        for (int i = 0; i < leng; i++) {
            status[0][moneyItems[i]] = moneyItems[i];
        }

        int minNum = -1;

        // 计算推导状态
        for (int i = 1; i < levelNum; i++) {
            // 推导出当前状态
            for (int j = 0; j < resultMemory; j++) {
                if (status[i - 1][j] != -1) {
                    // 遍历元素,进行累加
                    for (int k = 0; k < leng; k++) {
                        if (j + moneyItems[k] <= resultMemory) {
                            // 应该是想记录达到当前状态用的是哪种硬币
                            status[i][j + moneyItems[k]] = moneyItems[k];
                        }
                    }
                }

                // 找到最小的张数（此处大于0应该是错误的，应该只有等于0才符合）
                if (status[i][resultMemory] >= 0) {
                    minNum = i + 1;
                    break;
                }
            }

            if (minNum > 0) {
                break;
            }
        }

        int befValue = resultMemory;

        // 进行反推出，币的组合
        for (int i = minNum - 1; i >= 0; i--) {
            for (int j = resultMemory; j >= 0; j--) {
                if (j == befValue) {
                    System.out.println("当前的为:" + status[i][j]);
                    befValue = befValue - status[i][j];
                    break;
                }
            }
        }

        return minNum;
    }
}

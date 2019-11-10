package com.xianjinyi.gameProvider.leetcode.dynamicProgramming;

public class Coin {
    public static int[] item ={1,3,5};
    public static int itemLen =3;

    // 待完善
    public static int getLeastCoin(int val){
        // i 是个数 j是金额 j到达期望值时，i最小
        boolean [][]status = new boolean[val+1][val+1];
        status[0][item[0]] =true;
        status[0][item[1]] =true;
        status[0][item[2]] =true;

        int count = 0;
        aa:for (int i=1;i<=val;i++){
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
                        }else{
                            count = i;
                            break aa;
                        }
                    }
                }
            }
        }

        for (int i=0;i<=val;i++){
//            status[count][val]
        }


        return 0;
    }
}

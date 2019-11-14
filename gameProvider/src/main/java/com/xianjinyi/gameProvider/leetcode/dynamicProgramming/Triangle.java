package com.xianjinyi.gameProvider.leetcode.dynamicProgramming;

/**
 * @author: xianjinyi
 * @date 2019/11/14
 */

public class Triangle {

    private static int item[][] = new int[][]{{5},{7,8},{2,3,4},{4,9,6,1},{2,7,9,4,5}};

    /**
     * 杨辉三角 行列数一样
     * @param n 行
     * @return
     */
    private int getShortest(int n){

        // 值为到达该节点的最短路径
        int[][] status = new int[n][n];
        status[0][0] = item[0][0];

        for (int i = 1; i < n; i++) {
            // 从比自己小的下标，以及跟自己相同的下标过来
            for (int j = 0; j <= i; j++) {
                int min = Integer.MAX_VALUE;
                // 左边过来
                if (j - 1 >= 0 && status[i-1][j-1] < min ) {
                    min = status[i-1][j-1];
                }
                // 右边
                if (j<=i-1 && status[i-1][j] < min ) {
                    min = status[i-1][j];
                }
                status[i][j] = min + item[i][j];
            }
        }

        int index = -1;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++){
           if (status[n-1][i] < min){
               min = status[n-1][i];
               index = i;
           }
        }
        print(n-1,min,status);

        // 看看最低层
        System.out.println("每个节点最短路径");
        for (int i = 0;i< n;i++){
            System.out.println(status[n-1][i]);
        }
        return min;
    }

    private void print(int i,int min,int [][] status) {

        if (i <0){
            return;
        }
        for (int j =0;j<=i;j++){
            if (status[i][j] == min ){
                int itemStatus = item[i][j];
                print(i-1,min - itemStatus,status);

                System.out.println(itemStatus);
                break;
            }
        }
    }



    public static void main(String[] args) {
//        int shortest = new Triangle().getShortest(5);
//        System.out.println("---------" + shortest+ "-------");
        int i = new Triangle().yanghuiTriangle(item);
        System.out.println(i);
    }







// 评论答案，用作检验
    public int yanghuiTriangle(int[][] matrix) {
        int[][] state = new int[matrix.length][matrix.length];
        state[0][0] = matrix[0][0];
        for (int i = 1; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (j == 0) state[i][j] = state[i - 1][j] + matrix[i][j];
                else if (j == matrix[i].length - 1) state[i][j] = state[i - 1][j - 1] + matrix[i][j];
                else {
                    int top1 = state[i - 1][j - 1];
                    int top2 = state[i - 1][j];
                    state[i][j] = Math.min(top1, top2) + matrix[i][j];
                }
            }
        }
        int minDis = Integer.MAX_VALUE;
        for (int i = 0; i < matrix[matrix.length - 1].length; i++) {
            int distance = state[matrix.length - 1][i];
            if (distance < minDis) minDis = distance;
        }
        return minDis;
    }
}

package com.xianjinyi.gameProvider.leetcode.recall;

/**
 * @Author: xianjinyi
 * @date 2019/11/08
 * 回溯
 */
public class Recall {

    /**
     * 全局或成员变量,下标表示行,值表示queen存储在哪一列
     */
    int[] result = new int[8];
    int count =0;

    /**
     * 调用方式：cal8queens(0);
     * @param row
     */
    public void cal8queens(int row) {
        // 8个棋子都放置好了，打印结果
        if (row == 8) {
            printQueens(result);
            System.out.println("---"+ count++  +"----");
            return;
        }

        // 每一行都有8中放法 数组元素值column 表示列
        for (int column = 0; column < 8; ++column) {
            // 有些放法不满足要求
            if (isOk(row, column)) {
                // 第row行的棋子放到了column列
                result[row] = column;
                // 考察下一行
                cal8queens(row + 1);
            }
        }
    }

    /**
     * 判断row行column列放置是否合适
     *
     * @param row
     * @param column
     * @return
     */
    private boolean isOk(int row, int column) {
        //其他行的上一列 下一列
        int leftup = column - 1, rightup = column + 1;
        // 逐行往上考察每一行
        for (int i = row - 1; i >= 0; --i) {
            // 第i行的column列有棋子吗？
            if (result[i] == column) {
                return false;
            }
            // 考察左上对角线：第i行leftup列有棋子吗？
            // 距离多一层，对角线的列扩大1
            if (leftup >= 0) {
                if (result[i] == leftup) {
                    return false;
                }
            }
            // 考察右上对角线：第i行rightup列有棋子吗？
            if (rightup < 8) {
                if (result[i] == rightup) {
                    return false;
                }
            }
            --leftup;
            ++rightup;
        }
        return true;
    }


    /**
     * @param row 下标
     */
    public void my8Queen(int row){
        if(row == 8){
            printQueens(result);
            System.out.println("---"+ count++  +"----");
            return;
        }
        //不需要提前处理，当前行是逐个遍历的，是什么元素都不要紧
        //每次都只看自己前面行的，而前面行肯定是已经固定的（已经处理的）后面的是等着被覆盖的，也不要紧
//        if(row == 0){
//            for (int i=0;i< 8;i++){
//                result[i] =-1;
//            }
//        }


        // 当前行，应该放哪列，写错条件，如加了等号 <=8 就是 8*9的棋盘了
        for (int column = 0; column < 8; column++) {
            if (isOk2(row,column)){
                result[row] =column;
                my8Queen(row +1);
                // 不能break，才体现回溯思想，而且找到就break，当前这个找到，不代表就能走完全程
                // 并且在里面的递归已经处理完，接下来的循环递归并不会对上一次造成影响
                //break;
            }

        }

    }

    private boolean isOk2(int row, int column) {
        int left = column -1;
        int right = column + 1;

        // 跟之前每一行对比，其中left right 每次都往两边扩大一位
        for (int i = row -1;i>=0;i--){
            if (result[i] == column){
                return false;
            }

            if (left >= 0 && result[i] == left){
                return false;
            }
            if (right < 8 && result[i] == right){
                return false;
            }
            left--;
            right++;
        }

        return true;
    }


    public static void main(String[] args) {
//        new Recall().cal8queens(0);
        new Recall().my8Queen(0);
    }

    /**
     * 打印出一个二维矩阵
     *
     * @param result
     */
    private void printQueens(int[] result) {
        for (int row = 0; row < 8; ++row) {
            for (int column = 0; column < 8; ++column) {
                if (result[row] == column) {
                    System.out.print("Q ");
                } else {
                    System.out.print("* ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }
}

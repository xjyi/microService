package com.xianjinyi.gameProvider.leetcode;

/**
 * @Author: xianjinyi
 * @date 2019/10/29
 */
public class Permutation {

    // 调用方式：


    public static void main(String[] args) {
         int[]a = {1, 2, 3, 4};
         printPermutations(a, 4, 4);
    }


// k表示要处理的子数组的数据个数
    public static void printPermutations(int[] data, int n, int k) {
        if (k == 1) {
            for (int i = 0; i < n; ++i) {
                System.out.print(data[i] + " ");
            }
            System.out.println();
        }

        for (int i = 0; i < k; ++i) {
            int tmp = data[i];
            data[i] = data[k-1];
            data[k-1] = tmp;

            printPermutations(data, n, k - 1);

            // 不能少，换了位置，角标+1，之前换位置的可能再次被使用
            tmp = data[i];
            data[i] = data[k-1];
            data[k-1] = tmp;
        }
    }
}

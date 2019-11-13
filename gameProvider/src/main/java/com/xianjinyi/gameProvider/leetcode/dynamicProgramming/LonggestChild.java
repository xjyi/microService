package com.xianjinyi.gameProvider.leetcode.dynamicProgramming;

/**
 * @author: xianjinyi
 * @date 2019/11/13
 */
public class LonggestChild {

    // 我们有一个数字序列包含n个不同的数字，如何求出这个序列中的最长递增子序列长度？
    // 比如2, 9, 3, 6, 5, 1, 7这样一组数字序列，
    // 它的最长递增子序列就是2, 3, 5, 7，所以最长递增子序列的长度是4。

    // 每次取一个元素进行判断，是多阶段（无后效性的不关心状态来源，以及后续不影响当前状态一般都符合）
    // 可以根据子问题推导当前问题

    // 只剩下是否有重复子问题 有就动态规划，没就回溯
    // i 是每一个数字，j 是总长度 ，在相同数字，长度也相同时，有可能是几个不同的数字组成的，所以存在相同子问题

   public static int[] items = new int[]{2, 9, 3, 6, 5, 1, 7};

    /**
     * 二维数组，上下元素，断层后，当前层要往前找
     *
     * @return
     */
   public int getLongest(){
       int length = items.length;
       int[] status = new int[length];

       status[0] = 1;
       for (int i = 0; i < length; i++) {
           for (int j = i; j > 0; j--) {
                if (items[i] > items[j-1]){
                    status[i] = status[j-1]+1;
                }
           }
       }



       return 0;
   }
}

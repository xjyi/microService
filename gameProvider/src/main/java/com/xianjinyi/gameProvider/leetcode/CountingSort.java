package com.xianjinyi.gameProvider.leetcode;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;

/**
 * @Author: xianjinyi
 * @date 2019/10/21
 *  计数排序
 */

@Slf4j
public class CountingSort {


    public static void main(String[] args) {
//        int[] aa = {1, 33, 2, 4, 7, 8, 7, 9};
        int[] aa = {9,8,7,6,5,4,3,2,1};
        
        
        log.info("原数组" + JSON.toJSONString(aa));
        countingSort(aa);
        log.info("有序数组" + JSON.toJSONString(aa));
    }

    /**
     * 都是正整数
     * @param aa
     */
    private static void countingSort(int[] aa) {

        int n = aa.length;


        int min = aa[0];
        int max = aa[0];

        // 获取最大最小值
        for (int i =1;i<n;i++) {
            // 能保证是正整数的话，min就是0即可，
            if(aa[i] < min){
                min = aa[i];
                continue;
            }
            if(aa[i] > max){
                max = aa[i];
            }
        }

        int[] countArray = new int[max - min + 1];
        // 第一次遍历，计算各个元素个数
        for (int i = 0; i < n; i++) {
            countArray[aa[i] -min]++;
        }

        // 第二次遍历，累计个数
        for (int i =1;i<countArray.length;i++) {
            countArray[i]=countArray[i-1] + countArray[i];
        }

        int[] temps = new int[n];
        // 定位元素位置
        for (int i = n-1;i>=0;i--){
            int j =aa[i] - min;

            int position = countArray[j] - 1;
            temps[position] = aa[i];
            countArray[j] = countArray[j]-1;
        }
        // 临时数组赋值回原数组
        for (int i =0;i<n;i++) {
            aa[i]= temps[i];
        }


    }

















}

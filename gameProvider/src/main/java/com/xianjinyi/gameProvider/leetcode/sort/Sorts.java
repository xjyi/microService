package com.xianjinyi.gameProvider.leetcode.sort;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;

/**
 * @Author: xianjinyi
 * @date 2019/10/15
 */
@Slf4j
public class Sorts {


    /**
     * 冒泡
     *
     * @param nums
     */
    public static void bubbleSort(int[] nums) {
        log.info("原数组" + JSON.toJSONString(nums));
        int sizes = nums.length;
        int temp;

        for (int i = 0; i < sizes - 1; i++) {
            log.info("" + i);
            boolean swap = false;
            for (int j = 0; j < sizes - 1 - i; j++) {
                if (nums[j] > nums[j + 1]) {
                    temp = nums[j];
                    nums[j] = nums[j + 1];
                    nums[j + 1] = temp;
                    swap = true;
                }

            }
            // 已经有序
            if (!swap) {
                break;
            }
        }

        log.info("有序数组" + JSON.toJSONString(nums));
    }


    /**
     * insert
     *
     * @param nums
     */
    public static void insertSort(int[] nums) {
        log.info("原数组" + JSON.toJSONString(nums));
        int sizes = nums.length;

        for (int i = 1; i < sizes; i++) {

            for (int j = 0; j < i; j++) {

                if (nums[j] > nums[i]) {
                    // 从j开始，往后移动一位
                    int temp = nums[i];
                    for (int k = i; k > j; k--) {
                        nums[k] = nums[k - 1];
                    }
                    nums[j] = temp;
                    break;
                }

            }

        }
        log.info("有序数组" + JSON.toJSONString(nums));
    }


    public static void main(String[] args) {
//        int[] aa = {1, 33, 2, 4, 7, 8, 7, 9};
        int[] aa = {9,8,7,6,5,4,3,2,1};
//        bubbleSort(aa);
//        insertSort2(aa);
        log.info("原数组" + JSON.toJSONString(aa));
//        selectSort(aa);
        //mergeSort(aa);
        log.info("有序数组" + JSON.toJSONString(aa));
    }









    public static void selectSort(int[] nums) {
        log.info("原数组" + JSON.toJSONString(nums));
        int sizes = nums.length;

        for (int i = 0; i < sizes; i++) {
            int min = i;
            int minvalue = nums[min];
            for (int j = i+1; j < sizes; j++) {

                if(nums[j] < minvalue){
                    min = j;
                    minvalue = nums[j];
                }
            }

            nums[min] = nums[i];
            nums[i] = minvalue;

        }
        log.info("有序数组" + JSON.toJSONString(nums));
    }

        /**
         * Sort1 比较的不用移动（除了临界点），移动的不用比较
         * Sort2每一次比较的都要移动。代码更简洁
         * @param nums
         */
        public static void insertSort2( int[] nums){
            log.info("原数组" + JSON.toJSONString(nums));
            int sizes = nums.length;

            for (int i = 1; i < sizes; i++) {

                // 防止被覆盖
                int a = nums[i];
                int j = i - 1;
                for (; j >= 0; j--) {
                    if (nums[j] > a) {
                        nums[j + 1] = nums[j];
                    } else {

                        break;
                    }
                }
                // 循环外赋值，防止当前循环的值比有序的全部都小
                nums[j + 1] = a;

            }
            log.info("有序数组" + JSON.toJSONString(nums));
        }



}
package com.xianjinyi.gameProvider.leetcode;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;

/**
 * @Author: xianjinyi
 * @date 2019/10/15
 */
@Slf4j
public class Sorts {


    public static void main(String[] args) {
        int[] aa = {1,33,2,4,7,8,7,9};
        bubbleSort(aa);
    }


    public static void bubbleSort (int[] nums ){
        log.info("原数组" + JSON.toJSONString(nums));
        int sizes = nums.length;
        int temp ;

        for(int i =0;i<sizes;i++){
            for(int j =0;j<sizes-i;j++){
                if (nums[j] > nums[j+1]){
                    temp = nums[j];
                    nums[j] = nums[j+1] ;
                    nums[j+1] = temp ;
                }
            }
        }

        log.info("有序数组" + JSON.toJSONString(nums));
    }



}
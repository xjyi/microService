package com.xianjinyi.gameProvider.leetcode;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MergeSort {



    /**
     * 归并排序
     * @param nums
     */
    public static void mergeSort(int[] nums) {

        int n = nums.length;
        mergeSortHandle(nums,0,n-1);
    }

    /**
     *
     * @param nums
     * @param p 开始角标
     * @param r 结束角标
     */
    private static void mergeSortHandle(int[] nums, int p, int r) {

        if (p==r){
            return;
        }
        int q = (p+r)/2;



        mergeSortHandle(nums,p,q);
        mergeSortHandle(nums,q+1,r);
        merge(nums,p,q,r);
    }


    private static void merge(int[] nums, int p,int q,int r) {
        int temp[] = new int[r-p+1];
        int cur = 0;
        int left = p;
        int right = q+1;

        while(left<=q && right<=r){
            // 等号不能少，决定了此算法是否是稳定的
            if(nums[left] <= nums[right]){
                temp[cur++] = nums[left];
                left++;
            }else{
                temp[cur++] = nums[right];
                right++;
            }
        }
        left = left >q?right:left;
        for(int i=cur;i<r-p+1;i++){
            temp[cur++] = nums[left++];
        }

        cur=p;
        for(int i=0;i<r-p+1;i++,cur++){
            nums[cur] = temp[i];

        }


    }

    public static void main(String[] args) {
//        int[] aa = {1, 33, 2, 4, 7, 8, 7, 9};
        int[] aa = {9,8,7,6,5,4,3,2,1};
        log.info("原数组" + JSON.toJSONString(aa));
        mergeSort(aa);
        log.info("有序数组" + JSON.toJSONString(aa));
    }
}

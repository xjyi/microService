package com.xianjinyi.gameProvider.leetcode.sort;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MergeSort {


    public static void main(String[] args) {
//        int[] aa = {1, 33, 2, 4, 7, 8, 7, 9};
        int[] aa = {9,8,7,6,5,4,3,2,1,1,1,15,24,86,14,96};
        log.info("原数组" + JSON.toJSONString(aa));
        bucketSort(aa,3);
        log.info("有序数组" + JSON.toJSONString(aa));

//        int[] ints = new int[10];
//        log.info("长度：" + ints.length);
    }
    /**
     * 桶排序
     * 每个桶使用归并，保证算法稳定性
     *
     * 每次传入的数据范围不同，则桶的数量也不同，范围不好估计使用桶大小估计
     * 桶的数量越接近数据范围（数量与桶大小成反比），越接近O(n)
     * 假设都是正整数
     * @param nums
     */
    public static void bucketSort(int[] nums,int bucketSizes) {

        // 除法（不看小数）。保证每个桶之间的有序性

        int n = nums.length;


        int max = nums[0];

        // 获取最大值
        for (int i =1;i<n;i++) {
            // 能保证是正整数的话，min就是0即可，
            if(nums[i] > max){
                max = nums[i];
            }
        }


        // 桶的数量
        int i = max / bucketSizes +1 ;
        int[][] buckets = new int[i][bucketSizes];
        // 标记每个桶已装数据的角标
        int[] bucketCount = new int[i];


        for (int j=0;j<n;j++){
            // 归属哪个桶
            int index = nums[j] / bucketSizes;
            if(bucketCount[index] ==buckets[index].length ){
                // 扩容
                ensureSize(buckets,index);
            }
            buckets[index][bucketCount[index]++] = nums[j];
        }


//        for (int k=0;k<i;k++){
//
//        }
        int l =0;
        for (int k=0;k<i;k++){
            int[] bucket = buckets[k];
            //log.info("-----每一组排序前：{}",JSON.toJSONString(bucket));
            if (bucketCount[k] == 0){continue;}
            mergeSortHandle(bucket,0,bucketCount[k]-1);
            //log.info("每一组排序后：{}",JSON.toJSONString(bucket));

            for (int j=0;j<bucketCount[k];j++){
                nums[l] = bucket[j];
                l++;
            }
        }

    }

    private static void ensureSize(int[][] buckets, int index) {
        int[] bucket = buckets[index];
        int length = bucket.length;

        int[] temps = new int[length * 2];
        for (int i=0;i<length;i++) {
            temps[i]=bucket[i];
        }
        buckets[index] = temps;
    }


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


}

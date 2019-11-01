package com.xianjinyi.gameProvider.leetcode;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;

/**
 * @Author: xianjinyi
 * @date 2019/11/01
 */
@Slf4j
public class MyMergeSort {


    public static void main(String[] args) {
        int[] arr = {12, 3, 2, 1, 4, 6, 5, 8, 7};
        System.out.println(JSON.toJSONString(arr));
        myMerge(arr);
        System.out.println(JSON.toJSONString(arr));
    }


    public static void  myMerge(int[] aa){
        // 默认数组是满的
        int n = aa.length;

        toMerge(aa,0,n-1);
    }

    private static void toMerge(int[] aa, int left, int rigth) {
        if(left >= rigth){
            return;
        }
        int mid = left + ((rigth-left)>>1);
        toMerge(aa,left,mid);
        toMerge(aa,mid+1,rigth);
        merge(aa,left,rigth,mid);
    }

    private static void merge(int[] aa, int left, int rigth,int mid) {
        int[] temp = new int[rigth - left +1];
        int middle = mid ;
        int low = left ;
        mid = mid+1;


        for (int i =0; i<rigth - low +1 ;i++){

            if(left <= middle && mid <= rigth){
                if(aa[left] <= aa[mid]){
                    temp[i] = aa[left];
                    left = left+1;
                }else{
                    temp[i] = aa[mid];
                    mid = mid+1;
                }
                continue;
            }


            if (left > middle){
                temp[i] = aa[mid];
                mid = mid+1;
            }else{
                temp[i] = aa[left];
                left = left+1;
            }


        }


        int j =0;
        for (int i =low; i<=rigth ;i++,j++){
            aa[i] = temp[j];
        }

    }

}

package com.xianjinyi.gameProvider.leetcode.sort;


import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class QuickSort {

    public static void main(String[] args) {
//        int[] aa = {1, 33, 2, 4, 7, 8, 7, 9};
        int[] aa = {9,8,7,6,5,4,3,2,1};
        log.info("原数组" + JSON.toJSONString(aa));
        quickSort(aa);
        log.info("有序数组" + JSON.toJSONString(aa));
    }

    private static void quickSort(int[] aa) {
        int n = aa.length;

        handleQuick(aa,0,n-1);


    }

    private static void handleQuick(int[] aa, int p, int r) {
        if (p>=r)
            return;


        int pivot = partition(aa,p,r);
        handleQuick(aa,p,pivot-1);
        handleQuick(aa,pivot+1,r);
    }

    private static int partition(int[] aa,int p ,int r) {

        int j=p;
        int temp;
        for (int i = p;i<r;i++){
            if(aa[i] < aa[r]){
                if(i==j){
                    j++;

                    continue;
                }

                 temp = aa[i];
                aa[i] = aa[j];
                aa[j] = temp;
                j++;
            }
        }

        temp = aa[j];
        aa[j] = aa[r];
        aa[r] = temp;
        return j;
    }


}

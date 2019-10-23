package com.xianjinyi.gameProvider.leetcode;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;

/**
 * @Author: xianjinyi
 * @date 2019/10/23
 */
@Slf4j
public class BinarySearch {


    public static void main(String[] args) {
        //System.out.println(4>>1);

        int[] aa = {0,1,1,2,3,5,6,7,9};
        log.info("原数组" + JSON.toJSONString(aa));
        int i = bSearchRepeat(aa, 1);
        log.info("角标：{}",i);
    }




    /**
     *  二分变体(简约版，困难版)
     *  有序数组，元素可能重复
     *  获取第一个等于给定值的角标
     */
    public static int bSearchRepeat2(int aa[],int value){
        int n = aa.length;
        int low = 0;
        int high = n-1;

        while(low <= high){
            int mid = low + ((high-low)>>1);
            if (aa[mid] >= value ){
                // value可能在前面。也可能是当前
                high = mid - 1;
            }else{
                low = mid+1;
            }
        }



        return  -1;
    }


    /**
     *  二分变体
     *  有序数组，元素可能重复
     *  获取第一个等于给定值的角标
     */
    public static int bSearchRepeat(int aa[],int value){
        int n = aa.length;
        int low = 0;
        int high = n-1;

        while(low <= high){
            int mid = low + ((high-low)>>1);
             if (aa[mid] > value ){
                 high = mid-1;
             }else if (aa[mid] < value){
                 low = mid+1;
             }else{
                 if(mid == 0 || aa[mid-1] !=value){
                     return mid;
                 }
                 high = mid-1;
             }
        }



        return  -1;
    }



    /**
     * 简单二分
     *  有序数组，元素不重复
     *  非递归
     */
    public static int bSearch2(int aa[],int value){
        int n = aa.length;

        int min = 0;
        int max = n-1;
        while(min <= max){
            int mid = min + ((max - min)>>1);
            if(aa[mid] == value){
                return mid;
            }else if (aa[mid] > value){
                max = mid-1;
            }else{
                min = mid+1;
            }
        }

        return  -1;
    }


    /**
     * 简单二分
     *  有序数组，元素不重复
     * 递归方式
     */
    public static int bSearch(int aa[],int value){
          int n = aa.length;
          return  bSearchRecursion(aa,0,n-1,value);
    }

    private static int bSearchRecursion(int[] aa, int min, int max,int value) {


        if (min>max){
            return -1;
        }
//        if(min == max && aa[min]!=value){
//            return -1;
//        }
        // 二分查找效率高，次数多没有关系，且命中两端的概率不高，可此处不做判断
//        if(aa[min] == value){
//            return min;
//        }else if (aa[max] == value){
//            return max;
//        }

       int mid = min + ((max-min)>>1);
       if(aa[mid] == value ){
           return mid;
       }else if(aa[mid]< value){
           return bSearchRecursion(aa,mid+1,max,value);
       }else{
           return bSearchRecursion(aa,min,mid-1,value);
       }
    }
}

package com.xianjinyi.gameProvider.leetcodeclasswork;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class ArrayAlgorithm {

    /**
     * 1. 实现一个支持动态扩容的数组
     */
    class MyArray{
        /**
         * 大小
         */
        private int n;
        private int resizeFactor = 2;
        private volatile int index;

        int[] arr;

        public  MyArray( int n){
            this.n = n;
            arr = new int[n];
        }

        //  暂不考虑线程安全
        public boolean add(int v){
            if (index == n-1){
                myResize();
            }
            arr[index +1] = v;
            index ++;
            return true;
        }

        private void myResize() {
            int[] newArr = new int[n * resizeFactor];
            for (int i = 0; i < n; i++) {
                newArr[i] = arr[i];
            }
            n = n * resizeFactor;
            arr = newArr;
        }

    }


    /**
     * 一个大小固定的有序数组，支持动态增删改操作
     */
    class OrderlyArray{
        int[] arr;
        private int length;


        public OrderlyArray(int n){
            arr = new int[100];
        }


        public boolean add (int value){
            if(length == arr.length){
                return false;
            }
            int position = getPosition(value, 0, length - 1);
            for (int i=length -1 ;i >=position;i--){
                arr[i+1] = arr[i];
            }
            arr[position] = value;
            length++;
            return true;
        }


        public boolean update (int value){

            int updatePosition = getUpdatePosition(value, 0, length - 1);
            if (updatePosition !=-1){
                arr[updatePosition] = value;
            }
            return true;
        }

        public boolean remove (int value){

            int updatePosition = getUpdatePosition(value, 0, length - 1);
            if (updatePosition !=-1){
                for (int i =updatePosition;i<length-1; i++){
                    arr[i] = arr[i+1];
                }
            }
            return true;
        }


        // 不使用递归
        private int getUpdatePosition(int value, int left, int right) {
            int low = left;
            int high = right;

            while (true){
                if (low >high){
                    return -1;
                }
                int mid = low + ((high - low) >> 1);
                if (arr[mid] == value){
                    return mid;
                }else if (arr[mid] > value){
                    low = mid+1;
                }else{
                    high = mid;
                }
            }


        }

        // 删除，修改 针对数据不重复的情况

        /**
         * 递归方式
         * 返回理应插入的下标,此位置（包含当前位置）全部往后移动
         * 找到第一个比当前的大的
         * @param value
         * @return
         */
        private int getPosition(int value,int left,int right) {
            if (arr[left] > value){
                return left;
            }

            if (arr[right] < value){
                return right +1;
            }


            int mid = left + ((right - left) >> 1);
            if (mid == left){
                // 区间左右两数相邻
                return right;
            }


            if ( arr[mid] >value ){
                return getPosition( value, left,mid);

            }else {
                return getPosition( value, mid+1,right);
            }
        }


    }

    /**
     * 三数之和
     */
    class Solution {
        public List<List<Integer>> threeSum(int[] nums) {
            List aa =new ArrayList<List<Integer>>();
            int n = nums.length;
            Arrays.sort(nums);

            for (int i=0;i<n;i++){
                int left = i+1;
                int right = n-1;

                // 当前有序，i是最小的一个
                if (nums[i] >0){
                    break;
                }
                // 每个i都已经匹配过后面所有可能的元素，相同的i则说明重复
                if (i>0 && nums[i] ==nums[i-1] ){
                    continue;
                }

                while(left<right){
                    int sum = nums[i] + nums[left] + nums[right];
                    if (sum==0){
                        ArrayList<Integer> integers = new ArrayList<>();
                        integers.add(nums[i]);
                        integers.add(nums[left]);
                        integers.add(nums[right]);
                        aa.add(integers);

                        // 因为while是连续多个
                        while (left <right && nums[left] == nums[left+1]){
                            left++;
                        }
                        while (left <right && nums[right] == nums[right-1]){
                            right--;
                        }

                        left++;
                        right--;
                    }else if (sum<0){
                        // 非必需
//                        while (left <right && nums[left] == nums[left+1]){
//                            left++;
//                        }
                        left++;
                    }else{

//                        while (left <right && nums[right] == nums[right-1]){
//                            right--;
//                        }
                        right--;
                    }
                }
            }
            return aa;
        }
    }

    /**
     * 求众数
     */
    public int majorityElement(int[] nums) {
        if (nums.length == 1){
            return nums[0];
        }

        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])){
                Integer size = map.get(nums[i]);
                if (size +1 > nums.length/2){
                    return nums[i];
                }
                map.put(nums[i], size+1);
            }else{
                map.put(nums[i], 1);
            }
        }
        return -1;
    }
}

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
     *
     * 还有投票法，见leetcode
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

    /**
     * 缺失的第一个正数
     *
     * 时间复杂度应为O(n)，并且只能使用常数级别的空间 O(1)
     *
     *
     * 负数，大于n的，重复的，这些数值没有意义，所以不换位置，其他的换到正确位置，每个值都处理完后，第一个没有在正确位置的，就是解
     *
     * 原理跟使用负数的差不多，这种更容易想到。且不用处理1，这种特殊情况
     * @param nums
     * @return
     */
    public int firstMissingPositive(int[] nums) {
        // n个数据，最极端的情况，也是n个连续的正数，否则就有空洞
        // 最小缺失正数必定 <= n+1


        int n = nums.length;
        // 基于相同的思想，不额外增加空间，那就交换位置
        for (int i = 0; i < n; i++) {
            // 处理当前i位置的元素,换完之后，继续判断当前i位置是否需要处理
            // nums[i]!=i+1  不行，因为i+1这个数可能并不存在，
            // 这个条件只能证明当前的位置不对，但是也不足以 作为交换位置的根据，只能根据值找他应该存在的位置（该位置如果已经有一个跟当前一样的数据，那就不需交换了）
            // 数组数据不重复的情况下也是没问题的，但是数据有重复，则会造成死循环
            while(nums[i] >0 && nums[i]<=n && nums[nums[i] - 1]!= nums[i]){
                swap(nums,i,nums[i]);
            }
        }

        for (int i = 0; i < n; i++) {
            if (nums[i] !=i+1){
                return i+1;
            }
        }





        // 以下是O(n)的解法，不符合要求
        // 1~n的存储
//        boolean[] status = new boolean[n];
//        for (int i = 0; i < n; i++) {
//            if (nums[i] >0 && nums[i] <=n){
//                status[nums[i]-1] = true;
//            }
//        }
//
//        for (int i = 0; i < n; i++) {
//            if (!status[i]){
//                return i+1;
//            }
//        }
        return n+1;
    }

    private void swap(int[] nums, int index1, int index2) {
//        int temp = nums[i];
//        nums[i] = nums[num];
//        nums[num] = temp;


        nums[index1] = nums[index1] ^ nums[index2];
        nums[index2] = nums[index1] ^ nums[index2];
        nums[index1] = nums[index1] ^ nums[index2];

        // 一般就用一个temp即可
//        如果 a ^ b = c ，那么 a ^ c = b 与 b ^ c = a 同时成立，利用这一条，可以用于交换两个变量的值。
        // 相同的为0，不同的为1

//        对于异或运算实现的交换方法，如果调用 swap(nums, i, i)，那么最终的结果会变为 0。
//        对于加减法实现的交换方法，有可能发生溢出。
    }


    /**
     * 同样是 0，负数，大于n的数转为为1（先确保1已经存在，不是解）
     * 此时数组中都是正整数，且都在1~n范围内
     * 则遍历数组，把数值对应的下标置为负数（表示该数已经存在，但是又不影响使用该数据的使用（负数用的时候取绝对值））多出来的0，n刚好互补
     * 数组中某个元素为正数，说明没有值对应这个下标，就是解
     * @param nums
     * @return
     */
    public int firstMissingPositive1(int[] nums) {
        int n = nums.length;

        // 基本情况
        int contains = 0;
        for (int i = 0; i < n; i++)
            if (nums[i] == 1) {
                contains++;
                break;
            }

        if (contains == 0)
            return 1;

        // nums = [1]
        if (n == 1)
            return 2;

        // 用 1 替换负数，0，
        // 和大于 n 的数
        // 在转换以后，nums 只会包含
        // 正数
        for (int i = 0; i < n; i++)
            if ((nums[i] <= 0) || (nums[i] > n))
                nums[i] = 1;

        // 使用索引和数字符号作为检查器
        // 例如，如果 nums[1] 是负数表示在数组中出现了数字 `1`
        // 如果 nums[2] 是正数 表示数字 2 没有出现
        for (int i = 0; i < n; i++) {
            int a = Math.abs(nums[i]);
            // 如果发现了一个数字 a - 改变第 a 个元素的符号
            // 注意重复元素只需操作一次
            if (a == n)
                nums[0] = - Math.abs(nums[0]);
            else
                nums[a] = - Math.abs(nums[a]);
        }

        // 现在第一个正数的下标
        // 就是第一个缺失的数
        for (int i = 1; i < n; i++) {
            if (nums[i] > 0)
                return i;
        }

        if (nums[0] > 0)
            return n;

        return n + 1;
    }


}

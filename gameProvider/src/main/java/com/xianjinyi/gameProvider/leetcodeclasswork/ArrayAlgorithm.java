package com.xianjinyi.gameProvider.leetcodeclasswork;

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


}

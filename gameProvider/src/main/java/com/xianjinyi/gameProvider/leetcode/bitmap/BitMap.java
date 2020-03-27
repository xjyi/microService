package com.xianjinyi.gameProvider.leetcode.bitmap;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class BitMap {


    private char[] bytes;
    // 数组个数 * 8
    private int nbits;

    public BitMap(int nbits) {
        this.nbits = nbits;
        this.bytes = new char[nbits / 8 + 1];
    }


    public void set(int k) {
        if (k > nbits) {
            return;
        }
        int byteIndex = k / 8;
        int bitIndex = k % 8;
        bytes[byteIndex] |= (1 << bitIndex);
    }



    public boolean get(int k) {
        if (k > nbits) {
            return false;
        }
        int byteIndex = k / 8;
        int bitIndex = k % 8;
        return (bytes[byteIndex] & (1 << bitIndex)) != 0;
    }

    public static void main(String[] args) {
        int[][] matrix = {
                {1,4,7,11,15},
                {2,5,8,12,19},
                {3,6,9,16,22},
                {10,13,14,17,24},
                {18,21,23,26,30}};
        System.out.println(findNumberIn2DArray (matrix,5));;
    }



    public static boolean findNumberIn2DArray(int[][] matrix, int target) {


        // 二维数组长度
        int m = matrix.length;
        if(m==0){
            return false;
        }
        int n = matrix[0].length;
        if(n==0){
            return false;
        }
        int i =0;
        int j =0;

        boolean[][] flag = new boolean[m][n];

        // 使用广度优先
        LinkedList<int[]> queue = new LinkedList<int[]>();
        queue.add(new int[]{0,0});
        while(queue.size()>0){
            int[] poll = queue.poll();
            int tar = matrix[poll[0]][poll[1]];
             i = poll[0];
             j = poll[1];
            System.out.println("poll:"+poll);
            if (tar  == target){
                return true;
            }else if (tar > target){
                continue;
            }else{

                if (i+1 <m && (!flag[i+1][j])){
                    flag[i+1][j] = true;
                    queue.add(new int[]{i+1,j});
                    System.out.println(i+1 + "-----"+j +"==="+matrix[i+1][j]);
                }
                if (j+1 <n && (!flag[i][j+1])){
                    flag[i][j+1] = true;
                    queue.add(new int[]{i,j+1});
                    System.out.println(i + "-----"+(j+1)+"==="+matrix[i][j+1]);
                }
            }

        }
        return false;
    }

}

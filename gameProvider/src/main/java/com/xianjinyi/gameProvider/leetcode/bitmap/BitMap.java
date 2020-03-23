package com.xianjinyi.gameProvider.leetcode.bitmap;

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
        System.out.println(movingCount(38,15,9));;
    }

    static int[][] aa;
    public static  int movingCount(int m, int n, int k) {
         aa = new int[m][n];

         // 初始化
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                aa[i][j] = -1;
            }
        }

        int count = 0;
        toCount(0, 0, m, n, k);


        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if ( aa[i][j] ==-1){
                    continue;
                }
                count += aa[i][j];
            }
        }
        return count;
    }

    // 不能越过不可通过的格子
    // 循环遍历容易越过不能通过的边界
    // 递归容易重复统计格子


    public static void toCount(int i,int j,int m, int n, int k) {
        int a = i/100 + i/10 + i%10;
        int b = j/100 + j/10 + j%10;

        int i1= i+1;
        int j1= j+1;
        int a1 = i1/100 + i1/10 + i1%10;
        int b1 = j1/100 + j1/10 + j1%10;

        if (i == m || j == n || i < 0 || j < 0) {
            return;
        }
        if (aa[i][j] != -1) {
            return;
        }

        if (a +b > k ){
            aa[i][j] = 0;
            return;
        }
        aa[i][j] = 1;

        // 上下左右
        if (a1+b <= k ){
            toCount(i1,j,m,n,k);
        }

        if (a+b1 <= k ){
            toCount(i,j1,m,n,k);
        }


    }
}

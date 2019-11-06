package com.xianjinyi.gameProvider.leetcode.stringSearch;

import com.alibaba.fastjson.JSON;

public class Kmp {















    public static int mykmp(char[] a, int n, char[] b, int m) {

        int[] next = getMyNexts(b,m);
        int j =0;
        for(int i = 0;i< n; i++){
            while(j>0 && a[i] != b[j]){
                j = next[j-1]+1;
            }
            // 走到这步，前面肯定对齐了，或者都是首位匹配（主串此时的首位可能就是前面已经检查的部分都不匹配，重新开始从头匹配）
            if (a[i] == b[j]){
                j++;
            }
            if(j == m){return i - m+1;}

        }

        return 0;
    }

//    public static void main(String[] args) {
//        char[] chars = new char[]{'a','b','a','b','a','c','d'};
//
//        int[] myNexts = getMyNexts(chars, chars.length);
//        System.out.println(JSON.toJSONString(myNexts));
//
//        System.out.println("------------");
//        int[] nexts = getNexts(chars, chars.length);
//        System.out.println(JSON.toJSONString(nexts));
//    }
    private static int[] getMyNexts(char[] b, int m) {
        int[] next = new int[m];

        next[0] =-1;
        // 不存在是-1，所以-1开始
        // j 同时也是下标，所以用于下标时 不能是[j]
        int j = -1;
        // 前缀子串从1开始
        for (int i = 1; i < m; i++) {
            while(j>-1 && b[i]!=b[j+1]){
                // j此时代表坏字符前一个，
                // 当前刚好出现坏字符，所以拿好前缀（比当前小1）的最长后缀next[j],作为新的j ，比较的是j+1
                j = next[j];
            }
             // 退出while循环 表示b[i]==b[j]
            // 或者j == -1
            if(b[i]==b[j+1]){
                j++;
            }
            next[i] = j;


        }

        return next;
    }


    /**
     * a, b分别是主串和模式串；n, m分别是主串和模式串的长度。
     * @param a
     * @param n
     * @param b
     * @param m
     * @return
     * bm中移动的是主串，kmp中移动的是模式串
     * 遇到不匹配的，模式串会用自己更靠前的好前缀子串去匹配，主串不动，相对就是模式串往后移动了
     *
     */
    public static int kmp(char[] a, int n, char[] b, int m) {
        int[] next = getNexts(b, m);
        int j = 0;
        for (int i = 0; i < n; ++i) {
            // 一直找到a[i]和b[j]
            // j 对不上，则找j前面已经匹配好的好前缀，取好前缀的最长好后缀
            // 此时新的j是好后缀最后一个下标+1，重新对比，直到没有后缀，或者两者相等
            while (j > 0 && a[i] != b[j]) {
                j = next[j - 1] + 1;
            }
            if (a[i] == b[j]) {
                ++j;
            }
            // 找到匹配模式串的了
            if (j == m) {
                return i - m + 1;
            }
        }
        return -1;
    }

    /**
     * b表示模式串，m表示模式串的长度
     * @param b
     * @param m
     * @return
     */
    private static int[] getNexts(char[] b, int m) {
        // 数组元素默认0
        int[] next = new int[m];
        next[0] = -1;
        int k = -1;

        // 每个前缀子串
        // 每次开启新的循环，k是上一个最好前缀子串的最长匹配前缀
        for (int i = 1; i < m; ++i) {

            // 下一个字符不相等，找0到next(k) 的最长串
            // k = -1 说明上一个好前缀子串，没有最长的可匹配后缀
            while (k != -1 && b[k + 1] != b[i]) {
                k = next[k];
            }
            if (b[k + 1] == b[i]) {
                ++k;
            }
            next[i] = k;
        }
        return next;
    }
}

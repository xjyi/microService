package com.xianjinyi.gameProvider.leetcode.stringSearch;

public class Kmp {

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

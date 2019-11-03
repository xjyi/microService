package com.xianjinyi.gameProvider.leetcode.stringSearch;

public class Bm {


    private static final int SIZE = 256; // 全局变量或成员变量


    /**
     * 记录模式串中每个字符最后出现的位置
     *
     *
     * 变量b是模式串，m是模式串的长度，bc表示刚刚讲的散列表。
     * @param b
     * @param m
     * @param bc
     */
    private void generateBC(char[] b, int m, int[] bc) {
        // 初始化bc
        for (int i = 0; i < SIZE; ++i) {
            bc[i] = -1;
        }

        // 计算b[i]的ASCII值
        // 记录模式串中每个字符最后出现的位置
        for (int i = 0; i < m; ++i) {
            int ascii = (int) b[i];
            bc[ascii] = i;
        }
    }


    // bm算法
    // a,b表示主串和模式串；n，m表示主串和模式串的长度。
    public int bm2(char[] a, int n, char[] b, int m) {
        int[] bc = new int[SIZE]; // 记录模式串中每个字符最后出现的位置
        generateBC(b, m, bc); // 构建坏字符哈希表
        int[] suffix = new int[m];
        boolean[] prefix = new boolean[m];
        generateGS(b, m, suffix, prefix);
        int i = 0; // j表示主串与模式串匹配的第一个字符
        while (i <= n - m) {
            int j;
            for (j = m - 1; j >= 0; --j) { // 模式串从后往前匹配
                if (a[i+j] != b[j]) break; // 坏字符对应模式串中的下标是j
            }
            if (j < 0) {
                return i; // 匹配成功，返回主串与模式串第一个匹配的字符的位置
            }
            int x = j - bc[(int)a[i+j]];
            int y = 0;
            if (j < m-1) { // 如果有好后缀的话
                y = moveByGS(j, m, suffix, prefix);
            }
            i = i + Math.max(x, y);
        }
        return -1;
    }

    // j表示坏字符对应的模式串中的字符下标; m表示模式串长度
    private int moveByGS(int j, int m, int[] suffix, boolean[] prefix) {
        int k = m - 1 - j; // 好后缀长度
        if (suffix[k] != -1) return j - suffix[k] +1;

        // j是坏字符串下标
        // j+1是好后缀的第一位下标，从第二位下标开始看，有没有符合的前缀
        for (int r = j+2; r <= m-1; ++r) {
            // 最后的下标是 m-1， m-1 -r+1 = m-r
            if (prefix[m-r] == true) {
                return r;
            }
        }
        return m;
    }


    /**
     * 预处理
     * @param b
     * @param m
     * @param suffix
     * @param prefix
     */
    // b表示模式串，m表示长度，suffix，prefix数组事先申请好了
    private void generateGS(char[] b, int m, int[] suffix, boolean[] prefix) {
        for (int i = 0; i < m; ++i) { // 初始化
            suffix[i] = -1;
            prefix[i] = false;
        }


        // i往前推进，模式串的子串范围变大
        // 每个子串都与整个模式串匹配，相同长度的公共子串，后面会覆盖前面的
        for (int i = 0; i < m - 1; ++i) { // b[0, i]
            int j = i;
            int k = 0; // 公共后缀子串长度
            while (j >= 0 && b[j] == b[m-1-k]) { // 与b[0, m-1]求公共后缀子串
                --j;
                ++k;
                suffix[k] = j+1; //j+1表示公共后缀子串在b[0, i]中的起始下标 i
            }

            if (j == -1) {
                prefix[k] = true; //如果公共后缀子串也是模式串的前缀子串
            }
        }
    }
















    @Deprecated
    public int bm(char[] a, int n, char[] b, int m) {
        // 构建坏字符哈希表
        // 记录模式串中每个字符最后出现的位置
        int[] bc = new int[SIZE];
        generateBC(b, m, bc);

        // i表示主串与模式串对齐的第一个字符
        int i = 0;

        while (i <= n - m) {
            int j;

            // 模式串从后往前匹配
            // 坏字符对应模式串中的下标是j
            for (j = m - 1; j >= 0; --j) {
                if (a[i + j] != b[j]){
                    break;
                }
            }

            if (j < 0) {
                return i; // 匹配成功，返回主串与模式串第一个匹配的字符的位置
            }
            // 这里等同于将模式串往后滑动j-bc[(int)a[i+j]]位
            i = i + (j - bc[(int) a[i + j]]);
        }

        return -1;
    }



}

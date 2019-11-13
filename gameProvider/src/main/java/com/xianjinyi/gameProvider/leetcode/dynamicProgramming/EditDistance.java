package com.xianjinyi.gameProvider.leetcode.dynamicProgramming;


public class EditDistance {

    private char[] a = "mitcmu".toCharArray();
    private char[] b = "mtacnu".toCharArray();
    private int n = 6;
    private int m = 6;
    private int minDist = Integer.MAX_VALUE; // 存储结果


    // 回溯 调用方式 lwstBT(0, 0, 0);
    public void lwstBT(int i, int j, int edist) {
        // 已完成，计算剩余距离
        if (i == n || j == m) {
            if (i < n) {
                edist += (n - i);
            }
            if (j < m) {
                edist += (m - j);
            }
            if (edist < minDist) {
                minDist = edist;
            }
            return;
        }

        // 两个字符匹配
        if (a[i] == b[j]) {
            lwstBT(i + 1, j + 1, edist);
        } else { // 两个字符不匹配
            // 删除a[i]或者b[j]前添加一个字符
            lwstBT(i + 1, j, edist + 1);

            // 删除b[j]或者a[i]前添加一个字符
            lwstBT(i, j + 1, edist + 1);

            // 将a[i]和b[j]替换为相同字符
            lwstBT(i + 1, j + 1, edist + 1);
        }
    }


    /**
     * 最长公共子串 ,有一个公共的算一个
     *
     * 只允许增加删除
     * @param a
     * @param n
     * @param b
     * @param m
     * @return
     */
    public int myCommon(char[] a, int n, char[] b, int m) {
        int[][] status = new int[n][m];
        for (int i = 0; i < n; i++) {
            if (a[i] == b[0]) {
                status[i][0] = 1;
            } else if (i != 0) {
                status[i][0] = status[i - 1][0];
            } else {
                status[i][0] = 0;
            }
        }

        for (int j = 0; j < m; j++) {
            if (b[j] == a[0]) {
                status[0][j] = 1;
            } else if (j != 0) {
                status[0][j] = status[0][j-1];
            } else {
                status[j][0] = 0;
            }
        }

        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                if (a[i] == b[j]){
                    status[i][j] = mymax(status[i-1][j],status[i][j-1],status[i-1][j-1]) +1;
                }else{
                    status[i][j] = mymax(status[i-1][j],status[i][j-1],status[i-1][j-1]);
                }

            }
        }

        return status[n-1][m-1];
    }

    private int mymax(int x, int y, int z) {
        int max = 0;
        if (x > max){
            max = x;
        }
        if (y > max){
            max = y;
        }
        if (z > max){
            max = z;
        }

        return max;
    }




    public int lcs(char[] a, int n, char[] b, int m) {
        int[][] maxlcs = new int[n][m];
        for (int j = 0; j < m; ++j) {//初始化第0行：a[0..0]与b[0..j]的maxlcs
            if (a[0] == b[j]) maxlcs[0][j] = 1;
            else if (j != 0) maxlcs[0][j] = maxlcs[0][j-1];
            else maxlcs[0][j] = 0;
        }
        for (int i = 0; i < n; ++i) {//初始化第0列：a[0..i]与b[0..0]的maxlcs
            if (a[i] == b[0]) maxlcs[i][0] = 1;
            else if (i != 0) maxlcs[i][0] = maxlcs[i-1][0];
            else maxlcs[i][0] = 0;
        }
        for (int i = 1; i < n; ++i) { // 填表
            for (int j = 1; j < m; ++j) {
                if (a[i] == b[j]) maxlcs[i][j] = max(
                        maxlcs[i-1][j], maxlcs[i][j-1], maxlcs[i-1][j-1]+1);
                else maxlcs[i][j] = max(
                        maxlcs[i-1][j], maxlcs[i][j-1], maxlcs[i-1][j-1]);
            }
        }
        return maxlcs[n-1][m-1];
    }

    private int max(int x, int y, int z) {
        int maxv = Integer.MIN_VALUE;
        if (x > maxv) maxv = x;
        if (y > maxv) maxv = y;
        if (z > maxv) maxv = z;
        return maxv;
    }











    public int myLwstDP(char[] a, int n, char[] b, int m) {
        int[][] status = new int[n][m];

        if (a[0] == b[0]){
            status[0][0] = 1;
        }else{
            status[0][0] = 0;
        }
        // 初始化第一行,即对比第一列第一个数据，当前位置的字符串需要多少 莱文斯坦距离

//        for (int i = 1; i < n; i++) {
//            status[i][0] = status[i-1][0] +1;
//        }
//
//        for (int j = 1; j < m; j++) {
//            status[0][j] = status[0][j-1] +1;
//        }
        // 当前字符串的最后字符与另外一个字符相同，则距离是 当前字符长度-1 == 下标，因为对比不一定是从头开始比，跟任意一个比都是可以的，
        // 但是最短也有j，因为长度差减少不了
        for (int j = 0; j < m; ++j) {
            if (a[0] == b[j]) {
                status[0][j] = j;
            } else if (j != 0) {
                status[0][j] = status[0][j - 1] + 1;
            } else {
                status[0][j] = 1;
            }
        }
        for (int i = 0; i < n; ++i) {
            if (a[i] == b[0]) {
                status[i][0] = i;
            } else if (i != 0) {
                status[i][0] = status[i - 1][0] + 1;
            } else {
                status[i][0] = 1;
            }
        }

        // 状态转移
        for(int i=1;i<n ;i++){
            for (int j =1;j<m;j++){
                if(a[i] == b[j]){
                    status[i][j] = min(status[i-1][j],status[i][j-1],status[i-1][j-1]);
                }else{
                    status[i][j] = min(status[i-1][j],status[i][j-1],status[i-1][j-1]) +1;
                }
            }
        }


        return status[n-1][m-1];
    }












    /**
     * 动态规划 莱文斯坦距离
     *
     * @param a
     * @param n
     * @param b
     * @param m
     * @return
     */
    public int lwstDP(char[] a, int n, char[] b, int m) {
        int[][] minDist = new int[n][m];
        // 初始化第0行:a[0..0]与b[0..j]的编辑距离
        for (int j = 0; j < m; ++j) {
            if (a[0] == b[j]) {
                minDist[0][j] = j;
            } else if (j != 0) {
                minDist[0][j] = minDist[0][j - 1] + 1;
            } else {
                minDist[0][j] = 1;
            }
        }
        // 初始化第0列:a[0..i]与b[0..0]的编辑距离
        for (int i = 0; i < n; ++i) {
            if (a[i] == b[0]) {
                minDist[i][0] = i;
            } else if (i != 0) {
                minDist[i][0] = minDist[i - 1][0] + 1;
            } else {
                minDist[i][0] = 1;
            }
        }
        // 按行填表
        for (int i = 1; i < n; ++i) {
            for (int j = 1; j < m; ++j) {
                if (a[i] == b[j]) {
                    minDist[i][j] = min(
                            minDist[i - 1][j] + 1, minDist[i][j - 1] + 1, minDist[i - 1][j - 1]);
                } else {
                    minDist[i][j] = min(
                            minDist[i - 1][j] + 1, minDist[i][j - 1] + 1, minDist[i - 1][j - 1] + 1);
                }
            }
        }
        return minDist[n - 1][m - 1];
    }

    private int min(int x, int y, int z) {
        int minv = Integer.MAX_VALUE;

        if (x < minv) {
            minv = x;
        }

        if (y < minv) {
            minv = y;
        }

        if (z < minv) {
            minv = z;
        }

        return minv;
    }


}

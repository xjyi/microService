package com.xianjinyi.gameProvider.leetcode.recall;

import org.apache.commons.lang.StringUtils;

/**
 * “*”匹配任意多个（大于等于0个）任意字符，“?”匹配零个或者一个任意字符。
 */
public class Pattern {
    private boolean matched = false;
    private char[] pattern; // 正则表达式
    private int plen; // 正则表达式长度

    public Pattern(char[] pattern, int plen) {
        this.pattern = pattern;
        this.plen = plen;
    }


    public boolean myMmatch(char[] text, int tlen) {


        toMatch(0,0,text,tlen);

        return matched;
    }

    private void toMatch(int ti, int pj, char[] text, int tlen) {

        if (matched){
            return;
        }

        if (pj == plen ){
            if (ti == tlen ){
                matched = true;
            }
            return;
        }

        if (pattern[pj] == '*'){
            for (int i=0; i< tlen -ti;i++) {
                toMatch(ti +i, pj +1,  text, tlen);
            }
        }else if (pattern[pj] == '?'){
            // 每进去一个递归，说明当前已经处理完了，一直进到里面，在里面就能判断是否match，所以连着写是没问题的
            // 当然不是说全部递归都这样，只是说这种场景
            toMatch(ti , pj +1,  text, tlen);
            toMatch(ti +1, pj +1,  text, tlen);
        }else if(ti < tlen && text[ti] == pattern[pj]){
            toMatch(ti +1, pj +1,  text, tlen);
        }


    }












    public boolean match(char[] text, int tlen) { // 文本串及长度
        matched = false;
        rmatch(0, 0, text, tlen);
        return matched;
    }

    // 递归一次，匹配的正则表达式移动一位
    private void rmatch(int ti, int pj, char[] text, int tlen) {
        // 如果已经匹配了，就不要继续递归了（对于找到之后就需要停止的，这个判断不可缺）
        // 因为return只是当前方法，回到上一层，可能还会继续执行其他递归

        // 回溯与贪心的很大区别就是当前的选择是不是肯定正确
        // 回溯中也要区分清楚，是不是找到答案就停止（本例），还是要全部找出来（8皇后，0-1背包）
        if (matched) {
            return;
        }

        // 正则表达式到结尾了
        if (pj == plen) {
            // 文本串也到结尾了
            if (ti == tlen) {
                matched = true;
            }
            return;
        }

        // *匹配任意个字符
        if (pattern[pj] == '*') {
            for (int k = 0; k <= tlen-ti; ++k) {
                // 逐个试试
                rmatch(ti+k, pj+1, text, tlen);
            }

        // ?匹配0个或者1个字符
        } else if (pattern[pj] == '?') {
            rmatch(ti, pj+1, text, tlen);
            rmatch(ti+1, pj+1, text, tlen);
        // 被匹配字符串还有剩余字符
        } else if (ti < tlen && pattern[pj] == text[ti]) { // 纯字符匹配才行
            rmatch(ti+1, pj+1, text, tlen);
        }
    }
}

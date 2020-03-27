package com.xianjinyi.gameProvider.leetcodeclasswork;

import org.apache.commons.lang.StringUtils;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Stack;

/**
 * @author: xianjinyi
 * @date 2019/11/25
 */
public class LinkedAl {

    // 应该使用优先级队列，但是此处ListNode没有实现comparable 接口
    public ListNode mergeKLists(ListNode[] lists) {

        ListNode sentinal = new ListNode(-1);
        ListNode next = sentinal;

        PriorityQueue<ListNode> listNodes = new PriorityQueue<>();
        for (int i=0;i<lists.length;i++){
            listNodes.add(lists[i]);
        }

        while (!listNodes.isEmpty()){
            ListNode poll = listNodes.poll();
            next.next = poll;
            poll = poll.next;
            if (poll!=null){
                listNodes.add(poll);
            }
        }
         return sentinal.next;

//
//
//
//        while(true){
//            int min  = Integer.MAX_VALUE;
//            int j = -1;
//            for (int i=0;i<lists.length;i++){
//                if(lists[i] !=null && lists[i].val <min){
//                    min = lists[i].val;
//                    j=i;
//                }
//            }
//            if (j == -1){
//                break;
//            }
//            sentinal.next = lists[j];
//            lists[j] = lists[j].next;
////            if (lists[j].next !=null){
//////
//////            }
//        }



       // return sentinal.next;
    }

       class ListNode {
      int val;
      ListNode next;
      ListNode(int x) { val = x; }
  }

    public static void main(String[] args) {
        new LinkedAl().isValid("([)]");
    }

    char a1 = '(';
    char a2 = ')';
    char b1 = '{';
    char b2 = '}';
    char c1 = '[';
    char c2 = ']';

    /**
     * 需要使用栈，不是队列，所以后插入的要在前面
     * @param s
     * @return
     */
    public boolean isValid(String s) {


        LinkedList<Character> queue =  new LinkedList();

        char[] chars = s.toCharArray();
        for (int i=0;i<chars.length;i++){
            if (chars[i] == a1 || chars[i] == b1  || chars[i] == c1){
                queue.addFirst(chars[i]);
            }else {
                Character poll = queue.poll();
                if (poll ==null){
                    return false;
                }
                boolean check = check(poll, chars[i]);
                if (!check){
                    return false;
                }
            }
        }
        if (queue.size()>0){
            return false;
        }
        return true;
    }

    private boolean check(char poll,char thisChar) {
        if ( (thisChar == a2 && poll==a1) || (thisChar == b2 && poll==b1) || (thisChar == c2 && poll==c1)){
           return true;
        }
        return false;
    }


    /**
     * 给定一个只包含 '(' 和 ')' 的字符串，找出最长的包含有效括号的子串的长度。
     * @param s
     * @return
     * 动态规划方式
     */
    public int longestValidParentheses1(String s) {
            int maxans = 0;
            int dp[] = new int[s.length()];

            for (int i = 1; i < s.length(); i++) {
                // 只有 ) 字符的才有可能是有效结束字符
                if (s.charAt(i) == ')') {
                    // 前一个是(
                    if (s.charAt(i - 1) == '(') {
                        dp[i] = (i >= 2 ? dp[i - 2] : 0) + 2;

                    } else if (i - dp[i - 1] > 0 && s.charAt(i - dp[i - 1] - 1) == '(') {
                        dp[i] = dp[i - 1] + ((i - dp[i - 1]) >= 2 ? dp[i - dp[i - 1] - 2] : 0) + 2;
                    }
                    maxans = Math.max(maxans, dp[i]);
                }
            }
            return maxans;

    }









    /**
     * 动态规划方式
     * @param s
     * @return
     */
    public int mylongestValidParentheses1(String s) {
        int max = 0 ;
        // 默认0
        int [] status = new int[s.length()];
        for (int i=1;i<s.length();i++){
            // 只有结尾是)的才有可能是最长子串的尾部
            if ( s.charAt(i) == ')'){

                if(s.charAt(i-1) == '('){
                    // 可以只判断i - 2 >= 0，省略：s.charAt(i-2) == ')'   因为不是）的肯定是0
                    if (i - 2 >= 0 && s.charAt(i-2) == ')'){
                        status[i] = status[i - 2] + 2 ;
                    }else{
                        status[i] = 2;
                    }


                    // status[i-1] > 0 && s.charAt(i-1) == ')'  可省略
                    // 首先status[i-1] > 0 就包含了 s.charAt(i-1) == ')'
                    // 如果status[i-1] == 0，s.charAt(i - status[i-1] -1) 的条件跟上一个if相同，不可能成立，所以可以省略
                    // 以上只是优化
                }else if (status[i-1] > 0 && s.charAt(i-1) == ')' ){

                    if (i - status[i-1] -1 >=0 && s.charAt(i - status[i-1] -1) == '('){
                        status[i] = i - status[i-1] -2 >=0?status[i-1] + status[i - status[i-1] -2] +2:status[i-1]  +2;
                    }
                    // && i - status[i-1] -1 >0 && status[i - status[i-1] -1] =='('

                }
            }

            max = Math.max(max, status[i]);
        }

        // 求最大值的方法，可以直接在循环中进行判断替换
//        for (int i=0;i<s.length();i++){
//            if (status[i] >max){
//                max = status[i];
//            }
//        }
        return max;
    }


    /**
     * 栈方式
     * @param s
     * @return
     */
    public int mylongestValidParentheses2(String s) {
        // 栈方式，上面第一个是动态规划
        int max=0;
        Stack<Integer> stack = new Stack<>();

        // 保证至少有一个数据
        stack.push(-1);

        for (int i =0;i<s.length();i++){
            if (s.charAt(i) ==  '('){
                stack.push(i);
            }else{
                //  ) 时
                stack.pop();
                if (stack.size() == 0){
                    // 当前 ） 没有对应，断了, 记录断了的位置
                    stack.push(i);
                }else{
                    // 没断，算出距离
                    max = Math.max(max,i- stack.peek());

                }
            }
        }

        return max;
    }







    public int longestValidParentheses2(String s) {
        int maxans = 0;
        Stack<Integer> stack = new Stack<>();
        stack.push(-1);

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push(i);
            } else {
                stack.pop();
                if (stack.empty()) {
                    stack.push(i);
                } else {
                    maxans = Math.max(maxans, i - stack.peek());
                }
            }
        }
        return maxans;
    }

    public int evalRPN(String[] tokens) {
        Stack<Integer> sta = new Stack();
        for(int i =0;i<tokens.length;i++){
            if("+".equals(tokens[i]) ){
                int a = sta.pop();
                int b = sta.pop();
                sta.push(a+b);
            }else if("-".equals(tokens[i])){
                int a = sta.pop();
                int b = sta.pop();
                sta.push(a-b);
            }else if("*".equals(tokens[i])){
                int a = sta.pop();
                int b = sta.pop();
                sta.push(a*b);
            }else if("/".equals(tokens[i])) {
                int a = sta.pop();
                int b = sta.pop();
                sta.push(a/b);
            }else{
                sta.push(Integer.valueOf(tokens[i]));
            }
        }
        return sta.pop();
    }
}

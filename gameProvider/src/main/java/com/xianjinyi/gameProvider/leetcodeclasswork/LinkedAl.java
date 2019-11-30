package com.xianjinyi.gameProvider.leetcodeclasswork;

import java.util.*;

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
        int max=0;

        return max;
    }


    /**
     * 不需额外空间
     * @param s
     * @return
     */
    public int mylongestValidParentheses3(String s) {
        // 从左往右 左括号多不要紧，直到数量等于右括号，则当前长度=有括号*2；
        int max=0;
        int left = 0,right =0;
        for (int i=0;i<s.length();i++){
            if (s.charAt(i) == '('){
                left++;
            }else {
                right++;
            }

            if (left == right){
                max = Math.max(max,right*2);
            }else if(right > left){
                left = right =0;
            }
        }

        left = right =0;

        for (int i=s.length()-1;i>=0;i--){
            if (s.charAt(i) == ')'){
                right++;
            }else {
                left++;
            }

            if (left == right){
                max = Math.max(max,left*2);
            }else if(left > right){
                left = right =0;
            }
        }

        return max;
    }

    /**
     * 滑动窗口最大值
     * 要求时间复杂度 O(n)
     * @param nums
     * @param k
     * @return
     */
    public int[] myMaxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        // 以k为大小，间隔成n/k(+1) 个区间，小区间内最右是最大数据
        int[] left = new int[n];
        int[] right = new int[n];

        left[0] = nums[0];
        right[n-1] = nums[n-1];

        for (int i = 1; i < n; i++) {
            if (i % k == 0) {
                left[i] = nums[i];
            }else{
                left[i]= Math.max(nums[i],left[i-1]);
            }

            // 最右一个是n-1，与i建立关系就是 n-1 -i
            int j= n-i-1;
            if ((j+1)%k == 0){
                right[j] = nums[j];
            }else{
                right[j] = Math.max(nums[j],right[j+1]);
            }

        }

        int[] ints = new int[n - k + 1];
        for (int i = 0; i < n - k + 1; i++) {
            ints[i] = Math.max(left[i + k - 1], right[i]);
        }
        return ints;


        //理解错误，是要每个窗口中的最大值
//        int[] ints = new int[k];
//        int[] sums = new int[nums.length - k + 1];
//        int sum = 0;
//
//        for (int i = 0; i < k; i++) {
//            sum += nums[k];
//        }
//        sums[0] = sum;
//        int max = sums[0];
//        int maxIndex = 0;
//
//        for (int i = k; i < nums.length; i++) {
//            sums[i-k +1] = sums[i-k] + nums[i] - nums[i-k];
//            if (max < sums[i-k +1]){
//                max = sums[i-k +1];
//                maxIndex = i-k +1;
//            }
//
//        }
//
//        for (int j=0;j<k;j++,maxIndex++){
//            ints[j] = nums[maxIndex];
//        }
//        int[] ints = new int[k];
        // 下一个组合，总大小sum[i] 与 sum[i-1]  相同，则最大值相同
        // sum[i-1] 更大 ，说明新的值不及原来的区间最左元素大，此时旧区间最大值假如不是最左元素，则最大值不变
        //  是最左元素，取剩余的最大元素
        // sum[i] 更大，新的值比原来区间最左大，如果此元素比原最大元素大，则为新元素，否则不变




//        return ints;
    }


    /**
     * 双向队列方式
     */
    class Solution {
        // 存储的是下标
        ArrayDeque<Integer> deq = new ArrayDeque<Integer>();
        int [] nums;

        public void clean_deque(int i, int k) {
            // 首元素不是当前滑动区间的
            if (!deq.isEmpty() && deq.getFirst() == i - k){
                deq.removeFirst();
            }


            // 从后往前，比当前小的都删掉，这些都不可能是当前区间最大的，且对后面的没有作用
            while (!deq.isEmpty() && nums[i] > nums[deq.getLast()]) {
                deq.removeLast();
            }
        }

        public int[] maxSlidingWindow(int[] nums, int k) {
            int n = nums.length;
            if (n * k == 0) {
                return new int[0];
            }
            if (k == 1) {
                return nums;
            }

            // init deque and output
            this.nums = nums;
            //
//            int max_idx = 0;
            for (int i = 0; i < k; i++) {
                clean_deque(i, k);
                deq.addLast(i);
                // compute max in nums[:k]
//                if (nums[i] > nums[max_idx]){
//                    max_idx = i;
//                }
            }

            int [] output = new int[n - k + 1];
            // 当前队列最前面的肯定是最大的
            output[0] = nums[deq.getFirst()];

            // build output
            for (int i  = k; i < n; i++) {
                clean_deque(i, k);
                deq.addLast(i);
                output[i - k + 1] = nums[deq.getFirst()];
            }
            return output;
        }
    }





    public int[] maxSlidingWindow2(int[] nums, int k) {
        int n = nums.length;
        // 数据为空等
        if (n * k == 0) {
            return new int[0];
        }
        if (k == 1) return nums;

        // 起始到当前下标，k个数据内的最大值
        int [] left = new int[n];
        left[0] = nums[0];

        // 同理，方向从右往左
        int [] right = new int[n];
        right[n - 1] = nums[n - 1];



        for (int i = 1; i < n; i++) {
            // 则：小区间的最右，是该区间的最大
            // 按照k个大小的区间划分，区间的起点下标，先直接等于原数组元素
            if (i % k == 0) {
                left[i] = nums[i];  // block_start
            }else {
                // 其他的等于当前与上一个的最大值
                left[i] = Math.max(left[i - 1], nums[i]);
            }

            // 同理 小区间的最左，是该区间的最大
            int j = n - i - 1;
            if ((j + 1) % k == 0) {
                right[j] = nums[j];  // block_end
            }else {
                right[j] = Math.max(right[j + 1], nums[j]);
            }
        }

        int [] output = new int[n - k + 1];
        for (int i = 0; i < n - k + 1; i++){
            output[i] = Math.max(left[i + k - 1], right[i]);
        }


        return output;
    }

    /**
     * 爬楼梯
     *
     * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
     *
     * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
     * @param n
     * @return
     */
    public int climbStairs(int n) {



        return -1;// toClient(n);

    }

    public static HashMap<Integer,Integer> map =  new HashMap<>();
    private int toClient(int n) {
        // n递增，用数组即可
        if (n == 1){
            return 1;
        }
        if (n == 2){
            return 2;
        }
        if (map.containsKey(n)){
            return map.get(n);
        }else{
            int i = toClient(n - 1) + toClient(n - 2);
            map.put(n,i);
            return i;
        }


    }
}

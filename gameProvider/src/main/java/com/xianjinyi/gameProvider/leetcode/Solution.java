package com.xianjinyi.gameProvider.leetcode;

/**
 * @Author: xianjinyi
 * @date 2019/10/15
 *
 * 判断单链表是否为回文
 *
 *
 */
public class Solution {

        public boolean isPalindrome(ListNode head) {
            if (head == null || head.next == null) {
                return true;
            }

            ListNode prev = null;
            ListNode slow = head;
            ListNode fast = head;

            // 使用快慢指针定位链表中间节点
            while (fast != null && fast.next != null) {
                fast = fast.next.next;
                ListNode next = slow.next;
                slow.next = prev;
                prev = slow;
                slow = next;
            }

            // 奇数数据处理
            if (fast != null) {
                slow = slow.next;
            }

            // 判断是否回文
            while (slow != null) {
                if (slow.val != prev.val) {
                    return false;
                }
                slow = slow.next;
                prev = prev.next;
            }

            // todo 将链表前半部分还原
            return true;
        }
}

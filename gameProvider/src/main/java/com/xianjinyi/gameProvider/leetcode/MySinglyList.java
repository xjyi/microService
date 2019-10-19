package com.xianjinyi.gameProvider.leetcode;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.util.JSONPObject;
import lombok.Builder;

/**
 * @Author: xianjinyi
 * @date 2019/10/15
 *
 * 单链表
 */
public class MySinglyList {

    public MyListNode head;
    public MyListNode tail;


    //@Builder
    static class MyListNode{
        public int val;
        public MyListNode next;
    }


    public static void main(String[] args) {

        // 构建一个正序链表
        MySinglyList mySinglyList = new MySinglyList();
        MyListNode build = new MyListNode();
        build.val =0;

        MyListNode node = mySinglyList.head = build;
        for (int i = 1; i <= 10; i++) {
            MyListNode node1 = new MyListNode();
            node1.val = i;
            MyListNode nextNode = addNode(node,node1);
            node = nextNode;
        }
        System.out.println(JSON.toJSONString(mySinglyList.toString()));



        MySinglyList reverseList = reverse(mySinglyList) ;
        System.out.println(JSON.toJSONString(reverseList.toString()));

    }

    /**
     * 反转一个链表
     * @param mySinglyList
     * @return
     */
    private static MySinglyList reverse(MySinglyList mySinglyList) {


        // 初始值必须为null
        MyListNode prev = null;

        MyListNode node = mySinglyList.head;

        while (node != null) {
            MyListNode nextnode = node.next;

            node.next = prev;

            prev = node;

            node = nextnode;
        }

        // 最后一个节点指向head
        mySinglyList.head = prev;

        return mySinglyList;

    }


    private static MyListNode addNode(MyListNode prev, MyListNode build) {
        prev.next = build;
        return build;
    }













    @Override
    public String toString() {
        StringBuilder aa = new StringBuilder();
        if (head!=null){
            aa.append(head.val+",");
        }
        MyListNode node = head;
        while (node.next !=null){
            MyListNode next = node.next;
            aa.append(next.val+"," );
            node = next;
        }

        return aa.toString();
    }



}

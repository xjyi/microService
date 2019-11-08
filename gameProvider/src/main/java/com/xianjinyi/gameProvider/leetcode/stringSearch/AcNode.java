package com.xianjinyi.gameProvider.leetcode.stringSearch;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Author: xianjinyi
 * @date 2019/11/06
 */
public class AcNode {

    public char data;
    public AcNode[] children = new AcNode[26]; // 字符集只包含a~z这26个字符
    public boolean isEndingChar = false; // 结尾字符为true
    public int length = -1; // 当isEndingChar=true时，记录模式串长度

    public AcNode fail; // 失败指针
    public AcNode(char data) {
        this.data = data;
    }

    /**
     * 存储无意义字符
     */
    private AcNode root = new AcNode('/');

    public void buildFailurePointer() {
        // 队列，暂存需要处理的节点
        Queue<AcNode> queue = new LinkedList<>();
        // root的fail是null，这是while循环的结束条件
        root.fail = null;
        queue.add(root);
        while (!queue.isEmpty()) {
            AcNode p = queue.remove();
            // 每个节点的26个子节点
            for (int i = 0; i < 26; ++i) {
                AcNode pc = p.children[i];
                if (pc == null) {
                    continue;
                }
                // 父节点是root，则当前子节点的失败指针就是root
                if (p == root) {
                    pc.fail = root;
                } else {
                    // q :父节点的失败指针 pc：当前正在处理的子节点
                    AcNode q = p.fail;
                    while (q != null) {
                        AcNode qc = q.children[pc.data - 'a'];
                        // qc不为null，此处qc的值肯定 == pc的值
                        if (qc != null) {
                            pc.fail = qc;
                            break;
                        }
                        // 继续找下一个最长可匹配串
                        q = q.fail;
                    }
                    if (q == null) {
                        pc.fail = root;
                    }
                }
                // 子节点放进队列（类似广度优先进行处理）
                queue.add(pc);
            }
        }
    }

    public void match(char[] text) { // text是主串
        int n = text.length;
        AcNode p = root;
        for (int i = 0; i < n; ++i) {
            int idx = text[i] - 'a';
            // 失败指针发挥作用的地方
            while (p.children[idx] == null && p != root) {
                p = p.fail;
            }

            // 给下一循环用
            p = p.children[idx];

            // p的失败指针如果是root，说明p没有匹配的后缀子串了
            // 如果没有匹配的，从root开始重新匹配
            if (p == null) {
                p = root;
            }
            AcNode tmp = p;
            // 打印出可以匹配的模式串
            // 不是root，说明当前字符（串）在trie树匹配成功，如果是结尾字符就打印，
            // 不是结尾字符就看最长可匹配后缀是否符合，直到失败指针指向root
            while (tmp != root) {
                if (tmp.isEndingChar == true) {
                    int pos = i-tmp.length+1;
                    System.out.println("匹配起始下标" + pos + "; 长度" + tmp.length);
                }
                tmp = tmp.fail;
            }
        }
    }
}

package com.xianjinyi.gameProvider.leetcode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Author: xianjinyi
 * @date 2019/10/31
 */
public class Graph {

    private int v; // 顶点的个数
    public LinkedList<Integer> adj[]; // 邻接表

    public Graph(int v) {
        this.v = v;
        adj = new LinkedList[v];
        for (int i=0; i<v; ++i) {
            adj[i] = new LinkedList<>();
        }
    }

    public void addEdge(int s, int t) { // 无向图一条边存两次
        adj[s].add(t);
        adj[t].add(s);
    }


    public static void main(String[] args) {
        Graph graph = new Graph(10);
        graph.addEdge(0,1);
        graph.addEdge(0,3);
        graph.addEdge(1,2);
        graph.addEdge(1,4);
        graph.addEdge(3,4);
        graph.addEdge(4,5);
        graph.addEdge(4,6);
        graph.addEdge(6,7);
        graph.addEdge(5,7);
        graph.addEdge(2,5);

//         graph.bfs(0,6);
        graph.myDfs(0,6);
//        boolean[] visiteds = new boolean[10];
//        System.out.println(visiteds[0]);

    }

    boolean found = false; // 全局变量或者类成员变量


    public void myDfs(int s, int t){
        boolean[] visiteds = new boolean[v];

        int[] prev = new int[v];
        for (int i=0; i<v;i++){
            prev[i]  = -1;
        }
        visiteds[s] = true;
        toDfs(s,t,visiteds,prev);
        print(prev,s,t);
    }

    private void toDfs(int s, int t, boolean[] visiteds, int[] prev) {
        // 这个不可缺，不然找到之后还会继续递归其他的
        if(found){
            return;
        }
        if(s == t){
            found = true;
            return;
        }
        visiteds[s] = true;
        LinkedList<Integer> list = adj[s];
        for(int i =0;i<list.size();i++){
            Integer p = list.get(i);

            if (!visiteds[p]){

                prev[p] = s;
//                if(p == t){
//                    found = true;
//                    return;
//                }
                toDfs(p,t,visiteds,prev);
            }

        }


    }


    public void dfs(int s, int t) {
        found = false;
        boolean[] visited = new boolean[v];
        int[] prev = new int[v];
        for (int i = 0; i < v; ++i) {
            prev[i] = -1;
        }
        recurDfs(s, t, visited, prev);
        print(prev, s, t);
    }

    private void recurDfs(int w, int t, boolean[] visited, int[] prev) {
        if (found == true) {
            return;
        }
        visited[w] = true;
        if (w == t) {
            found = true;
            return;
        }
        for (int i = 0; i < adj[w].size(); ++i) {
            int q = adj[w].get(i);
            if (!visited[q]) {
                prev[q] = w;
                recurDfs(q, t, visited, prev);
            }
        }
    }


    /**
     * 广度优先遍历
     * 数字不重复(s == t 本例中值也是下标)
     * @param s 开始节点下标
     * @param t 目标节点下标
     */
    public void myBfs(int s, int t) {
        // 记录已经访问的节点
        boolean[] visiteds = new boolean[v];

        // 记录来源 每个下标存的值是前驱顶点的下标 默认值有意义，转化为-1
        int[] prev = new int[v];
        for (int i = 0; i < v; i++) {
            prev[i] = -1;
        }

        // 暂存需要访问的节点，使用队列，先进先出
        LinkedList<Integer> tempNodes = new LinkedList<Integer>();

        visiteds[s] = true;
        tempNodes.add(s);

        // 有 待遍历 的顶点
        while(tempNodes.size() >0){
            Integer poll = tempNodes.poll();
            // 遍历同一广度的

            // 不能poll，这个是邻接表，结构不能变
//            while (adj[poll].size()>0) {
//                Integer p = adj[poll].poll();
            for (int i =0; i<adj[poll].size();i++){
                Integer p = adj[poll].get(i);
                if(!visiteds[p]){
                    prev[p] = poll;
                    if(t == p){
                        printRoute(prev,s,t);
                        //print(prev,s,t);
                        return;
                    }
                    tempNodes.add(p);
                    visiteds[p] = true;

                }

            }
        }

    }

    // 需要反向打印
    private void printRoute(int[] prev, int s, int t) {
        LinkedList<Object> list = new LinkedList<>();
        int next = t;
        while(s != next){
            list.addFirst(next);
            next = prev[next];
        }
        list.addFirst(s);
        while(list.size()>0){
            System.out.println(list.poll());
        }

    }





    public void bfs(int s, int t) {
        if (s == t) {
            return;
        }

        // visited 是用来记录已经被访问的顶点，
        // （猜测）图中没有重复的数据，实际开发中是一个个对象
        // 图中任意一个顶点，在visited数组中有对应的位置，本例中是直接将顶点（基础类型）作为角标，如果顶点是对象，需转化为数组，或可考虑使用散列表
        // 同理，邻接表中数组也是一样
        // 总结：邻接表的数组，visited访问记录数组，都能直接根据顶点的特征（下标），直接定位对应的邻接链表，是否访问标记等
        boolean[] visited = new boolean[v];
        visited[s]=true;

        // queue是一个队列，用来存储已经被访问、但相连的顶点还没有被访问的顶点。
        LinkedList<Integer> queue = new LinkedList<>();

        queue.add(s);

        // prev用来记录搜索路径
        int[] prev = new int[v];
        for (int i = 0; i < v; ++i) {
            prev[i] = -1;
        }


        while (queue.size() != 0) {

            // 链表第一个，存储的是邻接表数组的下标
            //  adj[w] : 顶点对应的链表
            int w = queue.poll();
            for (int i = 0; i < adj[w].size(); ++i) {
                int q = adj[w].get(i);
                if (!visited[q]) {
                    prev[q] = w;
                    if (q == t) {
                        print(prev, s, t);
                        return;
                    }
                    visited[q] = true;
                    queue.add(q);
                }
            }
        }
    }

    private void print(int[] prev, int s, int t) { // 递归打印s->t的路径
        if (prev[t] != -1 && t != s) {
            print(prev, s, prev[t]);
        }
        System.out.println(t + " ");
    }
}

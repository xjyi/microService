package com.xianjinyi.gameProvider.leetcode.graph;

import java.util.HashMap;
import java.util.LinkedList;

/**
 * @author: xianjinyi
 * @date 2019/11/14
 */
public class Topo {


    /**
     * Topo 图对象
     */
    private int v; // 顶点的个数

    // 邻接表，每个数组元素就是一个顶点，顶点值为下标
    // 每个顶点对应一个链表（数组值），存储指向的全部顶点
    private LinkedList<Integer> adj[];

    public Topo(int v) {
        this.v = v;
        adj = new LinkedList[v];
        for (int i=0; i<v; ++i) {
            adj[i] = new LinkedList<>();
        }
    }

    public void addEdge(int s, int t) { // s先于t，边s->t
        adj[s].add(t);
    }


    public void topoSortByDFS() {
        // 先构建逆邻接表，边s->t表示，s依赖于t，t先于s
        LinkedList<Integer> inverseAdj[] = new LinkedList[v];
        for (int i = 0; i < v; ++i) { // 申请空间
            inverseAdj[i] = new LinkedList<>();
        }
        // 通过邻接表生成逆邻接表
        for (int i = 0; i < v; ++i) {
            for (int j = 0; j < adj[i].size(); ++j) {
                int w = adj[i].get(j); // i->w
                inverseAdj[w].add(i); // w->i
            }
        }
        boolean[] visited = new boolean[v];
        for (int i = 0; i < v; ++i) { // 深度优先遍历图
            if (visited[i] == false) {
                visited[i] = true;
                dfs(i, inverseAdj, visited);
            }
        }
    }

    private void dfs(
            int vertex, LinkedList<Integer> inverseAdj[], boolean[] visited) {

        for (int i = 0; i < inverseAdj[vertex].size(); ++i) {
            int w = inverseAdj[vertex].get(i);
            if (visited[w] == true) continue;
            visited[w] = true;
            dfs(w, inverseAdj, visited);
        } // 先把vertex这个顶点可达的所有顶点都打印出来之后，再打印它自己
        System.out.print("->" + vertex);
    }




    public void topoSortByKahn() {
        int[] inDegree = new int[v];
        // 统计每个顶点的入度
        // 当前普通的邻接表，一般是都是指向（出度）的节点，统计需要转化为入度
        for (int i = 0; i < v; ++i) {
            for (int j = 0; j < adj[i].size(); ++j) {
                int w = adj[i].get(j); // i->w
                inDegree[w]++;
            }
        }
        // 先处理入度为0的
        LinkedList<Integer> queue = new LinkedList<>();
        for (int i = 0; i < v; ++i) {
            if (inDegree[i] == 0) {
                queue.add(i);
            }
        }
        while (!queue.isEmpty()) {
            int i = queue.remove();
            System.out.print("->" + i);
            // 当前节点处理完后，根据邻接表的出度记录，在对应的入度统计中-1
            for (int j = 0; j < adj[i].size(); ++j) {
                int k = adj[i].get(j);
                inDegree[k]--;
                // 减完之后为0的，就可以放进队列等待被处理
                if (inDegree[k] == 0) {
                    queue.add(k);
                }
            }
        }
    }

    public static void main(String[] args) {
        HashMap<Object, Object> objectObjectHashMap = new HashMap<>(17);
        objectObjectHashMap.put("1","2");
        System.out.println();
    }

}

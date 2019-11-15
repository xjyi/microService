package com.xianjinyi.gameProvider.leetcode.graph;

import java.util.LinkedList;
import java.util.PriorityQueue;

/**
 * @author: xianjinyi
 * @date 2019/11/15
 */
public class ShortestPath {


    // 有向有权图的邻接表表示
    private LinkedList<Edge> adj[]; // 邻接表
    private int v; // 顶点个数

    /**
     * ShortestPath 图对象
     * @param v
     */
    public ShortestPath(int v) {
        this.v = v;
        this.adj = new LinkedList[v];
        for (int i = 0; i < v; ++i) {
            this.adj[i] = new LinkedList<>();
        }
    }

    // 添加一条边
    // 邻接表里面存的是边对象 之前的无向图只需存储顶点，所以只记录一个int值,
    // 地图中需要存储方向、权重，所以使用对象
    public void addEdge(int s, int t, int w) {
        this.adj[s].add(new Edge(s, t, w));
    }

    private class Edge {
        public int sid; // 边的起始顶点编号
        public int tid; // 边的终止顶点编号
        public int w; // 权重
        public Edge(int sid, int tid, int w) {
            this.sid = sid;
            this.tid = tid;
            this.w = w;
        }
    }



    // 下面这个类是为了dijkstra实现用的
    private class Vertex implements Comparable<Vertex> {
        public int id; // 顶点编号ID
        public int dist; // 从起始顶点到这个顶点的距离
        public Vertex(int id, int dist) {
            this.id = id;
            this.dist = dist;
        }

        @Override
        public int compareTo(Vertex o) { // 按照dist从小到大排序
            if (o.dist > this.dist) {
                return -1;
            } else {
                return 1;
            }
        }
    }


    // 从顶点s到顶点t的最短路径
    public void dijkstra(int s, int t) {
        // 用来还原最短路径
        int[] predecessor = new int[this.v];

        // 记录起始顶点到这个顶点的距离，初始化dist为无穷大
        // 顶点依旧是
        Vertex[] vertexes = new Vertex[this.v];
        for (int i = 0; i < v; ++i) {
            vertexes[i] = new Vertex(i, Integer.MAX_VALUE);
        }

        // 小顶堆（根据Vertex类的compareTo 决定是大顶堆还是小顶堆）
        PriorityQueue<Vertex> queue = new PriorityQueue<>();
        // 标记是否进入过队列
        boolean[] inQueue = new boolean[this.v];


        // 先把起始顶点放到队列中
        queue.add(vertexes[s]);
        vertexes[s].dist = 0;
        inQueue[s] = true;


        while (!queue.isEmpty()) {
            // 取dist最小的顶点
            Vertex minVertex= queue.poll();

            // 最短路径产生了
            if (minVertex.id == t) {
                break;
            }

            // 优先遍历完当前所有边，再取下一个顶点（相当于广度优先遍历）
            // 广度遍历中，顶点对每个边，是先执行最近的，这个由优先级队列完成
            for (int i = 0; i < adj[minVertex.id].size(); ++i) {
                // 取出一条minVetex相连的边
                Edge e = adj[minVertex.id].get(i);

                // minVertex-->nextVertex
                Vertex nextVertex = vertexes[e.tid];

                //找到一条到nextVertex更短的路径
                // 起始顶点到当前顶点的距离+边的权重 小于 起始顶点到下一顶点的距离
                // 每次都走最短的路径，一个顶点在不同路径中，可能被遍历多次
                // Vertex 对象记录了起始顶点到当前顶点的最短路径，下次遇到的话，可能遇到更加短的，此时需要更新最短路径
                // 每个顶点的每条边都需要遍历
                if (minVertex.dist + e.w < nextVertex.dist) {
                    // 更新dist
                    nextVertex.dist = minVertex.dist + e.w;

                    //更新前驱顶点
                    predecessor[nextVertex.id] = minVertex.id;

                    // 如果没有在队列中，就把它放到队列中
                    if (inQueue[nextVertex.id] == false) {
                        queue.add(nextVertex);
                        inQueue[nextVertex.id] = true;
                    }
                }
            }
        }
        // 输出最短路径
        System.out.print(s);
        print(s, t, predecessor);
    }

    private void print(int s, int t, int[] predecessor) {
        if (s == t) return;
        print(s, predecessor[t], predecessor);
        System.out.print("->" + t);
    }
}

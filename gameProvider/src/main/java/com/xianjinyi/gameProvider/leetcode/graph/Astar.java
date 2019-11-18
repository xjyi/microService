package com.xianjinyi.gameProvider.leetcode.graph;

import java.util.LinkedList;
import java.util.PriorityQueue;

/**
 * @author: xianjinyi
 * @date 2019/11/18
 */
public class Astar {

    // 有向有权图的邻接表表示
    private LinkedList<Astar.Edge> adj[]; // 邻接表
    private int v; // 顶点个数

    /**
     * Astar 图对象
     * @param v
     */
    public Astar(int v) {
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
        this.adj[s].add(new Astar.Edge(s, t, w));
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



    private class Vertex implements Comparable<Vertex> {
        public int id; // 顶点编号ID
        public int dist; // 从起始顶点，到这个顶点的距离，也就是g(i)
        public int f; // 新增：f(i)=g(i)+h(i)
        public int x, y; // 新增：顶点在地图中的坐标（x, y）
        public Vertex(int id, int x, int y) {
            this.id = id;
            this.x = x;
            this.y = y;
            this.f = Integer.MAX_VALUE;
            this.dist = Integer.MAX_VALUE;
        }

        @Override
        public int compareTo(Vertex o) { // 按照f从小到大排序
            if (o.f > this.f) {
                return -1;
            } else {
                return 1;
            }
        }
    }
    // Graph类的成员变量，在构造函数中初始化
    Vertex[] vertexes = new Vertex[this.v];
    // 新增一个方法，添加顶点的坐标
    public void addVetex(int id, int x, int y) {
        vertexes[id] = new Vertex(id, x, y);
    }


    public void astar(int s, int t) { // 从顶点s到顶点t的路径
        int[] predecessor = new int[this.v]; // 用来还原路径
        // 按照vertex的f值构建的小顶堆，而不是按照dist
        PriorityQueue<Vertex> queue = new PriorityQueue(this.v);
        boolean[] inqueue = new boolean[this.v]; // 标记是否进入过队列
        vertexes[s].dist = 0;
        vertexes[s].f = 0;
        queue.add(vertexes[s]);
        inqueue[s] = true;
        while (!queue.isEmpty()) {
            Vertex minVertex = queue.poll(); // 取堆顶元素并删除

            for (int i = 0; i < adj[minVertex.id].size(); ++i) {
                // 取出一条minVetex相连的边
                Edge e = adj[minVertex.id].get(i);

                // minVertex-->nextVertex
                Vertex nextVertex = vertexes[e.tid];

                // 更新next的dist,f
                if (minVertex.dist + e.w < nextVertex.dist) {
                    // xianjinyi
                    if (inqueue[nextVertex.id] == true) {
                        queue.remove(nextVertex);
                    }


                    nextVertex.dist = minVertex.dist + e.w;
                    nextVertex.f
                            = nextVertex.dist+hManhattan(nextVertex, vertexes[t]);
                    predecessor[nextVertex.id] = minVertex.id;

                    // xianjinyi
                    queue.add(nextVertex);
                    inqueue[nextVertex.id] = true;
//                    if (inqueue[nextVertex.id] == true) {
//                        // 堆只有加入数据、删除数据才有意义，此处是f值变了，因为要更新位置
//                        // 只能先删后加
//                        //queue.update(nextVertex);
//                        // nextVertex 在重新对f赋值前，先删除（Dijkstra算法中也有类似问题）
//                        queue.remove(nextVertex);
//                        queue.add(nextVertex);
//                    } else {
//                        queue.add(nextVertex);
//                        inqueue[nextVertex.id] = true;
//                    }
                }
                // 只要到达t就可以结束while了
                if (nextVertex.id == t) {
                    break;
                }
            }
        }
        // 输出路径
        System.out.print(s);
        print(s, t, predecessor);
    }

    int hManhattan(Vertex v1, Vertex v2) { // Vertex表示顶点，后面有定义
        return Math.abs(v1.x - v2.x) + Math.abs(v1.y - v2.y);
    }
    private void print(int s, int t, int[] predecessor) {
        if (s == t) return;
        print(s, predecessor[t], predecessor);
        System.out.print("->" + t);
    }
}

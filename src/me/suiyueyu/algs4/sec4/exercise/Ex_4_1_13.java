package me.suiyueyu.algs4.sec4.exercise;

import me.suiyueyu.algs4.sec1.Queue_alg_1_3;
import me.suiyueyu.algs4.sec1.Stack_alg_1_2;
import me.suiyueyu.algs4.sec4.Graph_model;

/**
 * Created by yzcc on 2016/5/18.
 * 为breadthFirstPaths 的api添加一个并实现一个方法distTo(),返回从起点到给定
 * 顶点的最短路径的长度，他所需要的时间为常数.
 */
public class Ex_4_1_13 {
    private boolean[] marked;
    private int[] edgeTo;
    private final int s;

    public Ex_4_1_13(Graph_model G, int s) {
        marked = new boolean[G.V()];
        edgeTo = new int[G.E()];
        this.s = s;
        bfs(G, s);
    }

    public void bfs(Graph_model G, int s) {
        Queue_alg_1_3<Integer> queue = new Queue_alg_1_3<Integer>();
        marked[s] = true;
        queue.enqueue(s);
        while (!queue.isEmpty()) {
            int v = queue.dequeue();
            for (int w : G.adj(v)) {
                if (!marked[w]) {
                    edgeTo[w] = v;
                    marked[w] = true;
                    queue.enqueue(w);
                }
            }
        }
    }

    public boolean hasPathTo(int v) {
        return marked[v];
    }

    public Iterable<Integer> pathTo(int v) {
        if (!hasPathTo(v)) {
            return null;
        }
        Stack_alg_1_2<Integer> path = new Stack_alg_1_2<Integer>();
        for (int x = v; x != s; x = edgeTo[x]) {
            path.push(x);
        }
        path.push(s);
        return path;
    }
}

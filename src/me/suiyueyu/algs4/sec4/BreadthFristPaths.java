package me.suiyueyu.algs4.sec4;

import me.suiyueyu.algs4.sec1.Queue_alg_1_3;
import me.suiyueyu.algs4.sec1.Stack_alg_1_2;

/**
 * Created by yzcc on 2016/4/28.
 */
public class BreadthFristPaths {
    private boolean[] marked;
    private int[] edgeTo;
    private final int s;

    public BreadthFristPaths(Graph_model G, int s) {
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

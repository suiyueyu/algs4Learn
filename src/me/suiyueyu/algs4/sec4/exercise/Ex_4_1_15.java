package me.suiyueyu.algs4.sec4.exercise;

import me.suiyueyu.algs4.sec1.Bag_alg_1_4;
import stdlib.In;

/**
 * Created by yzcc on 2016/7/4.
 * 修改Graph 的输入构造函数，允许从标准输入读入图的邻接表(方法类似于SymbolGraph)，如图xxxxx所示。
 * 在顶点和边的总数过后，每一行由一个顶点和它的所有相邻顶点组成
 */
public class Ex_4_1_15 {
    private final int V;
    private int E;
    private Bag_alg_1_4<Integer>[] adj;

    public Ex_4_1_15(int V) {
        this.V = V;
        this.E = 0;
        adj = (Bag_alg_1_4<Integer>[]) new Bag_alg_1_4[V];
        for (int v = 0; v < V; v++) {
            adj[v] = new Bag_alg_1_4<Integer>();
        }
    }

    /**
     * 修改后的输入流构造函数
     *
     * @param in 输入格式：
     *           13 <- V
     *           13 <- E
     *           0 1 2 5 6
     *           3 4 5
     *           4 5 6
     *           7 8
     *           9 10 11 12
     *           11 12
     */
    public Ex_4_1_15(In in) {
        this.V = in.readInt();
        this.E = in.readInt();
        adj = (Bag_alg_1_4<Integer>[]) new Bag_alg_1_4[V];
        // TODO: 没写完
        int from;

//        int E = in.readInt();
//
//        for (int i = 0; i < E; i++) {
//            int v = in.readInt();
//            int w = in.readInt();
//            addEdge(v, w);
//        }
    }

    public int V() {
        return V;
    }

    public int E() {
        return E;
    }

    public void addEdge(int v, int w) {
        adj[v].add(w);
        adj[w].add(v);
        E++;
    }

    public Iterable<Integer> adj(int v) {
        return adj[v];
    }


}

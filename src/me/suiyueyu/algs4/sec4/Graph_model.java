package me.suiyueyu.algs4.sec4;

import me.suiyueyu.algs4.sec1.Bag_alg_1_4;
import stdlib.In;

/**
 * Created by yzcc on 2016/4/20.
 */
public class Graph_model {
    private final int V;
    private int E;
    private Bag_alg_1_4<Integer>[] adj;

    public Graph_model(int V) {
        this.V = V;
        this.E = 0;
        adj = (Bag_alg_1_4<Integer>[]) new Bag_alg_1_4[V];
        for (int v = 0; v < V; v++) {
            adj[v] = new Bag_alg_1_4<Integer>();
        }
    }

    public Graph_model(In in) {
        this(in.readInt());
        int E = in.readInt();

        for (int i = 0; i < E; i++) {
            int v = in.readInt();
            int w = in.readInt();
            addEdge(v, w);
        }
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

package me.suiyueyu.algs4.sec4.exercise;

import edu.princeton.cs.algs4.In;
import me.suiyueyu.algs4.sec1.Bag_alg_1_4;


/**
 * Created by yzcc on 2016/5/5.
 * 4.1.3为Graph添加一个复制构造函数，它接受一幅图G然后创建并初始化这幅图的一个副本。G的用例对
 * 它做出的任何改动都不应该影响到他的副本
 * 4.1.4 为Graph添加一个方法hasEdge(), 他接受两个整型参数v和w。如果图含有边
 * v -> w，方法返回true，否则返回false
 * 4.1.5 修改Graph，不允许存在平行边和自环
 */
public class Ex_Graph_model {
    private final int V;
    private int E;
    private Bag_alg_1_4<Integer>[] adj;

    public Ex_Graph_model(int V) {
        this.V = V;
        this.E = 0;
        adj = (Bag_alg_1_4<Integer>[]) new Bag_alg_1_4[V];
        for (int v = 0; v < V; v++) {
            adj[v] = new Bag_alg_1_4<Integer>();
        }
    }

    public Ex_Graph_model(In in) {
        this(in.readInt());
        int E = in.readInt();

        for (int i = 0; i < E; i++) {
            int v = in.readInt();
            int w = in.readInt();
            addEdge(v, w);
        }
    }

    /**
     * Ex_4.1.4
     * Ex_Graph_model 生成函数
     * 使用一个没用的类来表明本图不允许自环
     *
     * @param in                     输入流
     * @param isGraphWithoutSelfLoop 只是一个flag区别构造函数，代码中并未使用
     */
    public Ex_Graph_model(In in, boolean isGraphWithoutSelfLoop) {
        this(in.readInt());
        int E = in.readInt();

        try {
            for (int i = 0; i < E; i++) {
                int v = in.readInt();
                int w = in.readInt();

                if (v == w) {
                    // 自环
                    throw new SelfLoopException();
                }

                addEdgeWithoutParallelEdges(v, w);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 加入边，不允许平行边
     *
     * @param v
     * @param w
     */
    public void addEdgeWithoutParallelEdges(int v, int w) {
        try {
            if (hasEdge(v, w)) {
                throw new ParallelEdgeException();
            }
            // 防止有向图
            if (hasEdge(w, v)) {
                throw new ParallelEdgeException();
            }
            adj[v].add(w);
            adj[w].add(v);
            E++;
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    /**
     * 复制构造函数 Ex_4.1.3
     * 接受一个Graph的参数，然后复制构造
     * @param G 复制构造的图
     */
    public Ex_Graph_model(Ex_Graph_model G) {
        this(G.V());
        this.E = G.E();

        for (int i = 0; i < V; i++) {
            for (int adjVertex : G.adj(i)) {
                addEdge(i, adjVertex);
            }
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

    /**
     * Ex_4.1.4
     * hasEdge 存在边
     * 判断是否存在边v->w
     *
     * @param v
     * @param w
     * @return 若存在返回true，不存在返回false
     */
    public boolean hasEdge(int v, int w) {
        for (int adjVertex : adj(v)) {
            if (adjVertex == w) {
                return true;
            }
        }
        return false;
    }

    public String toString() {
        String s = V + " vertices, " + E + " edges \n";
        for (int v = 0; v < V; v++) {
            s += v + ": ";
            for (int w : adj(v)) {
                s += w + " ";
            }
            s += "\n";
        }
        return s;
    }

    /**
     * 输入流中发现自环
     */
    private class SelfLoopException extends Exception {
    }

    /**
     * 平行边
     */
    private class ParallelEdgeException extends Exception{}
}

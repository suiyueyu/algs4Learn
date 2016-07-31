package me.suiyueyu.algs4.sec4.exercise;

import me.suiyueyu.algs4.sec1.WeightedQuickUnionUF_alg_1_5;
import me.suiyueyu.algs4.sec4.Graph_model;

/**
 * Created by yzcc on 2016/5/9.
 * 按照正文中的要求，用union-find算法实现4.1.2.3中搜索的API
 */
public class Ex_4_1_8 {
    private WeightedQuickUnionUF_alg_1_5 wg;
    private int s;// 起点s

    /**
     * 找到与起点s连通的所有节点
     *
     * @param G 图G
     * @param s 起点s
     */
    Ex_4_1_8(Graph_model G, int s) {
        wg = new WeightedQuickUnionUF_alg_1_5(G.V());
        this.s = s;

        for (int v = 0; v < G.V(); v++) {
            for (int w : G.adj(v)) {
                if (!wg.connected(w, v)) {
                    wg.union(w, v);
                }
            }
        }
    }

    /**
     * v和s是连通的么
     *
     * @param v 顶点v
     * @return 是否连通
     */
    boolean marked(int v) {
        return wg.connected(this.s, v);
    }

    /**
     * 与s连通的顶点总数
     *
     * @return int
     */
    int count() {
        int father = wg.find(s);
        return wg.size(father);
    }
}

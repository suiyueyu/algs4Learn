package me.suiyueyu.algs4.sec4.exercise;

import me.suiyueyu.algs4.sec4.Graph_model;

/**
 * Created by yzcc on 2016/5/18.
 * 证明在任意一幅连通图中都存在一个顶点，删去它(以及和它相连的所有边)不会影响到图的连通性
 * 编写一个深度优先搜索的方法找出这样一个节点。
 * 提示：留心那些相邻节点全部都被标记过的顶点。
 */
public class Ex_4_1_10 {
    private boolean[] marked;
    private int count;


    public Ex_4_1_10(Graph_model G, int s) {
        marked = new boolean[G.V()];
        dfs(G, s);
    }

    private void dfs(Graph_model G, int v) {
        marked[v] = true;
        count++;

        for (int w : G.adj(v)) {
            if (!marked[w]) {
                dfs(G, w);
            }
        }
    }

    public boolean marked(int w) {
        return marked[w];
    }

    public int count() {
        return count;
    }

    /**
     * findNotCutVertex 找到这个节点
     * 名字其实不太正确，cut vertex是一去掉则网络断路
     * 找到一个点，即使去掉也不会影响网络的连通性
     * 即找到一个点，其邻居按照dfs都已经被访问过
     *
     * @return index:int 节点的下标
     */
    public int findNotCutVertex(Graph_model G, int v) {
        marked[v] = true;
        count++;

        boolean existAdjVertexNotAccessed = false;
        for (int w : G.adj(v)) {
            // 注意这里判断的是节点是否被访问过，如果网络有多个连通分量，那么
            // 节点就不会被访问
            if (!marked[v]) {
                existAdjVertexNotAccessed = true;
                return findNotCutVertex(G, w);
            }
        }

        // 其实existAdjVertexNotAcessed没用
        // 因为递归结束，第一个找不到邻居进行dfs的节点就是我们要找的顶点

        // 所有邻居都被访问过了
        return v;
    }
}

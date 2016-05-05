package me.suiyueyu.algs4.sec4;

/**
 * Created by yzcc on 2016/5/5.
 * G是二分图吗？(双色问题)
 */
public class TwoColor {
    private boolean[] marked;
    private boolean[] color;
    private boolean isTwoColorable = true;

    public TwoColor(Graph_model G) {
        marked = new boolean[G.V()];
        color = new boolean[G.V()];
        for (int s = 0; s < G.V(); s++) {
            if (!marked[s]) {
                dfs(G, s);
            }
        }
    }

    private void dfs(Graph_model G, int v) {
        marked[v] = true;
        for (int w : G.adj(v)) {
            if (!marked[w]) {
                color[w] = !color[v];
                dfs(G, w);
            } else if (color[w] == color[v]) {
                isTwoColorable = false;
            }
        }
    }

    public boolean isBipartite() {
        return isTwoColorable;
    }
}

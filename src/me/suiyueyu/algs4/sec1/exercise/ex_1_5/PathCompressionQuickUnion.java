package me.suiyueyu.algs4.sec1.exercise.ex_1_5;

import edu.princeton.cs.algs4.StdDraw;
import me.suiyueyu.algs4.sec1.algs.Findable;

/**
 * Created by yzcc on 2016/8/14.
 * <p>
 * 1.5.12 使用路径压缩的quick-union算法。 根据路径压缩修改quick-union 算法(请见1.5.23节)，
 * 在find()方法中添加一个循环来将从p到根节点的路径上的每个出点都连接到根节点。
 * 给出一列输入，使该方法能够产生一条长度为4的路径。
 * 注意：该算法的所有操作的均摊成本已知为对数级别
 */
public class PathCompressionQuickUnion implements Findable {
    private int[] id;
    private int count;

    public PathCompressionQuickUnion(int N) {
        count = N;
        id = new int[N];
        for (int i = 0; i < N; i++) {
            id[i] = i;
        }
    }

    public int count() {
        return count;
    }

    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    @Override
    /**
     * 在find()方法中添加一个循环来将从p到根节点的路径上的每个出点都连接到根节点。
     * 给出一列输入，使该方法能够产生一条长度为4的路径。
     * @see <a href = "http://algs4.cs.princeton.edu/15uf/QuickUnionPathCompressionUF.java.html">solution</a>
     */
    public int find(int p) {
        int root = p;
        while (root != id[root])
            root = id[root];
        while (p != root) {
            int newp = id[p];
            id[p] = root;
            p = newp;
        }
        return root;
    }

    @Override
    public void union(int p, int q) {
        int pRoot = find(p);
        int qRoot = find(q);

        if (pRoot == qRoot) return;

        id[pRoot] = qRoot;

        count--;
    }

}

package me.suiyueyu.algs4.sec1.exercise.ex_1_5;

import me.suiyueyu.algs4.sec1.algs.Findable;

/**
 * Created by yzcc on 2016/8/15.
 * <p>
 * 1.5.13 使用路径压缩的quick-union 算法。修改加权quick-union算法(算法 1.5)，实现如练习1.5.12所述的路径压缩。
 * 给出一列输入，使该方法能够产生一颗高度为4的树。
 * <p>
 * 注意：该算法的所有操作的均摊操作一直被限定在反 Ackermann函数的范围之内，且对于实际应用中可能出现的所有N值均小于5
 */
public class PathCompressionWeightedQuickUnion implements Findable {
    private int[] sz;
    private int[] id;
    private int count;

    public PathCompressionWeightedQuickUnion(int N) {
        sz = new int[N];
        id = new int[N];
        count = N;
        for (int i = 0; i < N; i++) {
            sz[i] = 1;
            id[i] = i;
        }
    }

    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    public int count() {
        return count;
    }

    @Override
    public int find(int p) {
        int root = p;
        while (root != id[root]) {
            root = id[root];
        }

        int tmpFather;
        while (p != root) {
            tmpFather = id[p];
            id[p] = root;
            p = tmpFather;
        }

        return root;
    }

    @Override
    public void union(int p, int q) {
        int pRoot = find(p);
        int qRoot = find(q);
        if (pRoot == qRoot) return;
        if (sz[pRoot] < sz[qRoot]) {
            id[pRoot] = qRoot;
            sz[qRoot] += sz[pRoot];
            System.out.println("tie " + pRoot + " to " + qRoot);
        } else {
            id[qRoot] = id[pRoot];
            sz[pRoot] += sz[qRoot];
            System.out.println("tie " + qRoot + " to " + pRoot);
        }
        // 这个别忘了
        count--;
    }

    public static void main(String[] args) {
        PathCompressionWeightedQuickUnion pCWQU = new PathCompressionWeightedQuickUnion(10);

        pCWQU.union(0, 1);
        pCWQU.union(2, 3);
        pCWQU.union(4, 5);
        pCWQU.union(6, 7);

        pCWQU.union(0, 2);
        pCWQU.union(4, 6);
        pCWQU.union(0, 4);

        // 想产生高度为4的树，主要还是尽量少在长枝上用 find()，尽量在root节点去连接
        // 而且weight让我们尽量去连 大小相等的树

        // 书上这个最差的例子可以用
        // TODO: 2016/8/15 后面的题目感觉不适合做题?先放着进第二章
    }

}

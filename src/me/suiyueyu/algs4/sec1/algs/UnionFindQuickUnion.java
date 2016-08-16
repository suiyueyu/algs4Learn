package me.suiyueyu.algs4.sec1.algs;

/**
 * Created by yzcc on 2016/8/14.
 */
public class UnionFindQuickUnion implements Findable {
    private int[] id;
    private int count;

    public UnionFindQuickUnion(int N) {
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
    public int find(int p) {
        while (p != id[p]) p = id[p];
        return p;
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

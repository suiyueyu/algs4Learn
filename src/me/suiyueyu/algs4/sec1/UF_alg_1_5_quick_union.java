package me.suiyueyu.algs4.sec1;

/**
 * Created by boge on 2015/8/5.
 */
public class UF_alg_1_5_quick_union extends UF_alg_1_5{

    public UF_alg_1_5_quick_union(int N) {
        super(N);
    }

    @Override
    public int find(int p) {
        while (p!=id[p]){
            p = id[p];
        }
        return p;
    }

    @Override
    public void union(int p, int q) {
        int pRoot = find(p);
        int qRoot = find(q);
        if (pRoot == qRoot) return;
        id[pRoot] = qRoot;
        count --;
    }
}

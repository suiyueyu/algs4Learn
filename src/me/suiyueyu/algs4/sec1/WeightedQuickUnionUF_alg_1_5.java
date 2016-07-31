package me.suiyueyu.algs4.sec1;

/**
 * Created by boge on 2015/8/5.
 */
public class WeightedQuickUnionUF_alg_1_5 {
    private int[] id;
    private int[] sz;
    private int count;

    public WeightedQuickUnionUF_alg_1_5(int N){
        count = N;
        id = new int[N];
        for (int i = 0; i < N; i++) {
            id[i] = i;
        }
        sz = new int[N];
        for (int i = 0; i < N; i++) {
            sz[i] = 1;
        }
    }
    public int count(){
        return count;
    }
    public boolean connected(int p,int q){
        return find(p) == find(q);
    }
    public int find(int p){
        while(p != id[p]){
            p = id[p];
        }
        return p;
    }
    public void union(int p, int q){
        int i = find(p);
        int j = find(q);
        if (i == j) return ;
        if (sz[i] < sz[j]){
            id[i] = j;
            sz[j] += sz[i];
        }
        else{
            id[j] = i;
            sz[i] += sz[j];
        }
        count --;
    }

    /**
     * Ex_4_1_8 中要求对UF类进行拓展，要不然拿不到private的sz参数
     * 破坏了类的封装
     *
     * @param i
     * @return sz[i]
     */
    public int size(int i) {
        return sz[i];
    }
}

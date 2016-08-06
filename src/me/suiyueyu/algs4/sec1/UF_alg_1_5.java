package me.suiyueyu.algs4.sec1;


/**
 * Created by boge on 2015/8/5.
 */
abstract public class UF_alg_1_5 {
    protected int[] id;
    protected int count;

    public UF_alg_1_5(int N){
        count = N;
        id = new int[N];
        for (int i = 0;i < N; i++){
            id[i] = i;
        }
    }
    public int count(){
        return count;
    }
    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }
    abstract public int find(int p);
    abstract public void union(int p, int q);

    public static void main(String[] args){
//        int N = StdIn.readInt();
//        UF_alg_1_5 uf = new UF_alg_1_5(N);
//
//        while(!StdIn.isEmpty()){
//            int p = StdIn.readInt();
//            int q = StdIn.readInt();
//
//            if (uf.connected(p,q)){
//                continue;
//            }
//            uf.union(p,q);
//            StdOut.println(uf.count() + "components");
//        }
    }
}

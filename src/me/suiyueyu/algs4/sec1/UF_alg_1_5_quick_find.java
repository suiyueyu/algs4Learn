package me.suiyueyu.algs4.sec1;

import me.suiyueyu.algs4.sec1.UF_alg_1_5;

/**
 * Created by boge on 2015/8/5.
 */
public class UF_alg_1_5_quick_find extends UF_alg_1_5{
    public UF_alg_1_5_quick_find(int N) {
        super(N);
    }

    public int find(int p){
        return id[p];
    }

    @Override
    public void union(int p, int q) {
        int pID = find(p);
        int qID = find(q);

        if (pID == qID){
            return ;
        }

        for (int i = 0; i < id.length; i++){
            if (id[i] == pID) {
                id[i] = qID;
            }
        }
        count --;
    }

}

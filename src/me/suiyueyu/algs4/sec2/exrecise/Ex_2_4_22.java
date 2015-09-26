package me.suiyueyu.algs4.sec2.exrecise;

import me.suiyueyu.algs4.sec2.MaxPQ_algs_2_6;

/**
 * Created by yzcc on 2015/9/21.
 */
public class Ex_2_4_22  <Key extends  Comparable<Key>> extends MaxPQ_algs_2_6<Key> {
    int findNearestCapcity(int N){
        int capcity = 1;
        while(capcity <= N){
            capcity*=2;
        }
        return capcity;
    }
    Ex_2_4_22(int maxN){
        super(maxN);
    }
    private void resize(int newNMax){
        Key[] temp = (Key[]) new Comparable[newNMax];
        for (int i = 0 ; i < N; i++){
            temp[i] = pq[i];
        }
        pq = temp;
    }

    public void insert(Key v){
        if (N == pq.length){
            if ((pq.length % 2 == 0)){
                resize(2 * pq.length);
            } else{
                resize(findNearestCapcity(pq.length));
            }
        }

        pq[N++] = v;
    }

    public Key delMax(){
//        Key v = pq[--N];
//        pq[N] = null;
//        if (N > 0 && N == pq.length/4){
//            resize(pq.length/2);
//        }
//        return v;

        Key max = pq[1];
        exch(1, N--);
        pq[N+1] = null;
        sink(1);

        if (N > 0 && N == pq.length / 4){
            resize(pq.length / 2);
        }

        return max;
    }

}

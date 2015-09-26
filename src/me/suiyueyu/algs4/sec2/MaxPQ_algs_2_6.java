package me.suiyueyu.algs4.sec2;

/**
 * Created by yzcc on 2015/9/17.
 */
public class MaxPQ_algs_2_6 <Key extends  Comparable<Key>>{
    protected Key[] pq;
    protected int N = 0;

    public MaxPQ_algs_2_6(int maxN){
        pq = (Key[]) new Comparable[maxN + 1];
    }

    public boolean isEmpty(){
        return N==0;
    }

    public int size(){
        return N;
    }

    public void insert(Key v){
        pq[++N] = v;
        swim(N);
    }

    public Key delMax(){
        Key max = pq[1];
        exch(1, N--);
        pq[N+1] = null;
        sink(1);
        return max;
    }


    private boolean less(int i, int j){
        return (pq[i].compareTo(pq[j])) < 0;
    }

    protected void exch(int i, int j){
        Key t = pq[i];
        pq[i] = pq[j];
        pq[j] = t;
    }

    protected void swim(int k){
        while (k > 1 && less(k/2, k)){
            exch(k/2, k);
            k = k/2;
        }
    }

    protected void sink(int k){
        while (2*k <= N){
            int j = 2*k;
            if (j < N && less(j, j+1)){
                j++;
            }
            if (!less(k, j))
                break;
            exch(k, j);
            k = j;
        }
    }
}

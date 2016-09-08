package me.suiyueyu.algs4.sec3.algs;


import me.suiyueyu.algs4.sec3.SequentialSearchST_algs_3_1;

/**
 * Created by yzcc on 2016/8/29.
 */
public class SeparateChainingHashST<Key, Value> {
    private int N;
    private int M; // 槽的数量

    // 无顺序的ST
    private SequentialSearchST_algs_3_1<Key, Value>[] st;

    public SeparateChainingHashST() {
        this(997);
    }

    public SeparateChainingHashST(int M) {
        this.M = M;

        st = (SequentialSearchST_algs_3_1<Key, Value>[]) new SequentialSearchST_algs_3_1[M];
        for (int i = 0; i < M; i++) {
            st[i] = new SequentialSearchST_algs_3_1<Key, Value>();
        }
    }

    private int hash(Key key) {
        return (key.hashCode() & 0x7fffffff) % M;
    }

    public Value get(Key key) {
        return st[hash(key)].get(key);
    }

    public void put(Key key, Value val) {
        st[hash(key)].put(key, val);
    }


}

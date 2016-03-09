package me.suiyueyu.algs4.sec3;


import me.suiyueyu.algs4.sec1.Queue_alg_1_3;

import java.util.Queue;

/**
 * Created by yzcc on 2016/3/7.
 */
public class BinarySearchST_algs_3_2<Key extends Comparable<Key>, Value> {
    private Key[] keys;
    private Value[] values;
    private int N;

    public BinarySearchST_algs_3_2(int capacity) {
        keys = (Key[]) new Comparable[capacity];
        values = (Value[]) new Object[capacity];
    }

    public int size() {
        return N;
    }

    public boolean isEmpty() {
        return size() == 0;
    }
    public Value get(Key key) {
        if (isEmpty()) {
            return null;
        }
        int i = rank(key);
        if (i < N && (keys[i].compareTo(key) == 0)) {
            return values[i];
        } else {
            return null;
        }
    }

    public int rank(Key key) {
        int lo = 0;
        int hi = N - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            int cmp = key.compareTo(keys[mid]);
            if (cmp < 0) {
                hi = mid - 1;
            } else if (cmp > 0) {
                lo = mid + 1;
            } else {
                return mid;
            }
        }
        return lo; // 没找到，但是按照位置来说，他应该在这个点,这时候lo>hi
    }

    public void put(Key key, Value value) {
        int i = rank(key);
        if (i < N && (keys[i].compareTo(key) == 0)) {
            values[i] = value;
            return;
        }
        for (int j = N; j > i; j--) {
            keys[j] = keys[j - 1];
            values[j] = values[j - 1];
        }
        keys[i] = key;
        values[i] = value;
        N++;
    }

    public Key min() {
        return keys[0];
    }

    public Key max() {
        return keys[N - 1];
    }

    public Key select(int k) {
        return keys[k];
    }

    /**
     * floor 小于等于key的最大键
     *
     * @param key 查找的Key
     * @return keys[pos] 查找到的keys的位置
     */
    public Key floor(Key key) {
        int rank = rank(key);
        if (isEmpty()) {
            return null;
        } else {
            if (contains(keys[rank])) {
                return keys[rank];
            }
            int pos = (rank - 1 + N) % N;
            return keys[pos];
        }
    }

    /**
     * ceiling 大于等于key的最小键
     *
     * @param key 查找的key
     * @return Key 最小键
     */
    public Key ceiling(Key key) {
        int i = rank(key);
        return keys[i];
    }

    public boolean contains(Key key) {
        return get(key) != null;
    }

    public void delete(Key key) {
        put(key, null);
    }

    public Iterable<Key> keys(Key lo, Key hi) {
        Queue_alg_1_3<Key> q = new Queue_alg_1_3<Key>();

        for (int i = rank(lo); i < rank(hi); i++) {
            q.enqueue(keys[i]);
        }
        if (contains(hi)) {
            q.enqueue(keys[rank(hi)]);
        }
        return q;
    }

    Iterable<Key> keys() {
        return keys(min(), max());
    }

}

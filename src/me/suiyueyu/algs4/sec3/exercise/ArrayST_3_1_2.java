package me.suiyueyu.algs4.sec3.exercise;

import me.suiyueyu.algs4.sec1.Queue_alg_1_3;

/**
 * Created by yzcc on 2016/3/12.
 * 开发一个符号表实现ArrayST, 使用(无序)数组来实现我们的基本API.
 */
public class ArrayST_3_1_2<Key, Value> {
    private Key[] keys;
    private Value[] vals;
    private int N;
    private int capacity;

    public ArrayST_3_1_2(int capacity) {
        keys = (Key[]) new Object[capacity];
        vals = (Value[]) new Object[capacity];
        N = 0;
        this.capacity = capacity;
    }

    public ArrayST_3_1_2() {
        this(1);
    }

    public void resize(int max) {
        Key[] keysTemp = (Key[]) new Object[max];
        Value[] valsTemp = (Value[]) new Object[max];

        for (int i = 0; i < N; i++) {
            keysTemp[i] = keys[i];
            valsTemp[i] = vals[i];
        }

        // 释放原空间
        keys = null;
        vals = null;
        // 转移
        keys = keysTemp;
        vals = valsTemp;
        capacity = max;
    }

    public void put(Key key, Value val) {
        if (N == capacity) {

            resize(2 * capacity);
        }
        for (int i = 0; i < N; i++) {
            // 更新keys

            if (keys[i].equals(key)) {

                vals[i] = val;
                return;
            }
        }

        if (N < capacity) {
            // 没找到，作为新节点加入
            keys[N] = key;
            vals[N] = val;
            N++;
        }
    }

    public Value get(Key key) {
        for (int i = 0; i < N; i++) {
            if (keys[i].equals(key)) {
                return vals[i];
            }
        }
        return null;
    }

    public void delete(Key key) {
        int i = 0;
        boolean flag = false;

        for (i = 0; i < N; i++) {
            if (keys[i] == key) {
                flag = true;
                break;
            }
        }
        // contains(key)
        if (flag) {
            // 把这个位置填掉
            for (int j = i; j < N - 1; j++) {
                keys[j] = keys[j + 1];
                vals[j] = vals[j + 1];
            }
            keys[N - 1] = null;
            vals[N - 1] = null;
            N--;
        }

    }

    public boolean contains(Key key) {

        for (int i = 0; i < N; i++) {
            if (keys[i].equals(key)) {
                return true;
            }
        }
        return false;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public int size() {
        return N;
    }

    public Iterable<Key> keys() {
        Queue_alg_1_3<Key> q = new Queue_alg_1_3<>();

        for (int i = 0; i < N; i++) {
            q.enqueue(keys[i]);
        }
        return q;
    }

    public Iterable<Value> vals() {
        Queue_alg_1_3<Value> q = new Queue_alg_1_3<Value>();

        for (int i = 0; i < N; i++) {
            q.enqueue(vals[i]);
        }
        return q;
    }
}

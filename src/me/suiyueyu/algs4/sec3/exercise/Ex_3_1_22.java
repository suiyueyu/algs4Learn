package me.suiyueyu.algs4.sec3.exercise;

import me.suiyueyu.algs4.sec1.Queue_alg_1_3;

/**
 * Created by yzcc on 2016/5/2.
 * 自组织查找。自组织查找指的是一种能够将数组元素重新排序使得被访问频率较高的元素更容易被找到的算法。
 * 请修改你为3.1.2给出的答案，在每次查找命中的
 */
public class Ex_3_1_22<Key extends Comparable<Key>, Value> {

    // 与ArraySt中相同
    private Key[] keys;
    private Value[] vals;
    private int N;
    private int capacity;

    public Ex_3_1_22(int capacity) {
        keys = (Key[]) new Object[capacity];
        vals = (Value[]) new Object[capacity];
        N = 0;
        this.capacity = capacity;
    }

    public Ex_3_1_22() {
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

    /**
     * moveToFront 前移编码
     * 将第i个位置挪到开头，同时中间元素右移一位
     *
     * @param i 挪动至开头的元素下标
     */
    private void moveToFront(int i) {
        Key moveKey = keys[i];
        Value moveVal = vals[i];

        // 中间的移动
        for (int j = i; j >= 0; j--) {
            keys[i] = keys[i - 1];
            vals[i] = vals[i - 1];
        }

        keys[0] = moveKey;
        vals[0] = moveVal;
    }

    public Value get(Key key) {
        for (int i = 0; i < N; i++) {
            if (keys[i].equals(key)) {
                // 查找命中时
                Value val = vals[i];
                moveToFront(i);// 前移编码
                return val;
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


package me.suiyueyu.algs4.sec3.exercise;

import me.suiyueyu.algs4.sec1.Queue_alg_1_3;
import me.suiyueyu.algs4.sec2.Merge_alg_2_4;

/**
 * Created by yzcc on 2016/4/2.
 * 3.1.12 修改 BinarySearchST，用一个Item对象的数组而非两个平行数组来保存键和值。
 * 添加一个构造函数，接受一个Item的数组为参数并将其归并排序。
 *
 * @author yzcc
 */
public class EX_3_1_12<Key extends Comparable<Key>, Value> {

    private class Item implements Comparable<Item> {
        Key key;
        Value val;

        public Item(Key key, Value val) {
            this.key = key;
            this.val = val;
        }

        @Override
        public int compareTo(Item i) {
            return this.key.compareTo(i.key);
        }
    }

    private int N;
    private Item[] items;

    public EX_3_1_12(Item[] items) {

    }

    public EX_3_1_12(int capcity) {
        items = (Item[]) new Object[capcity];
        Merge_alg_2_4.sort(items);

    }

    public int size() {
        return N;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public int rank(Key key) {
        int lo = 0;
        int hi = N - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            int cmp = key.compareTo(items[mid].key);
            if (cmp < 0) {
                hi = mid - 1;
            } else if (cmp > 0) {
                lo = mid + 1;
            } else {
                return mid;
            }
        }
        return lo;
    }


    public Value get(Key key) {
        if (isEmpty()) {
            return null;
        }
        int i = rank(key);
        if ((i < N) && (items[i].key.compareTo(key) == 0)) {
            return items[i].val;
        }
        return null;
    }

    public void put(Key key, Value val) {
        int i = rank(key);
        if ((i < N) && (items[i].key.compareTo(key) == 0)) {
            items[i].val = val;
            return;
        }
        for (int j = N; j > i; j--) {
            items[j].key = items[j - 1].key;
            items[j].val = items[j - 1].val;
        }
        items[i].val = val;
        items[i].key = key;
        N++;
    }

    public boolean contains(Key key) {
        return get(key) != null;
    }

    public Key max() {
        return items[N - 1].key;
    }

    public Key min() {
        return items[0].key;
    }


    public Iterable<Key> keys(Key lo, Key hi) {
        Queue_alg_1_3<Key> q = new Queue_alg_1_3<Key>();

        for (int i = rank(lo); i < rank(hi); i++) {
            q.enqueue(items[i].key);
        }
        if (contains(hi)) {
            q.enqueue(items[rank(hi)].key);
        }
        return q;
    }

    public Iterable<Key> keys() {
        return keys(min(), max());
    }

    public static void main(String[] args) {

    }
}

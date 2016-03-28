package me.suiyueyu.algs4.sec3.exercise;

import me.suiyueyu.algs4.sec1.Queue_alg_1_3;
import me.suiyueyu.algs4.sec2.Quick_alg_2_5;

/**
 * Created by yzcc on 2016/3/14.
 * 开发一个符号表的实现 OrderedSequentialSearchST，使用有序列表来实现我们的符号表API
 */
public class OrderedSequentialSearchST_3_1_3<Key extends Comparable<Key>, Value> {
    private class Node {
        Key key;
        Value val;
        Node next;

        public Node(Key key, Value val, Node next) {
            this.key = key;
            this.val = val;
            this.next = next;
        }
    }

    private Node first;
    private int N;

    public void put(Key key, Value val) {
        int i = rank(key);
        Node rankNode = jumpTo(i);

        // 已经存在key，更新val
        if ((i < N) && rankNode.key.compareTo(key) == 0) {
            rankNode.val = val;
            return;
        }

        // key不存在，但已经找到应该插入的位置
        if (i == 0) {//应该插入到头部
            Node node = new Node(key, val, first);
            first = node;
        } else {
            Node parent = jumpTo(i - 1);
            Node node = new Node(key, val, rankNode);
            parent.next = node;
            N++;
        }
    }

    public Value get(Key key) {
        if (isEmpty()) {
            return null;
        }
        int i = rank(key);
        Node rankNode = jumpTo(i);
        if (i < N && (rankNode.key.compareTo(key) == 0)) {
            return rankNode.val;
        } else {
            return null;
        }


    }

    public void delete(Key key) {
        int rank = rank(key);
        Node parent = jumpTo(rank - 1);
        Node nodeToDel = parent.next;
        parent.next = nodeToDel.next;
        nodeToDel = null;

        N--;
        // todo: 只剩下一个节点的情况先不考虑
        // todo: 不存在的情况也先不考虑
    }

    public boolean contains(Key key) {
        for (Node x = first; x != null; x = x.next) {
            if (x.key.compareTo(key) == 0) {
                return true;
            }
        }
        return false;
    }

    public boolean isEmpty() {
        return 0 == size();
    }

    public int size() {
        return N;
    }

    public Key min() {
        if (isEmpty()) {
            return null;
        } else {
            return first.key;
        }
    }

    public Key max() {
        if (isEmpty()) {
            return null;
        } else {
            return jumpTo(size() - 1).key;
        }
    }

//    public Key floor(Key key);
//
//    public Key ceiling(Key key);

    /**
     * rank 查找
     * 小于key的键的数量
     * 实现的是顺序查找
     * 这里我在想是写成顺序查找的还是二分的
     * 顺序的话比较简单，但是数量大的时候比较慢，O(n)的效率
     * 二分的话也不难，就是跳键感觉比较麻烦，我没做索引，比如找第5000个位置，就得5000次x=x.next,不知道效率好不好
     *
     * @param key 查找的key
     * @return rank:int 查找到的位置or应该插入的位置
     */
    public int rank(Key key) {
        int rank = 0;
        for (Node x = first; x != null; x = x.next, rank++) {
            // 第一个比key大的键
            if (x.key.compareTo(key) > 0) {
                break;
            }
        }
        return rank;
    }

    private Node jumpTo(int rank) {
        Node x = first;

        for (int i = 0; (i < rank) && (x != null); i++) {
            x = x.next;
        }
        return x;
    }

//    public Key select();

//    public void deleteMax();
//
//    public void deleteMin();

//    public int size(int lo, int hi);

//    Iterable<Key> keys(Key lo, Key hi);

    public Iterable<Key> keys() {
        Queue_alg_1_3<Key> q = new Queue_alg_1_3<Key>();

        for (Node x = first; x != null; x = x.next) {
            q.enqueue(x.key);
        }
        return q;
    }
}

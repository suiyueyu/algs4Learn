package me.suiyueyu.algs4.sec3.algs;

import edu.princeton.cs.algs4.StdOut;
import me.suiyueyu.algs4.sec1.algs.Queue;

/**
 * Created by yzcc on 2016/8/27.
 */
public class BST<Key extends Comparable<Key>, Value> {
    private Node root;

    private class Node {
        private Key key;
        private Value val;
        private Node left, right;
        private int N;

        public Node(Key key, Value val, int N) {
            this.key = key;
            this.val = val;
            this.N = N;
        }
    }

    public int size() {
        return size(root);
    }

    public int size(Node x) {
        if (x == null) return 0;
        return x.N;
    }

    public Value get(Key key) {
        return get(root, key);
    }

    private Value get(Node x, Key key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp < 0) return get(x.left, key);
        else if (cmp == 0) return x.val;
        else return get(x.right, key);
    }

    public void put(Key key, Value val) {
        root = put(root, key, val);
    }

    private Node put(Node x, Key key, Value val) {
        if (x == null) return new Node(key, val, 1);

        int cmp = key.compareTo(x.key);
        if (cmp < 0) x.left = put(x.left, key, val);
        else if (cmp == 0) x.val = val;// 更新
        else x.right = put(x.right, key, val);

        // size
        // 当是新创节点(这个点在叶子节点上)的时候，left 和right都是0
        // 已有节点(更新节点的数据)上来说，x.left 和 x.right 都是已知的
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }

    public Key min() {
        return min(root).key;
    }

    private Node min(Node x) {
        if (x.left == null) return x;
        return min(x.left);
    }

    public Key max() {
        return max(root).key;
    }

    private Node max(Node x) {
        if (x.right == null) return x;
        return max(x.right);
    }

    public Key floor(Key key) {
        Node x = floor(root, key);
        if (x == null) return null;
        return x.key;
    }

    /**
     * x <= key
     * x的最大值
     *
     * @param x
     * @param key
     * @return
     */
    private Node floor(Node x, Key key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp == 0) return x;
        if (cmp < 0) return floor(x.left, key);
        Node t = floor(x.right, key);
        // 右枝中没有找到再大一点的
        if (t != null) return t;
        else return x;
    }

    public Key select(int k) {
        return select(root, k).key;
    }

    /**
     * 返回排名为k的节点
     *
     * @param x
     * @param k
     * @return
     */
    private Node select(Node x, int k) {
        if (x == null) return null;
        int t = size(x.left);
        if (t > k) return select(x.left, k);
        else if (t < k) return select(x.right, k - t - 1);
        else return x;
    }

    public int rank(Key key) {
        return rank(key, root);
    }

    /**
     * 返回以x为根节点的子树中，小于x.key 的键的数量
     *
     * @param key
     * @param x
     * @return
     */
    private int rank(Key key, Node x) {
        if (x == null) return 0;
        int cmp = key.compareTo(x.key);
        if (cmp < 0) return rank(key, x.left);
        else if (cmp > 0) return 1 + size(x.left) + rank(key, x.right);
        else return size(x.left);
    }

    public void deleteMin() {
        root = deleteMin(root);
    }

    /**
     * 删除x的左子孙x.left，x.left没有 左子孙，因此没有比left更小的节点, 他是min
     * 然后把x.left = x.left.right，即用删除节点的右子孙替代他的位置
     *
     * @param x
     * @return
     */
    private Node deleteMin(Node x) {
        // x是要被删掉的点
        if (x.left == null) return x.right;

        // 1. x不是要被删的点
        // 若x是被删的父亲，那么这里deleteMin的返回值就是x.left.right
        // 就是说让右孙子替代左儿子
        // 2. 如果不是删除点的父亲
        // 那么deleteMin直接返回自身，没有改变顺序
        // 但是需要更新下删除节点后，各个点记录的N值
        x.left = deleteMin(x.left);
        x.N = size(x.left) + size(x.right) + 1;
        // x不是要被删的点
        return x;
    }

    public void deleteMax() {
        root = deleteMax(root);
    }

    private Node deleteMax(Node x) {
        if (x.right == null) return x.left;

        // 类似的，最大节点只可能有左子树，把这个左子树放到父亲节点的右子树就完成任务
        x.right = deleteMax(x.right);
        // 总感觉这句更新size的可以做的更精细
        x.N = size(x.left) + size(x.right) + 1;
        return x;

    }

    public void delete(Key key) {
        root = delete(root, key);
    }

    private Node delete(Node x, Key key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);

        if (cmp < 0) x.left = delete(x.left, key);
        else if (cmp > 0) x.right = delete(x.right, key);
        else {
            if (x.right == null) return x.left;
            if (x.left == null) return x.right;
            Node t = x;
            // x是继任者
            x = min(t.right);
            // x是右树中最小的，现在由x来领导新的右树(去掉了x)
            x.right = deleteMin(t.right);
            // 左树不用管，直接交给继任者就行
            x.left = t.left;
        }
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }

    private void print(Node x) {
        if (x == null) return;
        print(x.left);
        StdOut.println(x.key);
        print(x.right);
    }

    public Iterable<Key> keys() {
        return keys(min(), max());
    }

    public Iterable<Key> keys(Key lo, Key hi) {
        Queue<Key> queue = new Queue<Key>();
        keys(root, queue, lo, hi);
        return queue;
    }

    private void keys(Node x, Queue<Key> queue, Key lo, Key hi) {
        if (x == null) return;
        int cmplo = lo.compareTo(x.key);
        int cmphi = hi.compareTo(x.key);

        // 这一段都在左子树上
        if (cmplo < 0) keys(x.left, queue, lo, hi);
        // 这个节点在这个区间里面
        if (cmplo <= 0 && cmphi >= 0) queue.enqueue(x.key);
        // 这一段都在右子树上
        if (cmphi > 0) keys(x.right, queue, lo, hi);
    }

}

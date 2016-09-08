package me.suiyueyu.algs4.sec3.algs;

/**
 * Created by yzcc on 2016/8/28.
 */
public class RedBlackBST<Key extends Comparable<Key>, Value> {
    private Node root;

    private class Node {
        private Key key;
        private Value val;
        private Node left, right;
        private COLOR color;// 这个color是说指向父节点边的颜色
        private int N;

        public Node(Key key, Value val, int N, COLOR color) {
            this.key = key;
            this.val = val;
            this.N = N;
            this.color = color;
        }
    }

    public int size() {
        return size(root);
    }

    public int size(Node x) {
        if (x == null) return 0;
        return x.N;
    }


    // 原文里面使用static final boolean 实现的，我这里改成了enum
    private enum COLOR {
        RED, BLACK;
    }

    private boolean isRed(Node x) {
        if (x == null) return false;
        return x.color == COLOR.RED;
    }

    /**
     * 左旋
     * 交换子树, 变换颜色， 更新N
     *
     * @param h
     * @return
     */
    private Node rotateLeft(Node h) {
        Node x = h.right;

        // 交换子树
        h.right = x.left;
        x.left = h;

        // 变换颜色
        x.color = h.color;
        h.color = COLOR.RED;

        // 更新N
        x.N = h.N;
        h.N = size(h.left) + size(h.right) + 1;
        return x;
    }

    private Node rotateRight(Node h) {
        Node x = h.left;
        h.left = x.right;
        x.right = h;
        x.color = h.color;
        h.color = COLOR.RED;
        x.N = h.N;
        h.N = 1 + size(h.left) + size(h.right);
        return x;
    }

    private void flipColors(Node h) {
        h.color = COLOR.RED;
        h.left.color = COLOR.BLACK;
        h.right.color = COLOR.BLACK;
    }

    public void put(Key key, Value val) {
        root = put(root, key, val);
        // 根节点总为黑
        root.color = COLOR.BLACK;
    }

    private Node put(Node h, Key key, Value val) {
        if (h == null) return new Node(key, val, 1, COLOR.RED);

        int cmp = key.compareTo(h.key);
        if (cmp < 0) h.left = put(h.left, key, val);
        else if (cmp > 0) h.right = put(h.right, key, val);
        else h.val = val;

        if (isRed(h.right) && !isRed(h.left)) h = rotateLeft(h);
        if (isRed(h.left) && isRed(h.left.left)) h = rotateRight(h);
        if (isRed(h.left) && isRed(h.right)) flipColors(h);

        h.N = size(h.left) + size(h.right) + 1;
        return h;
    }

}

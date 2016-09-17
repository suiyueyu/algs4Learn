package me.suiyueyu.algs4.sec2.appendix;

import java.util.Comparator;

/**
 * Created by yzcc on 2016/9/17.
 * <p>
 * 斜堆(Skew heap)也叫自适应堆(self-adjusting heap)，它是左倾堆的一个变种。和左倾堆一样，它通常也用于实现优先队列；
 * 作为一种自适应的左倾堆，它的合并操作的时间复杂度也是O(lg n)。
 * 它与左倾堆的差别是：
 * (01) 斜堆的节点没有"零距离"这个属性，而左倾堆则有。
 * (02) 斜堆的合并操作和左倾堆的合并操作算法不同。
 * <p>
 * 斜堆的合并操作
 * (01) 如果一个空斜堆与一个非空斜堆合并，返回非空斜堆。
 * (02) 如果两个斜堆都非空，那么比较两个根节点，取较小堆的根节点为新的根节点。将"较小堆的根节点的右孩子"和"较大堆"进行合并。
 * (03) 合并后，交换新堆根节点的左孩子和右孩子。
 * 第(03)步是斜堆和左倾堆的合并操作差别的关键所在，如果是左倾堆，则合并后要比较左右孩子的零距离大小，
 * 若右孩子的零距离 > 左孩子的零距离，则交换左右孩子；最后，在设置根的零距离。
 */
public class SkewHeap<Key extends Comparable<Key>> {
    private SkewNode<Key> root;

    // 还是得仔细考虑下泛型怎么声明
    // 这个key和外层没啥关系，声明成other也行
    // 或者 声明的key不带extends Comparable 也不行，调用不出comparTo方法
    private class SkewNode<Key extends Comparable<Key>> {
        Key key;
        SkewNode<Key> left;
        SkewNode<Key> right;

        public SkewNode(Key key, SkewNode<Key> left, SkewNode<Key> right) {
            this.key = key;
            this.left = left;
            this.right = right;
        }

        public String toString() {
            return "Key : " + key;
        }
    }

    public void merge(SkewHeap<Key> other) {
        this.root = merge(this.root, other.root);
    }

    private SkewNode<Key> merge(SkewNode<Key> x, SkewNode<Key> y) {
        // (01) 如果一个空斜堆与一个非空斜堆合并，返回非空斜堆。
        if (x == null) return y;
        if (y == null) return x;

        //(02) 如果两个斜堆都非空，那么比较两个根节点，取较小堆的根节点为新的根节点。
        // 将"较小堆的根节点的右孩子"和"较大堆"进行合并。

        // 保持 x.key 较小
        if (x.key.compareTo(y.key) > 0) {
            SkewNode<Key> tmp = x;
            x = y;
            y = tmp;
        }

        // 新的右子树
        SkewNode<Key> tmp = merge(x.right, y);

        // (03) 合并后，交换新堆根节点的左孩子和右孩子。
        x.right = x.left;
        x.left = tmp;

        return x;
    }

    public void insert(Key key) {
        SkewNode<Key> node = new SkewNode<>(key, null, null);

        this.root = merge(this.root, node);
    }

    public Key remove() {
        if (root == null) return null;

        Key key = this.root.key;

        SkewNode<Key> left = this.root.left;
        SkewNode<Key> right = this.root.right;

        this.root = null;
        this.root = merge(left, right);

        return key;
    }
}

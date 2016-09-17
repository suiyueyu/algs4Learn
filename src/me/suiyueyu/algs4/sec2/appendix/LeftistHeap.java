package me.suiyueyu.algs4.sec2.appendix;

/**
 * Created by yzcc on 2016/9/17.
 * 左倾堆
 * 左倾堆(leftist tree 或 leftist heap)，又被成为左偏树、左偏堆，最左堆等。
 * 它和二叉堆一样，都是优先队列实现方式。当优先队列中涉及到"对两个优先队列进行合并"的问题时，
 * 二叉堆的效率就无法令人满意了，而本文介绍的左倾堆，则可以很好地解决这类问题。
 * <p>
 * 它的节点除了和二叉树的节点一样具有左右子树指针外，还有两个属性：键值和零距离。
 * (01) 键值的作用是来比较节点的大小，从而对节点进行排序。
 * (02) 零距离(英文名NPL，即Null Path Length)则是从一个节点到一个"最近的不满节点"的路径长度。
 * 不满节点是指该该节点的左右孩子至少有有一个为NULL。叶节点的NPL为0，NULL节点的NPL为-1。
 * <p>
 * 左倾堆有以下几个基本性质：
 * [性质1] 节点的键值小于或等于它的左右子节点的键值。
 * [性质2] 节点的左孩子的NPL >= 右孩子的NPL。
 * [性质3] 节点的NPL = 它的右孩子的NPL + 1。
 * <p>
 * 合并操作是左倾堆的重点。合并两个左倾堆的基本思想如下：
 * (01) 如果一个空左倾堆与一个非空左倾堆合并，返回非空左倾堆。
 * (02) 如果两个左倾堆都非空，那么比较两个根节点，取较小堆的根节点为新的根节点。
 * 将"较小堆的根节点的右孩子"和"较大堆"进行合并。
 * (03) 如果新堆的右孩子的NPL > 左孩子的NPL，则交换左右孩子。
 * (04) 设置新堆的根节点的NPL = 右子堆NPL + 1
 *
 * @see <a href="http://www.cnblogs.com/skywang12345/p/3638384.html">左倾堆</a>
 */
public class LeftistHeap<Key extends Comparable<Key>> {

    private LeftistNode<Key> root;

    private class LeftistNode<Key> {
        Key key;
        int npl; // 零路经长度(Null Path Length)
        LeftistNode<Key> left;
        LeftistNode<Key> right;

        public LeftistNode(Key key, LeftistNode<Key> left, LeftistNode<Key> right) {
            this.key = key;
            this.npl = 0;
            this.left = left;
            this.right = right;
        }

        public String toString() {
            return "key: " + key;
        }
    }

    public void merge(LeftistHeap that) {
        this.root = merge(this.root, that.root);
    }

    // 原来的声明是 private LeftistNode merge(LeftistNode x,
    //    LeftistNode y )
    // 我觉得这个加入是挺重要的，因为涉及到堆的合并问题
    // 但是如果这么写的话，就要求我也给 LeftistNode 类型参数
    // 写成 LeftistNode<Key> 不知道是否重要
    private LeftistNode<Key> merge(LeftistNode<Key> x, LeftistNode<Key> y) {

        // (01) 如果一个空左倾堆与一个非空左倾堆合并，返回非空左倾堆。
        if (x == null) return y;
        if (y == null) return x;

        // (02) 如果两个左倾堆都非空，那么比较两个根节点，
        // 取较小堆的根节点为新的根节点。
        // 将"较小堆的根节点的右孩子"和"较大堆"进行合并。
        if (x.key.compareTo(y.key) > 0) {
            LeftistNode tmp = x;
            x = y;
            y = tmp;
        }
        // 现在 x.key < y.key

        x.right = merge(x.right, y);

        // 跳出递归的条件是 x == null x.right 是 null
        // 或者  y == null, 另一边y为空
        // 就是说 assert(右子节点完成了merge过程)

        //(03) 如果新堆的右孩子的NPL > 左孩子的NPL，则交换左右孩子。
        if (x.left == null || x.left.npl < x.right.npl) {
            LeftistNode tmp = x.left;
            x.left = x.right;
            x.right = tmp;
        }

        // npl 设置
        // 这里有一个warning 说 x.left 总是 null
        if (x.right == null || x.left == null) {
            x.npl = 0;
        } else {
            x.npl = (x.left.npl > x.right.npl) ?
                    x.right.npl + 1 : x.left.npl + 1;
        }

        return x;
    }

    public void insert(Key key) {
        LeftistNode<Key> node = new LeftistNode<Key>(key, null, null);

//        assert (node!=null) : "节点创建失败";
        this.root = merge(this.root, node);
    }


    public Key remove() {
        if (root == null) return null;

        Key key = root.key;
        LeftistNode<Key> left = this.root.left;
        LeftistNode<Key> right = this.root.right;

        this.root = null;
        this.root = merge(left, right);

        return key;
    }

}

package me.suiyueyu.algs4.sec2.appendix;


/**
 * Created by yzcc on 2016/9/17.
 *
 * @see <a href = "http://www.cnblogs.com/skywang12345/p/3656098.html"></a>
 * <p>
 * 二项堆和之前所讲的堆(二叉堆、左倾堆、斜堆)一样，也是用于实现优先队列的。
 * 二项堆是指满足以下性质的二项树的集合：
 * (01) 每棵二项树都满足最小堆性质。即，父节点的关键字 <= 它的孩子的关键字。
 * (02) 不能有两棵或以上的二项树具有相同的度数(包括度数为0)。
 * 换句话说，具有度数k的二项树有0个或1个。
 * <p>
 * 二项堆的第(01)个性质保证了二项堆的最小节点就是某个二项树的根节点，
 * 第(02)个性质则说明结点数为n的二项堆最多只有log{n} + 1棵二项树。
 * 实际上，将包含n个节点的二项堆，表示成若干个2的指数和(或者转换成二进制)，
 * 则每一个2个指数都对应一棵二项树。
 * 例如，13(二进制是1101)的2个指数和为13=23 + 22+ 20,
 * 因此具有13个节点的二项堆由度数为3, 2, 0的三棵二项树组成。
 */
public class BinomialHeap<Key extends Comparable<Key>> {

    private BinomialHeap<Key> root;

    private class BinomialNode<Key extends Comparable<Key>> {
        Key key;
        int degree;
        BinomialNode<Key> child;
        BinomialNode<Key> parent;
        BinomialNode<Key> next;

        public BinomialNode(Key key) {
            this.key = key;
            this.degree = 0;
            this.child = null;
            this.parent = null;
            this.next = null;
        }

        public String toString() {
            return "Key : " + key;
        }
    }

    /**
     * 合并操作是二项堆的重点，它的添加操作也是基于合并操作来实现的。
     * 合并两个二项堆，需要的步骤概括起来如下：
     * <p>
     * (01) 将两个二项堆的根链表合并成一个链表。
     * 合并后的新链表按照"节点的度数"单调递增排列。
     * (02) 将新链表中"根节点度数相同的二项树"连接起来，
     * 直到所有根节点度数都不相同。
     *
     * @param h1
     * @param h2
     * @return
     */
    private BinomialNode<Key> merge(BinomialNode<Key> h1, BinomialNode<Key> h2) {
        if (h1 == null) return h2;
        if (h2 == null) return h1;

        // root是新堆的根，h3用来遍历h1和h3的。
        BinomialNode<Key> pre_h3,
                h3,
                root = null;

        pre_h3 = null;

        //整个while，h1, h2, pre_h3, h3都在往后顺移
        while ((h1 != null) && (h2 != null)) {
            if (h1.degree <= h2.degree) {
                h3 = h1;
                h1 = h1.next;
            } else {
                h3 = h2;
                h2 = h2.next;
            }

            if (pre_h3 == null) {
                pre_h3 = h3;
                root = h3;
            } else {
                pre_h3.next = h3;
                pre_h3 = h3;
            }

            if (h1 != null) {
                h3.next = h1;
            } else {
                h3.next = h2;
            }

        }
        return root;
    }


}

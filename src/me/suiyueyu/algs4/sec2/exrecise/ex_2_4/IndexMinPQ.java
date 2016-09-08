package me.suiyueyu.algs4.sec2.exrecise.ex_2_4;

import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;

/**
 * Created by yzcc on 2016/9/2.
 * 2.4.33 索引优先队列的实现
 * 按照2.4.6.6 节描述修改算法2.6来实现索引优先队列API中的基本操作：
 * 使用pq[] 保存索引， 添加一个数组keys[] 来保存元素，再添加一个数组qp[] 来保存pq[] 的这些数据结构。
 * 若i不在队列之中，则总是令qp[i] = -1 并添加一个方法 contains()来检测这种情况。
 * 你需要修改辅助函数exch() 和 less(), 但不需要修改sink() 和 swim()
 * <p>
 * 2.4.34 索引优先队列的实现 ( 附加操作 )。
 * 向练习 2.4.33 的实现中添加minIndex(), change() 和delete() 方法
 *
 * @see edu.princeton.cs.algs4.IndexMinPQ
 * 别的不说，这个实现的库函数貌似和别的风格不太一样，是不是优秀学生作品?
 */
public class IndexMinPQ<Key extends Comparable<Key>> implements Iterable<Integer> {
    private int N;
    private int[] pq; // 索引二叉堆，由1开始
    private int[] qp; // 逆序： qp[pq[i]] = pq[qp[i]] = i;
    private Key[] keys;

    public IndexMinPQ(int maxN) {
        if (maxN < 0) throw new IllegalArgumentException();
        keys = (Key[]) new Comparable[maxN + 1];
        pq = new int[maxN + 1];
        qp = new int[maxN + 1];
        N = 0;
        for (int i = 0; i <= maxN; i++) qp[i] = -1;
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public boolean contains(int k) {
        return qp[k] != -1;
    }

    public void insert(int k, Key key) {
        N++;
        qp[k] = N;
        pq[N] = k;
        keys[k] = key;
        swim(N);

        print();
    }

    public Key min() {
        return keys[pq[1]];
    }

    public int delMin() {
        int indexOfMin = pq[1];
        exch(1, N--);
        sink(1);

        // 去掉这个元素
        keys[pq[N + 1]] = null;
        qp[pq[N + 1]] = -1;

        return indexOfMin;
    }

    private void swim(int k) {
        while (k > 1 && greater(k / 2, k)) {
            exch(k / 2, k);
            k /= 2;
        }
    }

    private void sink(int k) {
        while (2 * k <= N) {
            int j = 2 * k;
            if (j < N && greater(j, j + 1)) j++;
            if (!greater(k, j)) break;
            exch(k, j);
            k = j;
        }
    }

    /**
     * 索引的移动，不仅是pq
     * qp也得改
     * keys不动
     *
     * @param i
     * @param j
     */
    private void exch(int i, int j) {
        int tmp = pq[i];
        pq[i] = pq[j];
        pq[j] = tmp;

        // qp
        // 直接等号就行了
        qp[pq[i]] = i;
        qp[pq[j]] = j;
    }

    private boolean greater(int i, int j) {
        return keys[pq[i]].compareTo(keys[pq[j]]) > 0;
    }

    public int minIndex() {
        return pq[1];
    }

    /**
     * 修改
     *
     * @param k
     * @param key
     * @deprecated 示例代码中不推荐使用
     */
    public void change(int k, Key key) {
        keys[k] = key;
        swim(qp[k]);
        sink(qp[k]);
    }

    public void delete(int k) {
        int index = qp[k];
        exch(index, N--);
        swim(index);
        sink(index);
        keys[k] = null;
        qp[k] = -1;
    }

    @Override
    public Iterator<Integer> iterator() {
        return new HeapIterator();
    }

    private class HeapIterator implements Iterator<Integer> {

        private IndexMinPQ<Key> copy;

        public HeapIterator() {
            copy = new IndexMinPQ(pq.length - 1);
            for (int i = 1; i <= N; i++) {
                copy.insert(pq[i], keys[pq[i]]);
            }
        }

        @Override
        public boolean hasNext() {
            return !copy.isEmpty();
        }

        @Override
        public Integer next() {
            if (!hasNext()) throw new UnsupportedOperationException("No such operator");
            return copy.delMin();
        }
    }


    public void print() {
        System.out.println("pq : ");
        for (int i = 0; i <= N; i++) {
            System.out.print(pq[i] + " ");
        }
        System.out.println();

        System.out.println("qp : ");
        for (int i = 0; i <= N; i++) {
            System.out.print(qp[i] + " ");
        }
        System.out.println();

        System.out.println("keys : ");
        for (int i = 0; i <= N; i++) {
            System.out.print(keys[i] + " ");
        }
        System.out.println();
        System.out.println("---------------------");
    }

    /**
     * Unit tests the <tt>IndexMinPQ</tt> data type.
     */
    public static void main(String[] args) {
        // insert a bunch of strings
        String[] strings = {"it", "was", "the", "best", "of", "times", "it", "was", "the", "worst"};

        IndexMinPQ<String> pq = new IndexMinPQ<String>(strings.length);
        for (int i = 0; i < strings.length; i++) {
            pq.insert(i, strings[i]);
        }

        // delete and print each key
        while (!pq.isEmpty()) {
            int i = pq.delMin();
            StdOut.println(i + " " + strings[i]);
        }
        StdOut.println();

        // reinsert the same strings
        for (int i = 0; i < strings.length; i++) {
            pq.insert(i, strings[i]);
        }

        // print each key using the iterator
        for (int i : pq) {
            StdOut.println(i + " " + strings[i]);
        }
        while (!pq.isEmpty()) {
            pq.delMin();
        }

    }

}

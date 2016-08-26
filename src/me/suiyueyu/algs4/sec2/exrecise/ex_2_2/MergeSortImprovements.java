package me.suiyueyu.algs4.sec2.exrecise.ex_2_2;

import edu.princeton.cs.algs4.StdOut;

/**
 * Created by yzcc on 2016/8/17.
 * 2.2.11 改进。 实现2.2.2 节所述的对递归排序的三项改进
 * 加快小数组的排序速度
 * 检测数组是否已经有序
 * 以及通过在递归中交换参数来避免数组复制
 *
 * @see <a href="http://algs4.cs.princeton.edu/22mergesort/MergeX.java.html">Solution: MergeX.java</a>
 */
public class MergeSortImprovements {

    // 避免类初始化
    private MergeSortImprovements() {
    }

    public static final int SMALL_ARRAY_SIZE = 15;

    public static void sort(Comparable[] a) {
        // 这里的复制看来是少不了了
        Comparable[] aux = a.clone();
        sort(aux, a, 0, a.length - 1);
    }

    public static void sort(Comparable[] src, Comparable[] dst, int lo, int hi) {

        if (lo >= hi) return;

        // 加快小数组的排序速度
        if (hi - lo + 1 < SMALL_ARRAY_SIZE) {
            Insertion.sort(dst, lo, hi);
            return;
        }

        // improve: dst中的是有序的
        int mid = lo + (hi - lo) / 2;
        sort(dst, src, lo, mid);
        sort(dst, src, mid + 1, hi);

        // 检测数组是否已经有序
        if (!less(src[mid + 1], src[mid])) {
//            for (int i = lo; i <= hi; i++){
//                dst[i] = src[i];
//            }
//            return;

            // 据说这样比上面的快
            // 这个System.arraycopy 是一个native方法，简单说，是java通过JNI(java native interface)调用其他语言(例如c++)的描述
            // @see <a href="http://stackoverflow.com/questions/6101311/what-is-the-native-keyword-in-java-for"></a>
            // http://blog.csdn.net/xw13106209/article/details/6989415
            System.arraycopy(src, lo, dst, lo, hi - lo + 1);
            return;
        }
        // TODO: 2016/8/17 检查下数组来回复制，写错了

        merge(src, dst, lo, mid, hi);
    }

    public static void merge(Comparable[] src, Comparable[] dst, int lo, int mid, int hi) {
        // 检测数组是否已经有序
        int i = lo, j = mid + 1;

        for (int k = lo; k <= hi; k++) {
            if (i > mid) dst[k] = src[j++];
            else if (j > hi) dst[k] = src[i++];
            else if (less(src[i], src[j])) dst[k] = src[i++];
            else dst[k] = src[j++];
        }


    }

    public static boolean less(Comparable a, Comparable b) {
        return a.compareTo(b) < 0;
    }

    private static void exch(Comparable[] a, int i, int j) {
        Comparable tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }

    private static void show(Comparable[] a) {
        for (int i = 0; i < a.length; i++) {
            StdOut.print(a[i] + " ");
        }
        StdOut.println();
    }

    public static void main(String[] args) {
        Integer[] i = new Integer[]{2, 5, 3, 6, 7, 3, 1, 0, 9};
        sort(i);
        show(i);
    }

}

class Insertion {
    public static void sort(Comparable[] a) {
        int N = a.length;

        // exch(a, min, 0);
        for (int i = N - 1; i > 0; i--) {
            if (less(a[i], a[i - 1])) {
                exch(a, i, i - 1);
            }
        }

        for (int i = 1; i < N; i++) {
            Comparable tmp = a[i];
            int j = i;
            for (; less(tmp, a[j - 1]); j--) {
                a[j] = a[j - 1];
            }
            a[j] = tmp;
        }
    }

    public static void sort(Comparable[] a, int lo, int hi) {
        int N = hi - lo + 1;

        for (int i = hi; i > lo; i--) {
            if (less(a[i], a[i - 1])) {
                exch(a, i, i - 1);
            }
        }

        for (int i = lo + 1; i <= hi; i++) {
            Comparable tmp = a[i];
            int j = i;
            for (; less(tmp, a[j - 1]); j--) {
                a[j] = a[j - 1];
            }
            a[j] = tmp;
        }

    }

    public static boolean less(Comparable a, Comparable b) {
        return a.compareTo(b) < 0;
    }

    private static void exch(Comparable[] a, int i, int j) {
        Comparable tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }

    private static void show(Comparable[] a) {
        for (int i = 0; i < a.length; i++) {
            StdOut.print(a[i] + " ");
        }
        StdOut.println();
    }


}
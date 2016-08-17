package me.suiyueyu.algs4.sec2.algs;

/**
 * Created by yzcc on 2016/8/17.
 * <p>
 * 任意长度数组N，排序时间和NlogN 成正比
 * 缺点，额外空间和N成正比
 */
public class Merge {
    private static Comparable[] aux;// 辅助数组，额外空间

    public static void sort(Comparable[] a) {
        aux = new Comparable[a.length];
        sort(a, 0, a.length - 1);
    }

    private static void sort(Comparable[] a, int lo, int hi) {
        if (hi <= lo) return;
        int mid = lo + (hi - lo) / 2;
        sort(a, lo, mid);
        sort(a, mid + 1, hi);
        merge(a, lo, mid, hi);
    }

    public static void merge(Comparable[] a, int lo, int mid, int hi) {
        int i = lo, j = mid + 1;

        for (int k = lo; k <= hi; k++) {
            aux[k] = a[k];
        }
        for (int k = lo; k <= hi; k++) {
            if (i > mid) a[k] = aux[j++];
            else if (j > hi) a[k] = aux[i++];
            else if (less(aux[j], aux[i])) a[k] = aux[j++];
            else a[k] = aux[i++];
        }

    }

    private static boolean less(Comparable a, Comparable b) {
        return a.compareTo(b) < 0;
    }

}

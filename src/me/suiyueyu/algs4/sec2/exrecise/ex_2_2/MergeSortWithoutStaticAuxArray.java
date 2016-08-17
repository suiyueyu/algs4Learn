package me.suiyueyu.algs4.sec2.exrecise.ex_2_2;

import edu.princeton.cs.algs4.StdOut;

/**
 * Created by yzcc on 2016/8/17.
 * 在库函数中使用aux[] 这样的静态数组是不妥当的，因为可能会有多个程序同时使用这个类。
 * 实现一个不用静态数组的Merge类，但也不要将aux[] 变为 merge() 的局部变量(请见本节的答疑部分)
 * 提示：可以将辅助数组作为参数传递给递归的 sort() 方法。
 */
public class MergeSortWithoutStaticAuxArray {

    public static void sort(Comparable[] a) {
        int N = a.length;
        Comparable[] aux = new Comparable[N];

        for (int sz = 1; sz < N; sz = sz + sz) {
            for (int lo = 0; lo < N - sz; lo += sz + sz) {
                merge(a, lo, lo + sz - 1,
                        Math.min(lo + 2 * sz - 1, N - 1), aux);
            }
        }
    }

    /**
     * merge
     *
     * @param a
     * @param lo
     * @param mid
     * @param hi
     * @param aux 辅助数组
     */
    public static void merge(Comparable[] a, int lo, int mid, int hi, Comparable[] aux) {
        for (int k = lo; k <= hi; k++) {
            aux[k] = a[k];
        }
        int i = lo, j = mid + 1;

        for (int k = lo; k <= hi; k++) {
            if (i > mid) a[k] = aux[j++];
            else if (j > hi) a[k] = aux[i++];
            else if (less(aux[i], aux[j])) a[k] = aux[i++]; // 这里的aux在比较，别写错了
            else a[k] = aux[j++];
        }
    }

    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }


}

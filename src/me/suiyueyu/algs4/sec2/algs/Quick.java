package me.suiyueyu.algs4.sec2.algs;

import edu.princeton.cs.algs4.StdRandom;

/**
 * Created by yzcc on 2016/8/31.
 */
public class Quick {
    public static void sort(Comparable[] a) {
        StdRandom.shuffle(a);
        sort(a, 0, a.length - 1);
    }

    private static void sort(Comparable[] a, int lo, int hi) {
        if (hi <= lo) return;
        int j = partition(a, lo, hi);
        sort(a, lo, j - 1);
        sort(a, j, hi);
    }

    private static int partition(Comparable[] a, int lo, int hi) {
        // 取lo和hi+1 都是因为下面直接++ -- 了
        int i = lo;
        int j = hi + 1;
        Comparable v = a[lo];
        while (true) {
            // 找一个比v大的，找一个比v小的
            // 然后他们交换
            while (less(a[++i], v)) if (i == hi) break;
            while (less(v, a[--j])) if (j == lo) break;
            if (i >= j) break;
            exch(a, i, j);
        }
        exch(a, lo, j);
        return j;
    }

    private static boolean less(Comparable a, Comparable b) {
        return a.compareTo(b) < 0;
    }

    private static void exch(Comparable[] a, int i, int j) {
        Comparable tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }
}

package me.suiyueyu.algs4.sec2.algs;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

/**
 * Created by yzcc on 2016/8/15.
 * <p>
 * shell排序的思想是数组中任意间隔h的元素都是有序的。这样的数组被称为h有序数组
 * <p>
 * Shell 更高效的原因是他权衡了子数组的规模和有序性。排序之初，各个子数组都很短，排序之后，子数组都是部分有序的，
 * 这两种情况都很适合插入排序。
 * <p>
 * 希尔排序是唯一无法准确描述其对于乱序的数组的性能特征的排序方法
 */
public class Shell {
    public static void sort(Comparable[] a) {
        int N = a.length;
        int h = 1;
        while (h < N / 3) {
            h = 3 * h + 1;// 1 4 13 40 121 364 1093
        }
        while (h >= 1) {
            for (int i = h; i < N; i++) {
                for (int j = i; j >= h && less(a[j], a[j - h]); j -= h)
                    exch(a, j, j - h);
            }
            h = h / 3;
        }
    }

    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    private static void exch(Comparable[] a, int i, int j) {
        Comparable t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    private static void show(Comparable[] a) {
        for (int i = 0; i < a.length; i++) {
            StdOut.print(a[i] + " ");
        }
        StdOut.println();
    }

    public static boolean isSorted(Comparable[] a) {
        for (int i = 0; i < a.length; i++) {
            if (less(a[i], a[i - 1])) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        String[] a = In.readStrings();
//        sort(a);
        assert isSorted(a);
        show(a);
    }

}

package me.suiyueyu.algs4.sec2.algs;

/**
 * Created by yzcc on 2016/9/1.
 */
public class Quick3ways {

    private static void sort(Comparable[] a, int lo, int hi) {
        if (hi <= lo) return;
        int lt = lo,
                i = lo + 1,
                gt = hi;
        Comparable v = a[lo]; // 空出lo的位置

//            < v  |   =v    |   > v
//        | -------| ------- | ------ |
//        lo      lt   i     gt       hi

        while (i <= gt) {
            int cmp = a[i].compareTo(v);
            if (cmp < 0) exch(a, i++, lt++);
            else if (cmp > 0) exch(a, i, gt--);// i没动
            else i++;
        }
        sort(a, lo, lt - 1);
        sort(a, gt + 1, hi);
    }

    private static void exch(Comparable[] a, int i, int j) {
        Comparable tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }
}

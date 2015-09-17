package me.suiyueyu.algs4.sec2;

import stdlib.In;

/**
 * Created by boge on 2015/8/11.
 */
public class Merge_alg_2_4 extends Example {

    private static Comparable[] aux;

    public static void sort(Comparable[] a) {
        aux = new Comparable[a.length];
        sort(a, 0, a.length - 1);
    }
    private static void sort(Comparable[] a, int lo, int hi) {
        if (hi <= lo) {
            return ;
        }
        int mid = lo + (hi - lo) / 2;
        sort(a, lo, mid);
        sort(a, mid + 1, hi);
        merge(a, lo, mid, hi);
    }
    private static void sortBU(Comparable[] a){
        int N = a.length;
        aux = new Comparable[N];
        for(int sz = 1; sz < N ; sz = sz+sz){
            for (int lo = 0; lo < N-sz; lo += (sz+sz)){
                merge( a, lo, lo+sz-1, Math.min(lo+sz+sz-1, N-1));
            }
        }
    }
    public static void merge(Comparable[] a , int lo, int mid, int hi) {
        int i = lo, j = mid + 1;

        for (int k = lo; k <= hi; k++) {
            aux[k] = a[k];
        }

        for (int k = lo; k <= hi; k++) {
            if (i > mid) {
                a[k] = aux[j++];
            } else if (j > hi) {
                a[k] = aux[i++];
            } else if (less(a[j], a[i])) {
                a[k] = aux[j++];
            } else {
                a[k] = aux[i++];
            }
        }
    }
    public static void main(String[] args) {
        String[] a = In.readStrings();
        sort(a);
        assert isSorted(a);
        show(a);
    }
}

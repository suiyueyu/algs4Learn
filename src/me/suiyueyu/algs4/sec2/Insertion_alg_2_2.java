package me.suiyueyu.algs4.sec2;

import stdlib.In;

/**
 * Created by boge on 2015/8/7.
 */
public class Insertion_alg_2_2 extends Example{
    public static void sort(Comparable[] a){
        int N = a.length;

        for (int i = 1; i < N; i++) {
            for (int j = i; (j>0) && less(a[j],a[j-1]); j--) {
                exch(a,j,j-1);
            }
        }
    }
    public static void main(String[] args){
        String[] a = In.readStrings();
        sort(a);
        assert isSorted(a);
        show(a);
    }
}

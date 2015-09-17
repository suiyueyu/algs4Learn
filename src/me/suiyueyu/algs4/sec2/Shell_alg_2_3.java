package me.suiyueyu.algs4.sec2;

import stdlib.In;

/**
 * Created by boge on 2015/8/11.
 */
public class Shell_alg_2_3 extends Example {
    public static void sort(Comparable[] a ){
        int N = a.length;
        int h = 1;
        while (h < N/3) {
            h = 3*h + 1;
        }
        while(h>=1){
            for (int i = h; i < N; i++) {
                for (int j = i; (j >= h) && less(a[j],a[j-h]); j -= h) {
                    exch(a,j,j-h);
                }
            }
            h = h/3;
        }
    }
    public static void main(String[] args){
        String[] a = In.readStrings();
        sort(a);
        assert isSorted(a);
        show(a);
    }
}

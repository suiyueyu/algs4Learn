package me.suiyueyu.algs4.sec2;


import edu.princeton.cs.algs4.In;

/**
 * Created by boge on 2015/8/7.
 */
public class Selection_alg_2_1 extends Example {
    public static void sort(Comparable[] a){
        int N = a.length;

        for (int i = 0; i < N; i++) {
            int min = i;
            for (int j = i+1; j < N; j++) {
                if (less(a[j],a[min])){
                    min = j;
                }
            }
            exch(a,i,min);
        }
    }
    public static void main(String[] args){
        String[] a = In.readStrings();
        sort(a);
        assert isSorted(a);
        show(a);
    }
}

package me.suiyueyu.algs4.sec1.algs;


import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import me.suiyueyu.algs4.sec1.BinarySearch;

import java.util.Arrays;

/**
 * Created by yzcc on 2016/8/10.
 */
public class TwoSumFast {
    public static int count(int[] a) {
        Arrays.sort(a);
        int N = a.length;
        int cnt = 0;
        for (int i = 0; i < N; i++) {
            if (BinarySearch.rank(-a[i], a) > i) {
                cnt++;
            }
        }
        return cnt;
    }

    public static void main(String[] args) {
        int[] a = In.readInts(args[0]);
        StdOut.println(count(a));

    }
}

package me.suiyueyu.algs4.sec1.algs;


import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

/**
 * Created by yzcc on 2016/8/10.
 */
public class ThreeSumFast {
    public static int count(int[] a) {
        Arrays.sort(a);
        int N = a.length;
        int cnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                // 1.4.2 修改ThreeSum 正确处理两个较大的int值相加可能溢出的情况。
                long sum = a[i] + a[j];
                assert (sum < Integer.MAX_VALUE) && (sum > -Integer.MAX_VALUE) : "some value's sum is overflow a integer";
                if (BinarySearch.rank(-a[i] - a[j], a) > i) {
                    cnt++;
                }
            }

        }
        return cnt;
    }

    public static void main(String[] args) {
//        int[] a = In.readInts(args[0]);
        int[] a = StdIn.readAllInts();
        StdOut.println(count(a));

    }
}

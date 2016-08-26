package me.suiyueyu.algs4.sec2.exrecise.ex_2_2_test;

import edu.princeton.cs.algs4.StdRandom;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * Created by yzcc on 2016/8/18.
 */
public class SortTestUseCase {
    public final static Integer[] i = new Integer[]{2, 3, 4, 6, 3, 2, 1, 6, 8};
    public final static Integer[] iSored = new Integer[]{1, 2, 2, 3, 3, 4, 6, 6, 8};
    public final static Double[] d = new Double[1024];
    public final static Double[] dSorted;

    static {
        for (int i = 0; i < d.length; i++) {
            d[i] = StdRandom.uniform();
        }
        dSorted = d.clone();
        Arrays.sort(dSorted);

    }

}

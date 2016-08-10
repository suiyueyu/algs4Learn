package me.suiyueyu.algs4.sec1.exercise.ex_1_4;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * Created by yzcc on 2016/8/10.
 * 1.4.16 最接近的一对(一维)。编写一个程序，给定一个含有N个double值的数组a[]
 * 在其中找到一对最接近的值：两者之差(绝对值)最小的两个数。程序在最坏情况下所需的运行时间
 * 是线性对数级别的
 * <p>
 * 这题能不能用排序？不用排序怎么做？
 */
public class ClosestPair {

    /**
     * 这个不用排序怎么做？
     *
     * @param a
     * @return
     */
    public static double[] findClossetPair(double[] a) {
        if (a.length < 2) {
            return null;
        }
        double[] pair = new double[2];
        Arrays.sort(a);
        double minAbsDifference = Double.MAX_VALUE;

//        minAbsDifference = Math.abs(a[0] - a[1]);

//        pair.lo = a[0];
//        pair.hi = a[1];

        double diff;

        for (int i = 0; i < a.length - 1; i++) {
            diff = Math.abs(a[i] - a[i + 1]);
            if (diff < minAbsDifference) {
                minAbsDifference = diff;
                pair[0] = a[i];
                pair[1] = a[i + 1];
            }
        }
        return pair;
    }

    public static void main(String[] args) {
        double[] a = {1.0, 1.1, 2.0, 3.0, 4.0, 5.0};
        double[] assertResult = {1.0, 1.1};

        double[] res = ClosestPair.findClossetPair(a);
        for (double d : res) {
            System.out.print(d + " ");
        }
        System.out.println(Arrays.equals(res, assertResult));
    }
}

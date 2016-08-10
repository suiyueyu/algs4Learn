package me.suiyueyu.algs4.sec1.exercise.ex_1_4;

import edu.princeton.cs.algs4.Particle;

import java.util.Arrays;

/**
 * Created by yzcc on 2016/8/10.
 * 1.4.17 最遥远的一对(一维)。编写一个程序，给定一个含有N个double值的数组a[]在其中找到一对最遥远的值：
 * 两者之差(绝对值)最大的两个数。程序在最坏情况下所需的运行时间应该是线性级别的。
 */
public class FarthestPair {

    public static double[] getPair(double[] a) {
        double max = -Double.MAX_VALUE;
        double min = -max;

        for (int i = 0; i < a.length; i++) {
            if (a[i] > max) {
                max = a[i];
            }
            if (a[i] < min) {
                min = a[i];
            }
        }
        if (a.length < 2) {
            return null;
        } else {
            return new double[]{min, max};
        }
    }

}

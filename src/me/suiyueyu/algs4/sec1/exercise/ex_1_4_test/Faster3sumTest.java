package me.suiyueyu.algs4.sec1.exercise.ex_1_4_test;

import me.suiyueyu.algs4.sec1.exercise.ex_1_4.Faster3sum;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by yzcc on 2016/8/10.
 */
public class Faster3sumTest extends Object {
    @Test
    public void twoSumCount() throws Exception {
        int[] a = {-2, -1, 0, 1, 2, 3};
        int[] a1 = {-2, -1, 0, 0, 1, 2, 3};
        int[] a2 = {-2, -1, 0, 0, 0, 1, 2, 3};
        int[] a3 = {-2, -1, 0, 0, 0, 1, 1, 1, 2, 3};

        // 最简单情形
        assertEquals(Faster3sum.twoSumCount(a), 2);
        // 含有0, 对应到lo和hi会在这次检测后直接 lo > hi
        assertEquals(Faster3sum.twoSumCount(a1), 3);
        // 多个0
        assertEquals(Faster3sum.twoSumCount(a2), 5);
        // 多个别的
        assertEquals(Faster3sum.twoSumCount(a3), 7);
    }

    @Test
    public void twoSumCount1() throws Exception {
        int[] a = {-2, -1, 0, 1, 2, 3};
        int[] a1 = {-2, -1, 0, 0, 1, 2, 3};
        int[] a2 = {-2, -1, 0, 0, 0, 1, 2, 3};
        int[] a3 = {-2, -1, 0, 0, 0, 1, 1, 1, 2, 3};

        // 最简单情形
        assertEquals(Faster3sum.twoSumCount(-1, a3, 0, a3.length - 1), 6);
        // a[i] = sum/2
        assertEquals(Faster3sum.twoSumCount(2, a3, 0, a3.length - 1), 7);
    }

}
package me.suiyueyu.algs4.sec2.exrecise.ex_2_1_test;

import edu.princeton.cs.algs4.StdRandom;
import me.suiyueyu.algs4.sec2.exrecise.ex_2_1.ShellSort;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * Created by yzcc on 2016/8/17.
 */
public class ShellSortTest {
    @Test
    public void sort() throws Exception {
//        Integer[] a  = new Integer[] {2, 3, 4, 6, 3, 2,1 ,6 , 8};
        Double[] a = new Double[20];
        for (int i = 0; i < a.length; i++) {
            a[i] = StdRandom.uniform();
        }

        Double[] b = a.clone();
        Arrays.sort(b);
        ShellSort.sort(a);


        for (double d : a) {
            System.out.printf("%.2f ", d);
        }
        System.out.println();
        assertTrue(Arrays.equals(a, b));
    }


}
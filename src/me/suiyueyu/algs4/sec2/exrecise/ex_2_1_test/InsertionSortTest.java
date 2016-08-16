package me.suiyueyu.algs4.sec2.exrecise.ex_2_1_test;

import me.suiyueyu.algs4.sec2.exrecise.ex_2_1.InsertionSort;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * Created by yzcc on 2016/8/16.
 */
public class InsertionSortTest {
    @Test
    public void sort() throws Exception {
        Integer[] a = new Integer[]{2, 3, 4, 5, 3, 2};
        Integer[] aSorted = new Integer[]{2, 2, 3, 3, 4, 5};

        InsertionSort.sort(a);

        assertTrue(Arrays.equals(a, aSorted));

        int[] b = new int[]{2, 3, 4, 5, 3, 2};
        int[] bSorted = new int[]{2, 2, 3, 3, 4, 5};
        InsertionSort.sort(b);

        assertTrue(Arrays.equals(b, bSorted));
    }

}
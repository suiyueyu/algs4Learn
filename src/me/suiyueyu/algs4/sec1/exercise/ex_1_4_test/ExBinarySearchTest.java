package me.suiyueyu.algs4.sec1.exercise.ex_1_4_test;

import me.suiyueyu.algs4.sec1.exercise.ex_1_4.ExBinarySearch;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by yzcc on 2016/8/10.
 */
public class ExBinarySearchTest extends Object {
    @Test
    public void rankLower() throws Exception {
        int[] a1 = {1, 2, 3, 4};
        int[] a2 = {0, 2, 3, 4, 4};
        int[] a3 = {1, 2, 3, 4, 4, 5};
        int[] a4 = {4, 2, 3, 1, 4, 5};

        int key1 = 0;
        int key2 = 4;
        int key3 = 4;

        assertEquals(ExBinarySearch.rankLower(key1, a1), -1);
        assertEquals(ExBinarySearch.rankLower(key1, a2), 0);
        assertEquals(ExBinarySearch.rankLower(key3, a1), 3);

        assertEquals(ExBinarySearch.rankLower(key2, a3), 3);
        assertEquals(ExBinarySearch.rankLower(key2, a4), 3);
        assertEquals(ExBinarySearch.rankLower(key1, a4), -1);
    }

}
package me.suiyueyu.algs4.sec1.exercise.ex_1_4_test;

import me.suiyueyu.algs4.sec1.exercise.ex_1_4.LocalMinimum;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.*;

/**
 * Created by yzcc on 2016/8/11.
 */

public class LocalMinimumTest {
    @Ignore
    public void minimumIndex() throws Exception {
        int[] a = {1, 2, 3, 4, 5};
        assertEquals(LocalMinimum.findArrayValley(a), 0);

        int[] a2 = {1, 3, 2, 4, 6};
        assertEquals(LocalMinimum.findArrayValley(a2), 2);

        int[] a3 = {1};
        assertEquals(LocalMinimum.findArrayValley(a3), 0);

        int[] a4 = {2, 1, 3, 4, 5, 6, 7, 8, 10, 9};
        assertEquals(LocalMinimum.findArrayValley(a4), 1);
    }

}
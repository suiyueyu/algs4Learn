package me.suiyueyu.algs4.sec2.exrecise.ex_2_2_test;

import me.suiyueyu.algs4.sec2.exrecise.ex_2_2.MergeSortImprovements;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * Created by yzcc on 2016/8/18.
 */
public class MergeSortImprovementsTest extends SortTestUseCase {
    @Test
    public void sort() throws Exception {
        MergeSortImprovements.sort(i);
        assertTrue(Arrays.equals(iSored, i));

    }

    @Test
    public void sort2() throws Exception {
        MergeSortImprovements.sort(d);
        assertTrue(Arrays.equals(dSorted, d));
    }

}
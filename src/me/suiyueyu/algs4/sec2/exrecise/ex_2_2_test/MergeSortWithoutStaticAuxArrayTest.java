package me.suiyueyu.algs4.sec2.exrecise.ex_2_2_test;

import edu.princeton.cs.algs4.StdRandom;
import me.suiyueyu.algs4.sec2.algs.MergeBU;
import me.suiyueyu.algs4.sec2.exrecise.ex_2_2.MergeSortWithoutStaticAuxArray;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * Created by yzcc on 2016/8/17.
 */
public class MergeSortWithoutStaticAuxArrayTest {
    @Test
    public void sort() throws Exception {

        Double[] d = new Double[20];
        for (int i = 0; i < d.length; i++) {
            d[i] = StdRandom.uniform();
        }

        Double[] b = d.clone();
        Arrays.sort(b);
        MergeSortWithoutStaticAuxArray.sort(d);

        assertTrue(Arrays.equals(d, b));

        Integer[] i = new Integer[]{2, 3, 4, 6, 3, 2, 1, 6, 8};
        Integer[] iSort = i.clone();

        Arrays.sort(iSort);
        MergeBU.sort(i);

//        for (int k = 0; k < i.length; k++){
//            System.out.println(i[k] + " - " + iSort[k]);
//        }

        assertTrue(Arrays.equals(i, iSort));
    }

}
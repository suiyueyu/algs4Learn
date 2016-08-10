package me.suiyueyu.algs4.sec1.exercise.ex_1_4_test;

import me.suiyueyu.algs4.sec1.exercise.ex_1_4.FarthestPair;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * Created by yzcc on 2016/8/10.
 */
public class FarthestPairTest {
    @Test
    public void getPair() throws Exception {
        double[] a = {1.0, 2.0, 3.0, 4.0, 5.0};
        double[] assertResult = {1.0, 5.0};
        assertTrue(Arrays.equals(FarthestPair.getPair(a), assertResult));
    }

}